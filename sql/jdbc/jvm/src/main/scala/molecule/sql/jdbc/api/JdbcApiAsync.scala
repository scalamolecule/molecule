package molecule.sql.jdbc.api

import molecule.core.action._
import molecule.core.api.{ApiAsync2, Connection, TxReport}
import molecule.core.util.FutureUtils
import molecule.sql.jdbc.subscription.SubscriptionStarter
import scala.concurrent.{ExecutionContext, Future}


trait JdbcApiAsync
  extends JVMJdbcApiBase
//    with ApiAsyncProxy
//    with ApiAsync
    with ApiAsync2
//    with JdbcApiSync
    with SubscriptionStarter
    with JdbcAsyncApiBase
    with FutureUtils {

//  //  implicit class jdbcQueryApiAsync[Tpl](q: Query[Tpl]) extends QueryApi[Tpl] {
//  implicit class jdbcQueryApiAsync[Tpl](q: Query[Tpl]) extends coreTestsQueryApiAsync[Tpl](q) {
//    val sync = new jdbcQueryApiSync[Tpl](q)
//    override def get(implicit conn: Connection, ec: ExecutionContext): Future[List[Tpl]] = {
//      Future(sync.get)
//    }
//    override def subscribe(callback: List[Tpl] => Unit)(implicit conn: Connection): Unit = {
//      sync.subscribe(callback)
//    }
//    override def inspect(implicit conn: Connection, ec: ExecutionContext): Future[Unit] = {
//      Future(sync.inspect)
//    }
//  }
//
//  //  implicit class jdbcQueryOffsetApiAsync[Tpl](q: QueryOffset[Tpl]) extends QueryOffsetApi[Tpl] {
//  implicit class jdbcQueryOffsetApiAsync[Tpl](q: QueryOffset[Tpl]) extends coreTestsQueryOffsetApiAsync[Tpl](q) {
//    val sync = new jdbcQueryOffsetApiSync[Tpl](q)
//    override def get(implicit conn: Connection, ec: ExecutionContext): Future[(List[Tpl], Int, Boolean)] = {
//      Future(sync.get)
//    }
//    override def inspect(implicit conn: Connection, ec: ExecutionContext): Future[Unit] = {
//      Future(sync.inspect)
//    }
//  }
//
//
//  //  implicit class jdbcQueryCursorApiAsync[Tpl](q: QueryCursor[Tpl]) extends QueryCursorApi[Tpl] {
//  implicit class jdbcQueryCursorApiAsync[Tpl](q: QueryCursor[Tpl]) extends coreTestsQueryCursorApiAsync[Tpl](q) {
//    val sync = new jdbcQueryCursorApiSync[Tpl](q)
//    override def get(implicit conn: Connection, ec: ExecutionContext): Future[(List[Tpl], String, Boolean)] = {
//      Future(sync.get)
//    }
//    override def inspect(implicit conn: Connection, ec: ExecutionContext): Future[Unit] = {
//      Future(sync.inspect)
//    }
//  }
//
//  //  implicit class jdbcSaveApiAsync[Tpl](save: Save) extends SaveTransaction {
//  implicit class jdbcSaveApiAsync[Tpl](save: Save) extends coreTestsSaveApiAsync[Tpl](save) {
//    val sync = new jdbcSaveApiSync[Tpl](save)
//    override def transact(implicit conn: Connection, ec: ExecutionContext): Future[TxReport] = {
//      Future(sync.transact)
//    }
//    override def inspect(implicit conn: Connection, ec: ExecutionContext): Future[Unit] = {
//      Future(sync.inspect)
//    }
//    override def validate(implicit conn: Connection): Map[String, Seq[String]] = {
//      sync.validate
//    }
//  }


  override def insert_transact(insert: Insert)
                              (implicit conn: Connection, ec: ExecutionContext): Future[TxReport] = {
    println("jdbc insert_transact")
    Future(new JdbcApiSync.jdbcInsertApiSync(insert).transact)
  }

//      class jdbcInsertApiAsync[Tpl](insert: Insert)  {
////  implicit class jdbcInsertApiAsync[Tpl](insert: Insert) extends coreTestsInsertApiAsync[Tpl](insert) {
//    val sync = new JdbcApiSync.jdbcInsertApiSync(insert)
//     override def transact(implicit conn: Connection, ec: ExecutionContext): Future[TxReport] = {
//      Future(sync.transact)
//    }
//    override def inspect(implicit conn: Connection, ec: ExecutionContext): Future[Unit] = {
//      Future(sync.inspect)
//    }
//    override def validate(implicit conn: Connection): Seq[(Int, Seq[InsertError])] = {
//      sync.validate
//    }
//  }


  //  implicit class jdbcUpdateApiAsync[Tpl](update: Update) extends UpdateTransaction {
  //    override def transact(implicit conn0: Connection, ec: ExecutionContext): Future[TxReport] = try {
  //      val errors = validate
  //      if (errors.isEmpty) {
  //        val conn = conn0.asInstanceOf[JdbcConn_JVM]
  //        conn.transact_async(getStmts(conn))
  //      } else {
  //        Future.failed(ValidationErrors(errors))
  //      }
  //    } catch {
  //      case e: Throwable => Future.failed(e)
  //    }
  //
  //    override def inspect(implicit conn0: Connection, ec: ExecutionContext): Future[Unit] = {
  //      printInspectTx("UPDATE", update.elements, getStmts(conn0.asInstanceOf[JdbcConn_JVM]))
  //    }
  //
  //    private def getStmts(conn: JdbcConn_JVM): PreparedStmt = {
  //      (new UpdateExtraction(conn.proxy.schema.uniqueAttrs, update.isUpsert) with Update_stmts {
  //        override protected val ps: PreparedStmt = ???
  //      })
  //        .getStmts(conn, update.elements)
  //    }
  //
  //    override def validate(implicit conn: Connection): Map[String, Seq[String]] = {
  //      validateUpdate(conn, update.elements)
  //    }
  //  }
  //
  //
  //  implicit class jdbcDeleteApiAsync[Tpl](delete: Delete) extends DeleteTransaction {
  //    override def transact(implicit conn0: Connection, ec: ExecutionContext): Future[TxReport] = try {
  //      val conn = conn0.asInstanceOf[JdbcConn_JVM]
  //      conn.transact_async(getStmts(conn))
  //    } catch {
  //      case e: Throwable => Future.failed(e)
  //    }
  //
  //    override def inspect(implicit conn0: Connection, ec: ExecutionContext): Future[Unit] = {
  //      printInspectTx("DELETE", delete.elements, getStmts(conn0.asInstanceOf[JdbcConn_JVM]))
  //    }
  //
  //    private def getStmts(conn: JdbcConn_JVM): PreparedStmt = {
  //      (new DeleteExtraction with Delete_stmts {
  //        override protected val ps: PreparedStmt = ???
  //      }).getStmtsData(conn, delete.elements)
  //    }
  //  }
//
//
//  private def printInspectTx(label: String, elements: List[Element], data: Data)
//                            (implicit ec: ExecutionContext): Future[Unit] = {
//    //    Future(printInspect(label, elements, stmts.toArray().toList.mkString("\n")))
//    ???
//  }
}
