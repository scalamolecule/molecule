package molecule.sql.core.facade

import java.sql
import java.sql.{Connection, SQLException}
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.marshalling.JdbcProxy
import molecule.core.spi.{Conn, TxReport}
import molecule.core.util.ModelUtils
import molecule.sql.core.transaction.{JdbcDataType_JVM, JoinTable, SqlBase_JVM, Table}
import scala.concurrent.{ExecutionContext, Future}
import scala.util.control.NonFatal

case class JdbcConn_JVM(
  override val proxy: JdbcProxy,
  private val sqlConn0: sql.Connection
) extends Conn(proxy)
  with JdbcDataType_JVM
  with SqlBase_JVM
  with ModelUtils
  with MoleculeLogging {


  override lazy val sqlConn: Connection = sqlConn0

  doPrint = false

  override def transact_async(data: (List[Table], List[JoinTable]))
                             (implicit ec: ExecutionContext): Future[TxReport] = {
    Future(transact_sync(data))
  }

  override def transact_sync(data: Data): TxReport = {
    atomicTransaction(() => populateStmts(data))
  }

  def atomicTransaction(executions: () => Map[List[String], List[Long]]): TxReport = {
    try {
      // Atomic transaction of all statements
      sqlConn.setAutoCommit(false)

      // Execute batches
      val idsMap = executions()

      // transact all
      sqlConn.commit()

      val entityIdsInvolved = idsMap.values.toList.reverse.flatten

      // Tx entity not implemented for sql-jdbc
      TxReport(0, entityIdsInvolved)
    } catch {
      // re-throw errors to keep stacktrace back to original error
      case e: SQLException =>
        try {
          sqlConn.rollback()
          logger.warn("Successfully rolled back unsuccessful insertion with error: " + e)
          throw e
        } catch {
          case e: SQLException =>
            logger.error("Couldn't roll back unsuccessful insertion: " + e)
            throw e
        }
      case NonFatal(e)     =>
        logger.error("Unexpected insertion error: " + e)
        throw e
    }
  }

  def populateStmts(data: Data): Map[List[String], List[Long]] = {
    val tables     = data._1
    val joinTables = data._2
    var idsMap     = Map.empty[List[String], List[Long]]
    var ids        = List.empty[Long]

    debug("########################################################################################## " + tables.size)

    // Insert statements backwards to obtain auto-generated ref ids for prepending inserts
    tables.reverse.foreach {
      case Table(refPath, stmt, ps, populatePS) =>
        debug("D --- table ----------------------------------------------")
        debug("idsMap 1: " + idsMap)
        debug(stmt)

        populatePS(ps, idsMap, 0)

        // Populate prepared statement
        ps.executeBatch()
        try {
          val resultSet = ps.getGeneratedKeys
          ids = List.empty[Long]
          if (!refPath.last.init.contains("_")) { // no join tables, but still "_"-suffixed tables
            while (resultSet.next()) {
              //              val id = resultSet.getLong(1)
              //              debug("D  ################# " + id)
              //              ids = ids :+ id
              ids = ids :+ resultSet.getLong(1)
            }
          }
        } catch {
          case e: NullPointerException =>
            // MariaDB can return null instead of empty ResultSet which we simply ignore
            logger.debug("Resultset was null")
            ()
          case NonFatal(e)             =>
            e.printStackTrace()
            throw e
        }
        ps.close()
        idsMap = idsMap + (refPath -> ids)
        debug("idsMap 2: " + idsMap)
    }

    joinTables.foreach {
      case JoinTable(stmt, ps, leftPath, rightPath, rightCounts) =>
        debug("D --- joinTable -------------------------------------------------\n" + stmt)
        val idsLeft    = idsMap(leftPath)
        var idLeft     = 0L
        var leftIndex  = 0
        val idsRight   = idsMap(rightPath)
        var rightIndex = 0
        var i          = 0
        while (leftIndex != idsLeft.length) {
          idLeft = idsLeft(leftIndex)
          i = 0
          while (i != rightCounts(leftIndex)) {
            ps.setLong(1, idLeft)
            ps.setLong(2, idsRight(rightIndex))
            ps.addBatch()
            rightIndex += 1
            i += 1
          }
          leftIndex += 1
        }
        ps.executeBatch()
    }

    idsMap
  }
}