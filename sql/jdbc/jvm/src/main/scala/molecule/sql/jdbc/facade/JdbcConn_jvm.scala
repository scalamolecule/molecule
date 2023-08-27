package molecule.sql.jdbc.facade

import java.sql
import java.sql.SQLException
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.marshalling.JdbcProxy
import molecule.core.spi.{Conn, TxReport}
import molecule.sql.jdbc.transaction.{JdbcBase_JVM, JdbcDataType_JVM, JoinTable, Table}
import scala.util.control.NonFatal

case class JdbcConn_jvm(override val proxy: JdbcProxy, override val sqlConn: sql.Connection)
  extends Conn(proxy)
    with JdbcDataType_JVM
    with MoleculeLogging
    with JdbcBase_JVM {

  private[molecule] var fresh = true

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

    //    debug("##########################################################################################")

    // Insert statements backwards to obtain auto-generated ref ids for prepending inserts
    tables.reverse.foreach {
      case Table(refPath, stmt, ps, populatePS) =>
        debug("D --- table ----------------------------------------------\n" + stmt)
        // Populate prepared statement
        populatePS(ps, idsMap, 0)

        ps.executeBatch()
        val resultSet = ps.getGeneratedKeys // is empty if no nested data
        ids = List.empty[Long]
        while (resultSet.next()) {
          //          val id = resultSet.getLong(1)
          //          debug("D  ################# " + id)
          //          ids = ids :+ id
          ids = ids :+ resultSet.getLong(1)
        }
        ps.close()
        idsMap = idsMap + (refPath -> ids)
      //        debug("idsMap: " + idsMap)
    }

    joinTables.foreach {
      case JoinTable(refPath, stmt, ps, leftPath, rightPath, rightCounts) =>
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