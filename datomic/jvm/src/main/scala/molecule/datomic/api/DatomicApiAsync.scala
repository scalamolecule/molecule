package molecule.datomic.api

import molecule.boilerplate.ast.Model._
import molecule.core.action.Insert
import molecule.core.api.{ApiAsync, Connection, TxReport}
import molecule.core.transaction.{DeleteExtraction, InsertExtraction, SaveExtraction, UpdateExtraction}
import molecule.core.util.FutureUtils
import molecule.datomic.action._
import molecule.datomic.facade.DatomicConn_JVM
import molecule.datomic.marshalling.DatomicRpcJVM.Data
import molecule.datomic.query.{DatomicQueryResolveCursor, DatomicQueryResolveOffset}
import molecule.datomic.transaction.{Delete_stmts, Insert_stmts, Save_stmts, Update_stmts}
import scala.concurrent.{ExecutionContext, Future}


trait DatomicApiAsync extends DatomicAsyncApiBase with ApiAsync with FutureUtils {

  implicit class datomicQueryApiAsync[Tpl](q: DatomicQuery[Tpl]) extends QueryApi[Tpl] {
    override def get(implicit conn: Connection, ec: ExecutionContext): Future[List[Tpl]] = {
      DatomicQueryResolveOffset[Tpl](q.elements, q.limit, None)
        .getListFromOffset_async(conn.asInstanceOf[DatomicConn_JVM], ec).map(_._1)
    }
    override def subscribe(callback: List[Tpl] => Unit)(implicit conn: Connection): Unit = {
      DatomicQueryResolveOffset[Tpl](q.elements, q.limit, None)
        .subscribe(conn.asInstanceOf[DatomicConn_JVM], callback)
    }
    override def inspect(implicit conn: Connection, ec: ExecutionContext): Future[Unit] =
      printInspectQuery("QUERY", q.elements)
  }

  implicit class datomicQueryOffsetApiAsync[Tpl](q: DatomicQueryOffset[Tpl]) extends QueryOffsetApi[Tpl] {
    override def get(implicit conn: Connection, ec: ExecutionContext): Future[(List[Tpl], Int, Boolean)] = {
      DatomicQueryResolveOffset[Tpl](q.elements, q.limit, Some(q.offset))
        .getListFromOffset_async(conn.asInstanceOf[DatomicConn_JVM], ec)
    }
    override def inspect(implicit conn: Connection, ec: ExecutionContext): Future[Unit] =
      printInspectQuery("QUERY (offset)", q.elements)
  }

  implicit class datomicQueryCursorApiAsync[Tpl](q: DatomicQueryCursor[Tpl]) extends QueryCursorApi[Tpl] {
    override def get(implicit conn: Connection, ec: ExecutionContext): Future[(List[Tpl], String, Boolean)] = {
      DatomicQueryResolveCursor[Tpl](q.elements, q.limit, Some(q.cursor))
        .getListFromCursor_async(conn.asInstanceOf[DatomicConn_JVM], ec)
    }
    override def inspect(implicit conn: Connection, ec: ExecutionContext): Future[Unit] =
      printInspectQuery("QUERY (cursor)", q.elements)
  }


  implicit class datomicSaveApiAsync[Tpl](save: DatomicSave) extends Transaction {
    override def transact(implicit conn: Connection, ec: ExecutionContext): Future[TxReport] = tryFuture {
      conn.asInstanceOf[DatomicConn_JVM].transact_async(getStmts)
    }

    override def inspect(implicit conn: Connection, ec: ExecutionContext): Future[Unit] =
      printInspectTx("SAVE", save.elements, getStmts)

    private def getStmts: Data =
      (new SaveExtraction() with Save_stmts).getStmts(save.elements)
  }


  implicit class datomicInsertApiAsync[Tpl](insert0: Insert) extends Transaction {
    val insert = insert0.asInstanceOf[DatomicInsert_JVM]
    override def transact(implicit conn: Connection, ec: ExecutionContext): Future[TxReport] = tryFuture {
      conn.asInstanceOf[DatomicConn_JVM].transact_async(getStmts)
    }
    override def inspect(implicit conn: Connection, ec: ExecutionContext): Future[Unit] =
      printInspectTx("INSERT", insert.elements, getStmts)

    private def getStmts: Data =
      (new InsertExtraction with Insert_stmts).getStmts(insert.elements, insert.tpls)
  }


  implicit class datomicUpdateApiAsync[Tpl](update: DatomicUpdate) extends Transaction {
    override def transact(implicit conn0: Connection, ec: ExecutionContext): Future[TxReport] = tryFuture {
      val conn = conn0.asInstanceOf[DatomicConn_JVM]
      conn.transact_async(getStmts(conn))
    }

    override def inspect(implicit conn0: Connection, ec: ExecutionContext): Future[Unit] =
      printInspectTx("UPDATE", update.elements, getStmts(conn0.asInstanceOf[DatomicConn_JVM]))

    private def getStmts(conn: DatomicConn_JVM): Data =
      (new UpdateExtraction(conn.proxy.uniqueAttrs, update.isUpsert) with Update_stmts)
        .getStmts(conn, update.elements)
  }


  implicit class datomicDeleteApiAsync[Tpl](delete: DatomicDelete) extends Transaction {
    override def transact(implicit conn0: Connection, ec: ExecutionContext): Future[TxReport] = tryFuture {
      val conn = conn0.asInstanceOf[DatomicConn_JVM]
      conn.transact_async(getStmts(conn))
    }

    override def inspect(implicit conn0: Connection, ec: ExecutionContext): Future[Unit] =
      printInspectTx("DELETE", delete.elements, getStmts(conn0.asInstanceOf[DatomicConn_JVM]))

    private def getStmts(conn: DatomicConn_JVM): Data =
      (new DeleteExtraction with Delete_stmts).getStmtsData(conn, delete.elements)
  }


  private def printInspectTx(label: String, elements: List[Element], stmts: Data)
                            (implicit ec: ExecutionContext): Future[Unit] = {
    Future(printInspect(label, elements, stmts.toArray().toList.mkString("\n")))
  }
}
