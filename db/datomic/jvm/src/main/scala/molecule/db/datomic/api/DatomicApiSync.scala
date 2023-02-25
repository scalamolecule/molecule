package molecule.db.datomic.api

import molecule.base.util.exceptions.MoleculeError
import molecule.core.action.Insert
import molecule.core.api.{ApiSync, Connection, TxReport}
import molecule.core.transaction.{DeleteExtraction, InsertExtraction, SaveExtraction, UpdateExtraction}
import molecule.db.datomic.action._
import molecule.db.datomic.facade.DatomicConn_JVM
import molecule.db.datomic.query._
import molecule.db.datomic.transaction.{Delete_stmts, Insert_stmts, Save_stmts, Update_stmts}

trait DatomicApiSync extends ApiSync {

  implicit class datomicQueryApiSync[Tpl](q: DatomicQuery[Tpl]) extends QueryApi[Tpl] {
    override def get(implicit conn: Connection): List[Tpl] = {
      DatomicQueryResolveOffset[Tpl](q.elements, q.limit, None)
        .getListFromOffset_sync(conn.asInstanceOf[DatomicConn_JVM])._1
    }
    override def inspect(implicit conn: Connection): Unit = ???
  }

  implicit class datomicQueryOffsetApiSync[Tpl](q: DatomicQueryOffset[Tpl]) extends QueryOffsetApi[Tpl] {
    override def get(implicit conn: Connection): (List[Tpl], Int, Boolean) = {
      DatomicQueryResolveOffset[Tpl](q.elements, q.limit, Some(q.offset))
        .getListFromOffset_sync(conn.asInstanceOf[DatomicConn_JVM])
    }
    override def inspect(implicit conn: Connection): Unit = ???
  }

  implicit class datomicQueryCursorApiSync[Tpl](q: DatomicQueryCursor[Tpl]) extends QueryCursorApi[Tpl] {
    override def get(implicit conn: Connection): (List[Tpl], String, Boolean) = {
      DatomicQueryResolveCursor[Tpl](q.elements, q.limit, Some(q.cursor))
        .getListFromCursor_sync(conn.asInstanceOf[DatomicConn_JVM])
    }
    override def inspect(implicit conn: Connection): Unit = ???
  }


  implicit class datomicSaveApiSync[Tpl](save: DatomicSave) extends SaveApi {
    override def transact(implicit conn: Connection): TxReport = try{
      val stmts = (new SaveExtraction() with Save_stmts).getStmts(save.elements)
      conn.asInstanceOf[DatomicConn_JVM].transact_sync(stmts)
    } catch {
      case t: Throwable => throw MoleculeError(t.toString)
    }
  }

  implicit class datomicInsertApiSync[Tpl](insert0: Insert) extends InsertApi {
    val q = insert0.asInstanceOf[DatomicInsert_JVM]
    override def transact(implicit conn: Connection): TxReport = try {
      val stmts = (new InsertExtraction with Insert_stmts).getStmts(q.elements, q.tpls)
      conn.asInstanceOf[DatomicConn_JVM].transact_sync(stmts)
    } catch {
      case t: Throwable => throw MoleculeError(t.toString)
    }
  }

  implicit class datomicUpdateApiSync[Tpl](update: DatomicUpdate) extends UpdateApi {
    override def transact(implicit conn0: Connection): TxReport = try{
      val conn  = conn0.asInstanceOf[DatomicConn_JVM]
      val stmts = (new UpdateExtraction(conn.proxy.uniqueAttrs, update.isUpsert) with Update_stmts)
        .getStmts(conn, update.elements)
      conn.transact_sync(stmts)
    } catch {
      case t: Throwable => throw MoleculeError(t.toString)
    }
  }

  implicit class datomicDeleteApiSync[Tpl](delete: DatomicDelete) extends DeleteApi {
    override def transact(implicit conn0: Connection): TxReport = try {
      val conn  = conn0.asInstanceOf[DatomicConn_JVM]
      val stmts = (new DeleteExtraction with Delete_stmts).getStmtsData(conn, delete.elements)
      conn.transact_sync(stmts)
    } catch {
      case t: Throwable => throw MoleculeError(t.toString)
    }
  }
}