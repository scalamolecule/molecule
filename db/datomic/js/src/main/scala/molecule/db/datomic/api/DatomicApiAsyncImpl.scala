package molecule.db.datomic.api

import boopickle.Default._
import molecule.core.action.Insert
import molecule.core.api.{ApiAsync, Connection, FutureUtils, TxReport}
import molecule.core.marshalling.serialize.PickleTpls
import molecule.db.datomic.action._
import molecule.db.datomic.facade.DatomicConn_JS
import scala.concurrent.{ExecutionContext, Future}

trait DatomicApiAsyncImpl extends ApiAsync with FutureUtils {

  implicit class datomicDelete2api[Tpl](delete: DatomicDeleteImpl) extends DeleteApi with FutureUtils {
    override def transact(implicit conn0: Connection, ec: ExecutionContext): Future[TxReport] = {
      val conn = conn0.asInstanceOf[DatomicConn_JS]
      conn.rpc.delete(conn.proxy, delete.elements).future
    }
  }


  implicit class datomicInsert2api[Tpl](insert0: Insert) extends InsertApi with FutureUtils {
    val insert = insert0.asInstanceOf[DatomicInsertImpl_JS]
    override def transact(implicit conn0: Connection, ec: ExecutionContext): Future[TxReport] = tryFuture {
      val conn                      = conn0.asInstanceOf[DatomicConn_JS]
      val (tplElements, txElements) = splitElements(insert.elements)
      val tplsSerialized            = PickleTpls(tplElements, Right(insert.tpls), true).pickle
      conn.rpc.insert(conn.proxy, tplElements, tplsSerialized, txElements).future
    }
  }


  implicit class datomicQuery2api[Tpl](q: DatomicQueryImpl[Tpl]) extends QueryApi[Tpl] {
    override def get(implicit conn0: Connection, ec: ExecutionContext): Future[List[Tpl]] = {
      val conn = conn0.asInstanceOf[DatomicConn_JS]
      conn.rpc.query[Tpl](conn.proxy, q.elements).future
    }
    override def inspect(implicit conn: Connection, ec: ExecutionContext): Future[Unit] = ???
  }

  implicit class datomicQueryOffset2api[Tpl](q: DatomicQueryImplOffset[Tpl]) extends QueryOffsetApi[Tpl] {
    override def get(implicit conn0: Connection, ec: ExecutionContext): Future[(List[Tpl], Int, Boolean)] = {
      val conn = conn0.asInstanceOf[DatomicConn_JS]
      conn.rpc.query[Tpl](conn.proxy, q.elements).future
      ???
    }
    override def inspect(implicit conn: Connection, ec: ExecutionContext): Future[Unit] = ???
  }

  implicit class datomicQueryCursor2api[Tpl](q: DatomicQueryImplCursor[Tpl]) extends QueryCursorApi[Tpl] {
    override def get(implicit conn0: Connection, ec: ExecutionContext): Future[(List[Tpl], String, Boolean)] = {
      val conn = conn0.asInstanceOf[DatomicConn_JS]
      conn.rpc.query[Tpl](conn.proxy, q.elements).future
      ???
    }
    override def inspect(implicit conn: Connection, ec: ExecutionContext): Future[Unit] = ???
  }


  implicit class datomicSave2api[Tpl](save: DatomicSaveImpl) extends SaveApi with FutureUtils {
    override def transact(implicit conn0: Connection, ec: ExecutionContext): Future[TxReport] = {
      val conn = conn0.asInstanceOf[DatomicConn_JS]
      conn.rpc.save(conn.proxy, save.elements).future
    }
  }


  implicit class datomicUpdate2api[Tpl](update: DatomicUpdateImpl) extends UpdateApi with FutureUtils {
    override def transact(implicit conn0: Connection, ec: ExecutionContext): Future[TxReport] = {
      val conn = conn0.asInstanceOf[DatomicConn_JS]
      conn.rpc.update(conn.proxy, update.elements, update.isUpsert).future
    }
  }
}
