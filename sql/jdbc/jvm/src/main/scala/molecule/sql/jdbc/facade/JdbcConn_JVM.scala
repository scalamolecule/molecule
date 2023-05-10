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
import molecule.sql.jdbc.transaction.JdbcDataType_JVM
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

  override def transact_sync(data: Data): TxReport = {
    val (stmts, setters) = data

    //    println("stmts  : " + stmts)
    //    println("setters: " + setters)

    var refIds    = List.empty[Long]
    var accIds    = List.empty[Long]
    var stmtIndex = 0
    val stmtCount = stmts.length
    try {
      // Atomic transaction of all statements
      sqlConn.setAutoCommit(false)

      setters.foreach { setValues =>
//        println("A " + stmts(stmtIndex))

        val ps = sqlConn.prepareStatement(stmts(stmtIndex), Statement.RETURN_GENERATED_KEYS)
        setValues(ps, refIds)
        ps.executeUpdate()
        val resultSet = ps.getGeneratedKeys // is empty if no nested data
        var ids       = List.empty[Long]
        while (resultSet.next()) {
          ids = ids :+ resultSet.getLong(1)
        }
//        println("ids: " + ids)

        refIds = ids
        accIds = ids ++ accIds
        ps.close()
        if (stmtIndex == stmtCount) {
          stmtIndex = 0
        } else {
          stmtIndex += 1
        }
      }

      // transact all
      sqlConn.commit()


      //      val stmt = "insert into Ns(int) values (?)"
      //
      //      val ps = sqlConn.prepareStatement(stmt, Statement.RETURN_GENERATED_KEYS)
      //
      //      ps.setInt(1, 3)
      //      ps.execute()
      //
      //      val resultSet = ps.getGeneratedKeys // is empty if no nested data
      //      var ids       = List.empty[Long]
      //      while (resultSet.next()) {
      //        ids = ids :+ resultSet.getLong(1)
      //      }
      //      println("ids: " + ids)


      // Tx entity not implemented for sql-jdbc
      TxReport(0, refIds)
    } catch {
      case e: SQLException =>
        try {
          sqlConn.rollback()
          throw ExecutionError(s"Successfully rolled back unsuccessful save with error: " + e)
        } catch {
          case e: SQLException =>
            throw ExecutionError("Couldn't roll back unsuccessful save: " + e)
          //          case NonFatal(e)     =>
          //            throw ExecutionError("Unexpected error from rollback attempt: " + e)
        }
      case NonFatal(e)     =>
        throw ExecutionError("Unexpected save error: " + e)
    }
  }


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