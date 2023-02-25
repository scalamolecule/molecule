package molecule.db.datomic.api

import boopickle.Default._
import molecule.core.action.Insert
import molecule.core.api.{ApiAsync, Connection, TxReport}
import molecule.core.marshalling.serialize.PickleTpls
import molecule.core.util.FutureUtils
import molecule.db.datomic.action._
import molecule.db.datomic.facade.DatomicConn_JS
import scala.concurrent.{ExecutionContext, Future}

trait DatomicApiAsync extends ApiAsync with FutureUtils {

  implicit class datomicQueryApiAsync[Tpl](q: DatomicQuery[Tpl]) extends QueryApi[Tpl] {
    override def get(implicit conn0: Connection, ec: ExecutionContext): Future[List[Tpl]] = {
      val conn = conn0.asInstanceOf[DatomicConn_JS]
      conn.rpc.query[Tpl](conn.proxy, q.elements, q.limit).future
    }
    override def inspect(implicit conn: Connection, ec: ExecutionContext): Future[Unit] = ???
  }

  implicit class datomicQueryOffsetApiAsync[Tpl](q: DatomicQueryOffset[Tpl]) extends QueryOffsetApi[Tpl] {
    override def get(implicit conn0: Connection, ec: ExecutionContext): Future[(List[Tpl], Int, Boolean)] = {
      val conn = conn0.asInstanceOf[DatomicConn_JS]
      conn.rpc.queryOffset[Tpl](conn.proxy, q.elements, q.limit, q.offset).future
    }
    override def inspect(implicit conn: Connection, ec: ExecutionContext): Future[Unit] = ???
  }

  implicit class datomicQueryCursorApiAsync[Tpl](q: DatomicQueryCursor[Tpl]) extends QueryCursorApi[Tpl] {
    override def get(implicit conn0: Connection, ec: ExecutionContext): Future[(List[Tpl], String, Boolean)] = {
      val conn = conn0.asInstanceOf[DatomicConn_JS]
      conn.rpc.queryCursor[Tpl](conn.proxy, q.elements, q.limit, q.cursor).future
    }
    override def inspect(implicit conn: Connection, ec: ExecutionContext): Future[Unit] = ???
  }


  implicit class datomicSaveApiAsync[Tpl](save: DatomicSave) extends SaveApi with FutureUtils {
    override def transact(implicit conn0: Connection, ec: ExecutionContext): Future[TxReport] = {
      val conn = conn0.asInstanceOf[DatomicConn_JS]
      conn.rpc.save(conn.proxy, save.elements).future
    }
  }

  implicit class datomicInsertApiAsync[Tpl](insert0: Insert) extends InsertApi with FutureUtils {
    val insert = insert0.asInstanceOf[DatomicInsert_JS]
    override def transact(implicit conn0: Connection, ec: ExecutionContext): Future[TxReport] = tryFuture {
      val conn                      = conn0.asInstanceOf[DatomicConn_JS]
      val (tplElements, txElements) = splitElements(insert.elements)
      val tplsSerialized            = PickleTpls(tplElements, true).pickle(Right(insert.tpls))
      conn.rpc.insert(conn.proxy, tplElements, tplsSerialized, txElements).future
    }
  }

  implicit class datomicUpdateApiAsync[Tpl](update: DatomicUpdate) extends UpdateApi with FutureUtils {
    override def transact(implicit conn0: Connection, ec: ExecutionContext): Future[TxReport] = {
      val conn = conn0.asInstanceOf[DatomicConn_JS]
      conn.rpc.update(conn.proxy, update.elements, update.isUpsert).future
    }
  }

  implicit class datomicDeleteApiAsync[Tpl](delete: DatomicDelete) extends DeleteApi with FutureUtils {
    override def transact(implicit conn0: Connection, ec: ExecutionContext): Future[TxReport] = {
      val conn = conn0.asInstanceOf[DatomicConn_JS]
      conn.rpc.delete(conn.proxy, delete.elements).future
    }
  }
}