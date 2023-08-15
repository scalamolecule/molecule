package molecule.sql.jdbc.facade

import java.sql
import java.sql.SQLException
import datomic.{Datom => _}
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.marshalling.JdbcProxy
import molecule.core.spi.{Conn, TxReport}
import molecule.sql.jdbc.transaction.{JdbcDataType_JVM, JdbcBase_JVM, JoinTable, Table}
import scala.util.control.NonFatal

case class JdbcConn_jvm(override val proxy: JdbcProxy, override val sqlConn: sql.Connection)
  extends Conn(proxy)
    with JdbcDataType_JVM
    with MoleculeLogging
    with JdbcBase_JVM {

  private[molecule] var fresh = true

  override def transact_sync(inserts: Data): TxReport = {
    val tableInserts     = inserts._1
    val joinTableInserts = inserts._2
    //    val idsMap             = mutable.Map.empty[List[String], Array[Int]]
    //    var ids              = Array.empty[Long]
    var idsMap           = Map.empty[List[String], List[Long]]
    var ids              = List.empty[Long]
    try {
      // Atomic transaction of all statements
      sqlConn.setAutoCommit(false)

      // Insert statements backwards to obtain auto-generated ref ids for prepending inserts
      tableInserts.reverse.foreach {
        case Table(refPath, stmt, ps, populatePS) =>
          //          println("--------------------------------------------------------------\n" + stmt)
          //          println("--------------------------------------------------------------")
          //          println(idsMap)
          // Populate prepared statement
          populatePS(ps, idsMap, 0)

          ps.executeBatch()
          val resultSet = ps.getGeneratedKeys // is empty if no nested data
          //          ids = Array.empty[Long]
          ids = List.empty[Long]
          while (resultSet.next()) {
            ids = ids :+ resultSet.getLong(1)
          }
          ps.close()
          idsMap = idsMap + (refPath -> ids)
      }

      joinTableInserts.foreach {
        case JoinTable(refPath, stmt, ps, leftPath, rightPath, rightCounts) =>
          //          println("--------------------------------------------------------------\n" + stmt)
          //          println(rightCounts)

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

      // transact all
      sqlConn.commit()

      // Tx entity not implemented for sql-jdbc
      TxReport(0, ids) // Use only top-level ids (ok?)
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
}