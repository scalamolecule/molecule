package molecule.datomic.api

import molecule.core.action.Insert
import molecule.core.api.{ApiAsync, Connection, TxReport}
import molecule.core.transaction.{DeleteExtraction, InsertExtraction, SaveExtraction, UpdateExtraction}
import molecule.core.util.FutureUtils
import molecule.datomic.action._
import molecule.datomic.facade.DatomicConn_JVM
import molecule.datomic.query.{DatomicQueryResolveCursor, DatomicQueryResolveOffset}
import molecule.datomic.transaction.{Delete_stmts, Insert_stmts, Save_stmts, Update_stmts}
import scala.concurrent.{ExecutionContext, Future}

trait DatomicApiAsync extends ApiAsync {

  implicit class datomicQueryApiAsync[Tpl](q: DatomicQuery[Tpl]) extends QueryApi[Tpl] {
    override def get(implicit conn: Connection, ec: ExecutionContext): Future[List[Tpl]] = {
      DatomicQueryResolveOffset[Tpl](q.elements, q.limit, None)
        .getListFromOffset_async(conn.asInstanceOf[DatomicConn_JVM], ec).map(_._1)
    }
    override def inspect(implicit conn: Connection, ec: ExecutionContext): Future[Unit] = ???
  }

  implicit class datomicQueryOffsetApiAsync[Tpl](q: DatomicQueryOffset[Tpl]) extends QueryOffsetApi[Tpl] {
    override def get(implicit conn: Connection, ec: ExecutionContext): Future[(List[Tpl], Int, Boolean)] = {
      DatomicQueryResolveOffset[Tpl](q.elements, q.limit, Some(q.offset))
        .getListFromOffset_async(conn.asInstanceOf[DatomicConn_JVM], ec)
    }
    override def inspect(implicit conn: Connection, ec: ExecutionContext): Future[Unit] = ???
  }

  implicit class datomicQueryCursorApiAsync[Tpl](q: DatomicQueryCursor[Tpl]) extends QueryCursorApi[Tpl] {
    override def get(implicit conn: Connection, ec: ExecutionContext): Future[(List[Tpl], String, Boolean)] = {
      DatomicQueryResolveCursor[Tpl](q.elements, q.limit, Some(q.cursor))
        .getListFromCursor_async(conn.asInstanceOf[DatomicConn_JVM], ec)
    }
    override def inspect(implicit conn: Connection, ec: ExecutionContext): Future[Unit] = ???
  }


  implicit class datomicSaveApiAsync[Tpl](save: DatomicSave) extends SaveApi with FutureUtils {
    override def transact(implicit conn: Connection, ec: ExecutionContext): Future[TxReport] = tryFuture {
      val stmts = (new SaveExtraction() with Save_stmts).getStmts(save.elements)
      conn.asInstanceOf[DatomicConn_JVM].transact_async(stmts)
    }
  }

  implicit class datomicInsertApiAsync[Tpl](insert0: Insert) extends InsertApi with FutureUtils {
    val insert = insert0.asInstanceOf[DatomicInsert_JVM]
    override def transact(implicit conn: Connection, ec: ExecutionContext): Future[TxReport] = tryFuture {
      val stmts = (new InsertExtraction with Insert_stmts).getStmts(insert.elements, insert.tpls)
      conn.asInstanceOf[DatomicConn_JVM].transact_async(stmts)
    }
  }

  implicit class datomicUpdateApiAsync[Tpl](update: DatomicUpdate) extends UpdateApi with FutureUtils {
    override def transact(implicit conn0: Connection, ec: ExecutionContext): Future[TxReport] = tryFuture {
      val conn  = conn0.asInstanceOf[DatomicConn_JVM]
      val stmts = (new UpdateExtraction(conn.proxy.uniqueAttrs, update.isUpsert) with Update_stmts)
        .getStmts(conn, update.elements)
      conn.transact_async(stmts)
    }
  }

  implicit class datomicDeleteApiAsync[Tpl](delete: DatomicDelete) extends DeleteApi with FutureUtils {
    override def transact(implicit conn0: Connection, ec: ExecutionContext): Future[TxReport] = tryFuture {
      val conn  = conn0.asInstanceOf[DatomicConn_JVM]
      val stmts = (new DeleteExtraction with Delete_stmts).getStmtsData(conn, delete.elements)
      conn.transact_async(stmts)
    }
  }
}
