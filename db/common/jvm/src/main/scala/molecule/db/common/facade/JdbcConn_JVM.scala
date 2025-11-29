package molecule.db.common.facade

import java.sql
import java.sql.{Connection, PreparedStatement, ResultSet, SQLException}
import scala.collection.mutable
import scala.concurrent.{ExecutionContext, Future}
import scala.util.control.NonFatal
import cats.effect.IO
import molecule.core.error.MoleculeError
import molecule.core.util.MoleculeLogging
import molecule.db.common.api.Savepoint
import molecule.db.common.javaSql.{ResultSetImpl, ResultSetInterface}
import molecule.db.common.marshalling.JdbcProxy
import molecule.db.common.spi.{Conn, TxReport}
import molecule.db.common.transaction.{CachedConnection, SavepointImpl}
import molecule.db.common.util.ModelUtils
import zio.ZIO

case class JdbcConn_JVM(
  override val proxy: JdbcProxy,
  private val sqlConn0: sql.Connection,
  override val authContext: Option[molecule.db.common.api.AuthContext] = None
) extends Conn(proxy, authContext)
  with AutoCloseable
  with CachedConnection
  with ModelUtils
  with MoleculeLogging {

  val sqlConn: Connection = sqlConn0

  /** Create a new connection with the specified authentication context */
  override def withAuthContext(authCtx: molecule.db.common.api.AuthContext): Conn =
    copy(authContext = Some(authCtx))

  /** Create a new connection without authentication (public access only) */
  override def clearAuth: Conn =
    copy(authContext = None)

  def queryStmt(query: String): PreparedStatement = {
    sqlConn.prepareStatement(
      query,
      ResultSet.TYPE_SCROLL_INSENSITIVE,
      ResultSet.CONCUR_READ_ONLY
    )
  }

  def resultSet(underlying: ResultSet): ResultSetInterface = {
    new ResultSetImpl(underlying)
  }

  def atomicTransaction(executions: () => List[Long]): TxReport = {
    try {
      // Atomic transaction of all statements
      sqlConn.setAutoCommit(false)

      // Execute and get affected initial entity ids
      val ids = executions()

      if (commit_) {
        sqlConn.commit()
      }

      TxReport(ids)
    } catch {
      // re-throw errors to keep stacktrace back to the original error
      case e: SQLException =>
        try {
          sqlConn.rollback()
          logger.warn(
            "Successfully rolled back unsuccessful transaction with error: " + e
          )
          throw e
        } catch {
          case e: SQLException =>
            logger.error("Couldn't roll back unsuccessful transaction: " + e)
            throw e
        }
      case NonFatal(e)     =>
        logger.error("Unexpected transaction error: " + e)
        throw e
    } finally {
      if (commit_) {
        sqlConn.setAutoCommit(true)
      }
    }
  }


  // Savepoint --------------------------------------------------------
  // Thankfully taken from ScalaSql

  // (Using ListBuffer instead of ArrayDeque for compatibility with Scala 2.12)
  val savepointStack = mutable.ListBuffer.empty[java.sql.Savepoint]

  override def waitCommitting(): Unit = {
    commit_ = false
  }

  override def commit(): Unit = {
    commit_ = true
    sqlConn.commit()
  }

  override def rollback(): Unit = {
    commit_ = false
    savepointStack.clear()
    try {
      sqlConn.setAutoCommit(false)
      sqlConn.rollback()
      logger.info(
        "Successfully rolled back"
      )
    } catch {
      case e: SQLException =>
        logger.error("Couldn't roll back unsuccessful transaction: " + e)
        throw e
      case NonFatal(e)     =>
        logger.error("Unexpected rollback error: " + e)
        throw e
    }
  }

  override def savepoint_sync[T](runSavepoint: Savepoint => T): T = {
    setAutoCommit(false)
    val savepoint = sqlConn.setSavepoint()
    savepointStack.append(savepoint)
    try {
      val sp  = new SavepointImpl(savepoint, () => rollbackSavepoint(savepoint))
      val res = runSavepoint(sp)
      if (savepointStack.lastOption.exists(_ eq savepoint)) {
        // Only release if this savepoint has not been rolled back,
        // directly or indirectly
        sqlConn.releaseSavepoint(savepoint)
      }
      res
    } catch {
      case e: Throwable =>
        rollbackSavepoint(savepoint)
        throw e
    }
  }

  override def savepoint_async[T](runSavepoint: Savepoint => Future[T])
                                 (using ec: ExecutionContext): Future[T] = {
    setAutoCommit(false)
    val savepoint = sqlConn.setSavepoint()
    savepointStack.append(savepoint)
    val sp = new SavepointImpl(savepoint, () => rollbackSavepoint(savepoint))
    runSavepoint(sp)
      .map { res =>
        if (savepointStack.lastOption.exists(_ eq savepoint)) {
          // Only release if this savepoint has not been rolled back,
          // directly or indirectly
          sqlConn.releaseSavepoint(savepoint)
        }
        res
      }
      .recover { case e =>
        // Rollback all executed actions so far
        rollbackSavepoint(savepoint)
        throw e
      }
  }

  override def savepoint_zio[T](
    runSavepoint: Savepoint => ZIO[Conn, MoleculeError, T]
  ): ZIO[Conn, MoleculeError, T] = {
    setAutoCommit(false)
    val savepoint = sqlConn.setSavepoint()
    savepointStack.append(savepoint)
    val sp = new SavepointImpl(savepoint, () => rollbackSavepoint(savepoint))
    runSavepoint(sp)
      .map { res =>
        if (savepointStack.lastOption.exists(_ eq savepoint)) {
          // Only release if this savepoint has not been rolled back,
          // directly or indirectly
          sqlConn.releaseSavepoint(savepoint)
        }
        res
      }
      .mapError { e =>
        // Rollback all executed actions so far
        rollbackSavepoint(savepoint)
        throw e
      }
  }

  override def savepoint_io[T](runSavepoint: Savepoint => IO[T]): IO[T] = {
    setAutoCommit(false)
    val savepoint = sqlConn.setSavepoint()
    savepointStack.append(savepoint)
    val sp = new SavepointImpl(savepoint, () => rollbackSavepoint(savepoint))
    runSavepoint(sp).attempt.map {
      case Right(t)    =>
        if (savepointStack.lastOption.exists(_ eq savepoint)) {
          // Only release if this savepoint has not been rolled back,
          // directly or indirectly
          sqlConn.releaseSavepoint(savepoint)
        }
        t
      case Left(error) =>
        // Rollback all executed actions so far
        rollbackSavepoint(savepoint)
        throw error
    }
  }

  // Make sure we keep track of what savepoints are active on the stack, so we do
  // not release or rollback the same savepoint multiple times even in the case of
  // exceptions or explicit rollbacks
  private def rollbackSavepoint(savepoint: java.sql.Savepoint): Unit = {
    savepointStack.indexOf(savepoint) match {
      case -1             => // do nothing
      case savepointIndex => {
        sqlConn.rollback(savepointStack(savepointIndex))
        savepointStack.takeInPlace(savepointIndex)
      }
    }
  }

  override def setAutoCommit(bool: Boolean): Unit = sqlConn.setAutoCommit(false)

  override def close(): Unit = sqlConn.close()
}