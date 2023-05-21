package molecule.core.api

import molecule.base.error._
import molecule.core.action._
import scala.concurrent.{ExecutionContext, Future}

trait ApiAsyncGateway  { self: ApiAsync2 =>

//  implicit class coreTestsQueryApiAsync[Tpl](q: Query[Tpl]) extends QueryApi[Tpl] {
//    override def get(implicit conn: Connection, ec: ExecutionContext): Future[List[Tpl]] = {
//      ???
//    }
//    override def subscribe(callback: List[Tpl] => Unit)(implicit conn: Connection): Unit = {
//      ???
//    }
//    override def inspect(implicit conn: Connection, ec: ExecutionContext): Future[Unit] = {
//      ???
//    }
//  }
//
//  implicit class coreTestsQueryOffsetApiAsync[Tpl](q: QueryOffset[Tpl]) extends QueryOffsetApi[Tpl] {
//    override def get(implicit conn: Connection, ec: ExecutionContext): Future[(List[Tpl], Int, Boolean)] = {
//      ???
//    }
//    override def inspect(implicit conn: Connection, ec: ExecutionContext): Future[Unit] = {
//      ???
//    }
//  }
//
//
//  implicit class coreTestsQueryCursorApiAsync[Tpl](q: QueryCursor[Tpl]) extends QueryCursorApi[Tpl] {
//    override def get(implicit conn: Connection, ec: ExecutionContext): Future[(List[Tpl], String, Boolean)] = {
//      ???
//    }
//    override def inspect(implicit conn: Connection, ec: ExecutionContext): Future[Unit] = {
//      ???
//    }
//  }
//
//
//  implicit class coreTestsSaveApiAsync[Tpl](save: Save) extends SaveTransaction {
//    override def transact(implicit conn: Connection, ec: ExecutionContext): Future[TxReport] = {
//      ???
//    }
//    override def inspect(implicit conn: Connection, ec: ExecutionContext): Future[Unit] = {
//      ???
//    }
//    override def validate(implicit conn: Connection): Map[String, Seq[String]] = {
//      ???
//    }
//  }


  implicit class coreTestsInsertApiAsync[Tpl](insert: Insert) {
    def transact(implicit conn: Connection, ec: ExecutionContext): Future[TxReport] = {
      self.insert_transact(insert)
    }
    def inspect(implicit conn: Connection, ec: ExecutionContext): Future[Unit] = {
      ???
    }
    def validate(implicit conn: Connection): Seq[(Int, Seq[InsertError])] = {
      ???
    }
  }


//  implicit class coreTestsUpdateApiAsync[Tpl](update: Update) extends UpdateTransaction {
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
//  implicit class coreTestsDeleteApiAsync[Tpl](delete: Delete) extends DeleteTransaction {
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


//  private def printInspectTx(label: String, elements: List[Element], data: Data)
//                            (implicit ec: ExecutionContext): Future[Unit] = {
//    //    Future(printInspect(label, elements, stmts.toArray().toList.mkString("\n")))
//    ???
//  }
}
