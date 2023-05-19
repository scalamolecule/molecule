package molecule.sql.jdbc.facade

import java.io.StringReader
import java.sql.{SQLException, Statement}
import java.util.{List => jList}
import java.{sql, lang => jl, util => ju}
import datomic.Util.readAll
import datomic.{Connection => DatomicConnection, Datom => _, _}
import molecule.base.error._
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.api.{Connection, TxReport}
import molecule.core.marshalling.{DatomicPeerProxy, SqlProxy}
import molecule.sql.jdbc.transaction.{JdbcDataType_JVM, Resolver}
import molecule.sql.jdbc.util.MakeTxReport
import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.concurrent.duration.DurationInt
import scala.concurrent.{Await, ExecutionContext, Future, Promise}
import scala.jdk.CollectionConverters._
import scala.util.control.NonFatal

case class JdbcConn_JVM(
  override val proxy: SqlProxy,
  sqlConn: sql.Connection
) extends Connection(proxy)
  with JdbcDataType_JVM
  with MoleculeLogging {

  private[molecule] var fresh = true

  override def transact_async(data: Data)(implicit ec: ExecutionContext): Future[TxReport] = {
    Future(transact_sync(data))
  }

  override def transact_sync(resolvers: Data): TxReport = {
    //    val (insertStmts, batchSetters) = resolvers

    val insertsCount = resolvers.length
    var insertIndex  = insertsCount
    //    val pss          = insertStmts.map(sqlConn.prepareStatement(_, Statement.RETURN_GENERATED_KEYS))
    var ids          = Array.empty[Long]
    val idLists      = new Array[List[Long]](insertsCount)
    val allIds       = new ListBuffer[Long]

    // Cache ref ids on possible levels (top level + 7 nested levels)
    //    val refMaps = new Array[Map[String, List[Long]]](8).map(_ => Map.empty[String, List[Long]])
    var refMap = Map.empty[(Int, String, String), Array[Long]]

    //    println("insertsCount: " + insertsCount)
    //    println("batchSetters.count: " + batchSetters.length)


    try {
      // Atomic transaction of all statements
      sqlConn.setAutoCommit(false)

      // Insert statements backwards to obtain ref ids for prepending inserts
      resolvers.reverse.foreach { case Resolver(level, ns, selfRef, stmt, ps, resolve, nestedCounts) =>
        insertIndex -= 1

        // Populate prepared statement
        println("--------------------------------------------------------------\n" + stmt)

        //        if (insertIndex == 0) {
        //          refMap.foreach{
        //            case ((a, b, c), ids) => println(s"$a  $b  $c  " + ids.toList)
        //          }
        //          println("------------")
        //        }
        resolve(ps, refMap, 0)

        ps.executeBatch()
        val resultSet = ps.getGeneratedKeys // is empty if no nested data

        ids = Array.empty[Long]
        while (resultSet.next()) {
          //          ids.addOne(resultSet.getLong(1))
          ids = ids :+ resultSet.getLong(1)
        }
        ps.close()

        // Cache ref ids for this insert
        refMap += (level, ns, selfRef) -> ids
      }

      //      batchSetters.reverse.foreach { setBatchValues =>
      //        insertIndex -= 1
      //        if (insertIndex == -1) {
      //          insertIndex = insertsCount - 1
      //          allIds ++= idLists.flatten
      //        }
      //
      //        println("------------------------------")
      //        println(insertStmts(insertIndex))
      //
      //        val ps = pss(insertIndex)
      //        // Set values of prepared statement for this insert
      //        setBatchValues(ps, idLists)
      //        ps.executeBatch()
      //        val resultSet = ps.getGeneratedKeys // is empty if no nested data
      //        ids.clear()
      //        while (resultSet.next()) {
      //          ids.addOne(resultSet.getLong(1))
      //        }
      //
      //        idLists(insertIndex) = ids.toList
      //        println("ids    : " + ids)
      //        println("idLists: " + idLists.toList)
      //      }
      //
      //      allIds.addAll(idLists.flatten)
      //      println("-----")
      //      println("allIds : " + allIds)
      //
      //      pss.foreach(_.close())

      // transact all
      sqlConn.commit()

      // Tx entity not implemented for sql-jdbc
      //      TxReport(0, allIds.toList)
      TxReport(0, ids.toList) // Use only top-level ids
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

  //  def transact_sync1(data: Data): TxReport = {
  //    val (insertStmts, batchSetters) = data
  //
  //    val insertsCount = insertStmts.length
  //    var insertIndex  = insertsCount
  //    val pss          = insertStmts.map(sqlConn.prepareStatement(_, Statement.RETURN_GENERATED_KEYS))
  //    val ids          = new ListBuffer[Long]
  //    val idLists      = new Array[List[Long]](insertsCount)
  //    val allIds       = new ListBuffer[Long]
  //
  //    println("insertsCount      : " + insertsCount)
  //    println("batchSetters.count: " + batchSetters.length)
  //
  //    try {
  //      // Atomic transaction of all statements
  //      sqlConn.setAutoCommit(false)
  //
  ////      batchSetters.foreach { setBatchValues =>
  //
  //      // Insert in reverse order to have ref ids for foreign keys and join tables
  //      batchSetters.reverse.foreach { setBatchValues =>
  //        insertIndex -= 1
  //        if (insertIndex == -1) {
  //          insertIndex = insertsCount - 1
  //          allIds ++= idLists.flatten
  //        }
  //
  //        println("------------------------------")
  //        println(insertStmts(insertIndex))
  //
  //        val ps = pss(insertIndex)
  //        // Set values of prepared statement for this insert
  //        setBatchValues(ps, idLists)
  //        ps.executeBatch()
  //        val resultSet = ps.getGeneratedKeys // is empty if no nested data
  //        ids.clear()
  //        while (resultSet.next()) {
  //          ids.addOne(resultSet.getLong(1))
  //        }
  //
  //        idLists(insertIndex) = ids.toList
  //        println("ids    : " + ids)
  //        println("idLists: " + idLists.toList)
  //      }
  //
  //      allIds.addAll(idLists.flatten)
  //      println("-----")
  //      println("allIds : " + allIds)
  //
  //      pss.foreach(_.close())
  //
  //      // transact all
  //      sqlConn.commit()
  //
  //      // Tx entity not implemented for sql-jdbc
  //      TxReport(0, allIds.toList)
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


  //  override def transact_sync2(ps: PreparedStmt): TxReport = {
  //    // Statements for each referenced namespace, from innermost to top
  //    val stmts: List[(String, (PreparedStmt, List[Long]) => Unit)] = ???
  //    //
  //    //    val data: (List[String], List[(PreparedStmt, List[Long]) => Unit]) = ???
  //    //    val (stmts, pss)                                                   = data
  //
  //
  //    var refIds = List.empty[Long]
  //    var accIds = List.empty[Long]
  //    try {
  //      // Atomic transaction of all statements
  //      sqlConn.setAutoCommit(false)
  //      stmts.foreach { case (stmt, setData) =>
  //        val ps = sqlConn.prepareStatement(stmt, Statement.RETURN_GENERATED_KEYS)
  //        setData(ps, refIds)
  //        if (ps.executeUpdate() == 0) {
  //          throw ExecutionError("Couldn't save row for statement: " + stmt)
  //        }
  //        val resultSet = ps.getGeneratedKeys
  //        var ids       = List.empty[Long]
  //        while (resultSet.next()) {
  //          ids = ids :+ resultSet.getLong(1)
  //        }
  //        refIds = ids
  //        accIds = ids ++ accIds
  //        ps.close()
  //      }
  //      // transact all
  //      sqlConn.commit()
  //
  //      // Tx entity not implemented for sql-jdbc
  //      TxReport(0, refIds)
  //    } catch {
  //      case e: SQLException =>
  //        try {
  //          sqlConn.rollback()
  //          throw ExecutionError(s"Successfully rolled back unsuccessful save with error: " + e)
  //        } catch {
  //          case e: SQLException =>
  //            throw ExecutionError("Couldn't roll back unsuccessful save: " + e)
  //          case NonFatal(e)     =>
  //            throw ExecutionError("Unexpected error from rollback attempt:" + e)
  //        }
  //      case NonFatal(e)     =>
  //        throw ExecutionError("Unexpected save error: " + e)
  //    }
  //  }


  /*
  String sql = "insert into employee (name, city, phone) values (?, ?, ?)";
  Connection connection = new getConnection();
  PreparedStatement ps = connection.prepareStatement(sql);
  final int batchSize = 1000;
  int count = 0;
  for (Employee employee: employees) {
    ps.setString(1, employee.getName());
    ps.setString(2, employee.getCity());
    ps.setString(3, employee.getPhone());
    ps.addBatch();
    if(++count % batchSize == 0) {
      ps.executeBatch();
    }
  }
  ps.executeBatch(); // insert remaining records
  ps.close();
  connection.close();
   */


  def close: Unit = sqlConn.close()


}