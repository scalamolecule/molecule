package molecule.db.datomic.api

import molecule.core.action.Insert
import molecule.core.api.{ApiAsync, Connection, FutureUtils, TxReport}
import molecule.core.transaction.{DeleteExtraction, InsertExtraction, SaveExtraction, UpdateExtraction}
import molecule.db.datomic.action._
import molecule.db.datomic.facade.DatomicConn_JVM
import molecule.db.datomic.query._
import molecule.db.datomic.transaction.{Delete_stmts, Insert_stmts, Save_stmts, Update_stmts}
import scala.concurrent.{ExecutionContext, Future}

trait DatomicApiAsyncImpl extends ApiAsync {

  implicit class datomicDelete2api[Tpl](delete: DatomicDeleteImpl) extends DeleteApi with FutureUtils {
    override def transact(implicit conn0: Connection, ec: ExecutionContext): Future[TxReport] = tryFuture {
      val conn  = conn0.asInstanceOf[DatomicConn_JVM]
      val stmts = (new DeleteExtraction with Delete_stmts).getStmtsData(conn, delete.elements)
      conn.transact_async(stmts)
    }
  }


  implicit class datomicInsert2api[Tpl](insert0: Insert) extends InsertApi with FutureUtils {
    val insert = insert0.asInstanceOf[DatomicInsertImpl_JVM]
    override def transact(implicit conn: Connection, ec: ExecutionContext): Future[TxReport] = tryFuture {
      val stmts = (new InsertExtraction with Insert_stmts).getStmts(insert.elements, insert.tpls)
      conn.asInstanceOf[DatomicConn_JVM].transact_async(stmts)
    }
  }


  implicit class datomicQuery2api[Tpl](q: DatomicQueryImpl[Tpl]) extends QueryApi[Tpl] {
    override def get(implicit conn: Connection, ec: ExecutionContext): Future[List[Tpl]] = {
      DatomicQueryResolveOffset[Tpl](q.elements, q.limit, None)
        .getListFromOffset_async(conn.asInstanceOf[DatomicConn_JVM], ec).map(_._1)
    }
    override def inspect(implicit conn: Connection, ec: ExecutionContext): Future[Unit] = ???
  }

  implicit class datomicQueryOffset2api[Tpl](q: DatomicQueryImplOffset[Tpl]) extends QueryOffsetApi[Tpl] {
    override def get(implicit conn: Connection, ec: ExecutionContext): Future[(List[Tpl], Int, Boolean)] = {
      DatomicQueryResolveOffset[Tpl](q.elements, q.limit, Some(q.offset))
        .getListFromOffset_async(conn.asInstanceOf[DatomicConn_JVM], ec)
    }
    override def inspect(implicit conn: Connection, ec: ExecutionContext): Future[Unit] = ???
  }

  implicit class datomicQueryCursor2api[Tpl](q: DatomicQueryImplCursor[Tpl]) extends QueryCursorApi[Tpl] {
    override def get(implicit conn: Connection, ec: ExecutionContext): Future[(List[Tpl], String, Boolean)] = {
      DatomicQueryResolveCursor[Tpl](q.elements, q.limit, Some(q.cursor))
        .getListFromCursor_async(conn.asInstanceOf[DatomicConn_JVM], ec)
    }
    override def inspect(implicit conn: Connection, ec: ExecutionContext): Future[Unit] = ???
  }


  implicit class datomicSave2api[Tpl](save: DatomicSaveImpl) extends SaveApi with FutureUtils {
    override def transact(implicit conn: Connection, ec: ExecutionContext): Future[TxReport] = tryFuture {
      val stmts = (new SaveExtraction() with Save_stmts).getStmts(save.elements)
      conn.asInstanceOf[DatomicConn_JVM].transact_async(stmts)
    }
  }


  implicit class datomicUpdate2api[Tpl](update: DatomicUpdateImpl) extends UpdateApi with FutureUtils {
    override def transact(implicit conn0: Connection, ec: ExecutionContext): Future[TxReport] = tryFuture {
      val conn  = conn0.asInstanceOf[DatomicConn_JVM]
      val stmts = (new UpdateExtraction(conn.proxy.uniqueAttrs, update.isUpsert) with Update_stmts)
        .getStmts(conn, update.elements)
      conn.transact_async(stmts)
    }
  }
}
