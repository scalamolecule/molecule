package molecule.db.datomic.api

import molecule.core.action.Insert
import molecule.core.api.{ApiSync, Connection, TxReport}
import molecule.db.datomic.action._
import molecule.db.datomic.facade.DatomicConn_JS


trait DatomicApiSyncImpl extends ApiSync {

  implicit class datomicQueryApiSync[Tpl](q: DatomicQueryImpl[Tpl]) extends QueryApi[Tpl] {
    override def get(implicit conn0: Connection): List[Tpl] = {
      //      val conn = conn0.asInstanceOf[DatomicConn_JS]
      //      conn.rpc.query[Tpl](conn.proxy, q.elements)
      ???
    }
    override def inspect(implicit conn: Connection): Unit = ???
  }

  implicit class datomicQueryOffsetApiSync[Tpl](q: DatomicQueryImplOffset[Tpl]) extends QueryOffsetApi[Tpl] {
    override def get(implicit conn0: Connection): (List[Tpl], Int, Boolean) = {
      //      val conn = conn0.asInstanceOf[DatomicConn_JS]
      //      conn.rpc.query[Tpl](conn.proxy, q.elements).future
      ???
    }
    override def inspect(implicit conn: Connection): Unit = ???
  }

  implicit class datomicQueryCursorApiSync[Tpl](q: DatomicQueryImplCursor[Tpl]) extends QueryCursorApi[Tpl] {
    override def get(implicit conn0: Connection): (List[Tpl], String, Boolean) = {
      val conn = conn0.asInstanceOf[DatomicConn_JS]
      //      conn.rpc.query[Tpl](conn.proxy, q.elements).future
      ???
    }
    override def inspect(implicit conn: Connection): Unit = ???
  }


  implicit class datomicSaveApiSync[Tpl](save: DatomicSaveImpl) extends SaveApi {
    override def transact(implicit conn0: Connection): TxReport = {
      ???
    }
  }

  implicit class datomicInsertApiSync[Tpl](insert: Insert) extends InsertApi {
    override def transact(implicit conn0: Connection): TxReport = {
      ???
    }
  }

  implicit class datomicUpdateApiSync[Tpl](update: DatomicUpdateImpl) extends UpdateApi {
    override def transact(implicit conn0: Connection): TxReport = {
      ???
    }
  }

  implicit class datomicDeleteApiSync[Tpl](delete: DatomicDeleteImpl) extends DeleteApi {
    override def transact(implicit conn0: Connection): TxReport = {
      ???
    }
  }
}
