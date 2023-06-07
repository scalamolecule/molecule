package molecule.sql.jdbc.facade

import java.sql
import java.sql.SQLException
import datomic.{Datom => _}
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.marshalling.JdbcProxy
import molecule.core.spi.{Conn, TxReport}
import molecule.sql.jdbc.transaction.{JdbcDataType_JVM, JdbcTxBase_JVM, JoinTableInsert, TableInsert}
import scala.util.control.NonFatal

case class JdbcConn_jvm(override val proxy: JdbcProxy, sqlConn: sql.Connection)
  extends Conn(proxy)
    with JdbcDataType_JVM
    with MoleculeLogging
    with JdbcTxBase_JVM {

  private[molecule] var fresh = true


  val idsMap0 = Map.empty[(List[String], String), Array[Long]]
  val ids0    = Array.empty[Long]

  //  //  override def transact_sync(resolvers: Data): TxReport = {
  //  def transact_sync2(resolvers: Data): TxReport = {
  //    try {
  //      // Atomic transaction of all statements
  //      sqlConn.setAutoCommit(false)
  //
  //      // Recursively resolve backwards from nested/last insert(s) to start insert
  //      val (_, ids) = inserts(resolvers.reverse, idsMap0, ids0)
  //
  //      // transact all
  //      sqlConn.commit()
  //
  //      // Tx entity not implemented for sql-jdbc
  //      TxReport(0, ids.toList) // Use only top-level ids (ok?)
  //    } catch {
  //      // re-throw errors to keep stacktrace back to original error
  //      case e: SQLException =>
  //        try {
  //          sqlConn.rollback()
  //          logger.warn("Successfully rolled back unsuccessful insertion with error: " + e)
  //          throw e
  //        } catch {
  //          case e: SQLException =>
  //            logger.error("Couldn't roll back unsuccessful insertion: " + e)
  //            throw e
  //        }
  //      case NonFatal(e)     =>
  //        logger.error("Unexpected insertion error: " + e)
  //        throw e
  //    }
  //  }
  //
  //  @tailrec
  //  private def inserts(
  //    insertResolvers: List[Table],
  //    idsMap: IdsMap,
  //    ids: Array[Long]
  //  ): (IdsMap, Array[Long]) = {
  //    insertResolvers match {
  //      case Table(refPath, ns, stmt, ps, populatePS, Nil) :: tail =>
  //        // Top level
  //        val (idsMap1, ids1) = insert(idsMap, refPath, ns, stmt, ps, populatePS)
  //        inserts(tail, idsMap1, ids1)
  //
  //      case Table(refPath, ns, stmt, ps, populatePS, nestedInsertResolvers) :: tail =>
  //        // Nested level
  //        val (idsMap1, _)    = resolveNested(nestedInsertResolvers, idsMap, refPath, ns)
  //        val (idsMap2, ids2) = insert(idsMap1, refPath, ns, stmt, ps, populatePS)
  //        inserts(tail, idsMap2, ids2)
  //
  //      case Nil => (idsMap, ids)
  //    }
  //  }
  //
  //  private def resolveNested(
  //    nestedInsertResolvers: List[Table],
  //    idsMap: IdsMap,
  //    refPath: List[String],
  //    ns: String,
  //  ): (IdsMap, Array[Long]) = {
  //    val (_, ids) = inserts(nestedInsertResolvers.reverse, idsMap0, ids0)
  //    (idsMap.+((refPath, ns) -> ids), ids)
  //  }
  //
  //  private def insert(
  //    idsMap: IdsMap,
  //    refPath: List[String],
  //    ns: String,
  //    stmt: String,
  //    ps: PS,
  //    populatePS: (PS, IdsMap, RowIndex) => Unit = null,
  //  ): (IdsMap, Array[Long]) = {
  //    // Populate prepared statement
  //    println(s"--------------------------------------------------------------\n" + stmt)
  //    populatePS(ps, idsMap, -1) // rowIndex not used here
  //    ps.executeBatch()
  //    val resultSet = ps.getGeneratedKeys // is empty if no nested data
  //    var ids       = ids0
  //    while (resultSet.next()) {
  //      ids = ids :+ resultSet.getLong(1)
  //    }
  //    ps.close()
  //    // Cache generated ids for this insert
  //    (idsMap.+((refPath, ns) -> ids), ids)
  //  }

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
        case TableInsert(refPath, stmt, ps, populatePS) =>
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
        case JoinTableInsert(refPath, stmt, ps, leftPath, rightPath, rightCounts) =>
          println("--------------------------------------------------------------\n" + stmt)
          println(rightCounts)
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
      TxReport(0, ids.toList) // Use only top-level ids (ok?)
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