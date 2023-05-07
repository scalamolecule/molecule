package molecule.datalog.datomic.api

import molecule.base.error._
import molecule.boilerplate.ast.Model._
import molecule.core.action._
import molecule.core.api.{ApiAsync, Connection, TxReport}
import molecule.core.marshalling.ConnProxy
import molecule.core.transaction.{DeleteExtraction, InsertExtraction, SaveExtraction, UpdateExtraction}
import molecule.core.util.FutureUtils
import molecule.core.validation.ModelValidation
import molecule.core.validation.insert.InsertValidation
import molecule.datalog.datomic.facade.DatomicConn_JVM
import molecule.datalog.datomic.subscription.SubscriptionStarter
import molecule.datalog.datomic.transaction.{Delete_stmts, Insert_stmts, Save_stmts, Update_stmts}
import molecule.datalog.datomic.marshalling.DatomicRpcJVM.Data
import molecule.datalog.datomic.query.{DatomicQueryResolveCursor, DatomicQueryResolveOffset}
import scala.concurrent.{ExecutionContext, Future}


trait DatomicApiAsync
  extends JVMDatomicApiBase
    with SubscriptionStarter
    with DatomicAsyncApiBase
    with ApiAsync
    with FutureUtils {

  implicit class datomicQueryApiAsync[Tpl](q: Query[Tpl]) extends QueryApi[Tpl] {
    override def get(implicit conn: Connection, ec: ExecutionContext): Future[List[Tpl]] = {
      DatomicQueryResolveOffset[Tpl](q.elements, q.limit, None, q.dbView)
        .getListFromOffset_async(conn.asInstanceOf[DatomicConn_JVM], ec).map(_._1)
    }

    override def subscribe(callback: List[Tpl] => Unit)(implicit conn: Connection): Unit = {
      val datomicConn = conn.asInstanceOf[DatomicConn_JVM]
      DatomicQueryResolveOffset[Tpl](q.elements, q.limit, None, q.dbView)
        .subscribe(datomicConn, getWatcher(datomicConn), callback)
    }

    override def inspect(implicit conn: Connection, ec: ExecutionContext): Future[Unit] = {
      printInspectQuery("QUERY", q.elements)
    }
  }

  implicit class datomicQueryOffsetApiAsync[Tpl](q: QueryOffset[Tpl]) extends QueryOffsetApi[Tpl] {
    override def get(implicit conn: Connection, ec: ExecutionContext): Future[(List[Tpl], Int, Boolean)] = {
      DatomicQueryResolveOffset[Tpl](q.elements, q.limit, Some(q.offset), q.dbView)
        .getListFromOffset_async(conn.asInstanceOf[DatomicConn_JVM], ec)
    }

    override def inspect(implicit conn: Connection, ec: ExecutionContext): Future[Unit] = {
      printInspectQuery("QUERY (offset)", q.elements)
    }
  }


  implicit class datomicQueryCursorApiAsync[Tpl](q: QueryCursor[Tpl]) extends QueryCursorApi[Tpl] {
    override def get(implicit conn: Connection, ec: ExecutionContext): Future[(List[Tpl], String, Boolean)] = {
      DatomicQueryResolveCursor[Tpl](q.elements, q.limit, Some(q.cursor), q.dbView)
        .getListFromCursor_async(conn.asInstanceOf[DatomicConn_JVM], ec)
    }

    override def inspect(implicit conn: Connection, ec: ExecutionContext): Future[Unit] = {
      printInspectQuery("QUERY (cursor)", q.elements)
    }
  }


  implicit class datomicSaveApiAsync[Tpl](save: Save) extends SaveTransaction {
    override def transact(implicit conn: Connection, ec: ExecutionContext): Future[TxReport] = try {
      val errors = validate
      if (errors.isEmpty) {
        conn.asInstanceOf[DatomicConn_JVM].transact_async(getStmts)
      } else {
        Future.failed(ValidationErrors(errors))
      }
    } catch {
      case e: Throwable => Future.failed(e)
    }

    override def inspect(implicit conn: Connection, ec: ExecutionContext): Future[Unit] = {
      printInspectTx("SAVE", save.elements, getStmts)
    }

    private def getStmts: Data = {
      (new SaveExtraction() with Save_stmts).getStmts(save.elements)
    }

    override def validate(implicit conn: Connection): Map[String, Seq[String]] = {
      val proxy = conn.proxy
      ModelValidation(proxy.nsMap, proxy.attrMap, "save").validate(save.elements)
    }
  }


  implicit class datomicInsertApiAsync[Tpl](insert0: Insert) extends InsertTransaction {
    val insert = insert0.asInstanceOf[InsertTpls]
    override def transact(implicit conn: Connection, ec: ExecutionContext): Future[TxReport] = try {
      val errors = validate
      if (errors.isEmpty) {
        conn.asInstanceOf[DatomicConn_JVM].transact_async(getStmts(conn.proxy))
      } else {
        Future.failed(InsertErrors(errors))
      }
    } catch {
      case e: Throwable => Future.failed(e)
    }

    override def inspect(implicit conn: Connection, ec: ExecutionContext): Future[Unit] = {
      printInspectTx("INSERT", insert.elements, getStmts(conn.proxy))
    }

    private def getStmts(proxy: ConnProxy): Data = {
      (new InsertExtraction with Insert_stmts)
        .getStmts(proxy.nsMap, insert.elements, insert.tpls)
    }

    override def validate(implicit conn: Connection): Seq[(Int, Seq[InsertError])] = {
      InsertValidation.validate(conn, insert.elements, insert.tpls)
    }
  }


  implicit class datomicUpdateApiAsync[Tpl](update: Update) extends UpdateTransaction {
    override def transact(implicit conn0: Connection, ec: ExecutionContext): Future[TxReport] = try {
      val errors = validate
      if (errors.isEmpty) {
        val conn = conn0.asInstanceOf[DatomicConn_JVM]
        conn.transact_async(getStmts(conn))
      } else {
        Future.failed(ValidationErrors(errors))
      }
    } catch {
      case e: Throwable => Future.failed(e)
    }

    override def inspect(implicit conn0: Connection, ec: ExecutionContext): Future[Unit] = {
      printInspectTx("UPDATE", update.elements, getStmts(conn0.asInstanceOf[DatomicConn_JVM]))
    }

    private def getStmts(conn: DatomicConn_JVM): Data = {
      (new UpdateExtraction(conn.proxy.uniqueAttrs, update.isUpsert) with Update_stmts)
        .getStmts(conn, update.elements)
    }

    override def validate(implicit conn: Connection): Map[String, Seq[String]] = {
      validateUpdate(conn, update.elements)
    }
  }


  implicit class datomicDeleteApiAsync[Tpl](delete: Delete) extends DeleteTransaction {
    override def transact(implicit conn0: Connection, ec: ExecutionContext): Future[TxReport] = try {
      val conn = conn0.asInstanceOf[DatomicConn_JVM]
      conn.transact_async(getStmts(conn))
    } catch {
      case e: Throwable => Future.failed(e)
    }

    override def inspect(implicit conn0: Connection, ec: ExecutionContext): Future[Unit] = {
      printInspectTx("DELETE", delete.elements, getStmts(conn0.asInstanceOf[DatomicConn_JVM]))
    }

    private def getStmts(conn: DatomicConn_JVM): Data = {
      (new DeleteExtraction with Delete_stmts).getStmtsData(conn, delete.elements)
    }
  }


  private def printInspectTx(label: String, elements: List[Element], stmts: Data)
                            (implicit ec: ExecutionContext): Future[Unit] = {
    Future(printInspect(label, elements, stmts.toArray().toList.mkString("\n")))
  }
}
