package molecule.sql.core.facade

import java.sql
import java.sql.{Connection, PreparedStatement, ResultSet, SQLException}
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.marshalling.JdbcProxy
import molecule.core.spi.{Conn, TxReport}
import molecule.core.util.ModelUtils
import molecule.sql.core.javaSql.{ResultSetImpl, ResultSetInterface}
import molecule.sql.core.transaction.{CachedConnection, SqlDataType_JVM}
import molecule.sql.core.transaction.strategy.SqlAction
import scala.util.control.NonFatal

case class JdbcConn_JVM(
  override val proxy: JdbcProxy,
  private val sqlConn0: sql.Connection
) extends Conn(proxy)
  with CachedConnection
  with SqlDataType_JVM
  with ModelUtils
  with MoleculeLogging {

  lazy val sqlConn: Connection = sqlConn0

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

  override def transact_sync(action: SqlAction): TxReport = {
    atomicTransaction(() => action.execute)
  }

  def atomicTransaction(executions: () => List[Long]): TxReport = {
    try {
      // Atomic transaction of all statements
      sqlConn.setAutoCommit(false)

      // Execute batches and get ids of affected entities of initial namespace
      val ids = executions()

      // commit or fail all
      sqlConn.commit()

      TxReport(ids)
    } catch {
      // re-throw errors to keep stacktrace back to original error
      case e: SQLException =>
        try {
          sqlConn.rollback()
          logger.warn(
            "Successfully rolled back unsuccessful insertion with error: " + e
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
    }  finally sqlConn0.setAutoCommit(true)
  }
}