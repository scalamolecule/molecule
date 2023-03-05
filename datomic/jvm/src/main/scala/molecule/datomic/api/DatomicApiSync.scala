package molecule.datomic.api

import molecule.base.util.exceptions.MoleculeError
import molecule.boilerplate.ast.Model._
import molecule.core.action.Insert
import molecule.core.api.{ApiSync, Connection, TxReport}
import molecule.core.transaction.{DeleteExtraction, InsertExtraction, SaveExtraction, UpdateExtraction}
import molecule.datomic.action._
import molecule.datomic.facade.DatomicConn_JVM
import molecule.datomic.marshalling.DatomicRpcJVM.Data
import molecule.datomic.query.{DatomicModel2Query, DatomicQueryResolveCursor, DatomicQueryResolveOffset}
import molecule.datomic.transaction.{Delete_stmts, Insert_stmts, Save_stmts, Update_stmts}


trait DatomicApiSync extends ApiSync {

  implicit class datomicQueryApiSync[Tpl](q: DatomicQuery[Tpl]) extends QueryApi[Tpl] {
    override def get(implicit conn: Connection): List[Tpl] = {
      DatomicQueryResolveOffset[Tpl](q.elements, q.limit, None)
        .getListFromOffset_sync(conn.asInstanceOf[DatomicConn_JVM])._1
    }
    def subscribe(callback: List[Tpl] => Unit)(implicit conn: Connection): Unit = {
      DatomicQueryResolveOffset[Tpl](q.elements, q.limit, None)
        .subscribe(conn.asInstanceOf[DatomicConn_JVM], callback)
    }
    override def inspect(implicit conn: Connection): Unit =
      printInspectQuery("QUERY", q.elements)
  }

  implicit class datomicQueryOffsetApiSync[Tpl](q: DatomicQueryOffset[Tpl]) extends QueryOffsetApi[Tpl] {
    override def get(implicit conn: Connection): (List[Tpl], Int, Boolean) = {
      DatomicQueryResolveOffset[Tpl](q.elements, q.limit, Some(q.offset))
        .getListFromOffset_sync(conn.asInstanceOf[DatomicConn_JVM])
    }
    override def inspect(implicit conn: Connection): Unit =
      printInspectQuery("QUERY (offset)", q.elements)
  }

  implicit class datomicQueryCursorApiSync[Tpl](q: DatomicQueryCursor[Tpl]) extends QueryCursorApi[Tpl] {
    override def get(implicit conn: Connection): (List[Tpl], String, Boolean) = {
      DatomicQueryResolveCursor[Tpl](q.elements, q.limit, Some(q.cursor))
        .getListFromCursor_sync(conn.asInstanceOf[DatomicConn_JVM])
    }
    override def inspect(implicit conn: Connection): Unit =
      printInspectQuery("QUERY (cursor)", q.elements)
  }


  implicit class datomicSaveApiSync[Tpl](save: DatomicSave) extends Transaction {
    override def transact(implicit conn: Connection): TxReport = catchMoleculeError {
      conn.asInstanceOf[DatomicConn_JVM].transact_sync(getStmts)
    }
    override def inspect(implicit conn: Connection): Unit = {
      printInspectTx("SAVE", save.elements, getStmts)
    }
    private def getStmts: Data =
      (new SaveExtraction() with Save_stmts).getStmts(save.elements)
  }


  implicit class datomicInsertApiSync[Tpl](insert0: Insert) extends Transaction {
    val insert = insert0.asInstanceOf[DatomicInsert_JVM]
    override def transact(implicit conn: Connection): TxReport = catchMoleculeError {
      conn.asInstanceOf[DatomicConn_JVM].transact_sync(getStmts)
    }
    override def inspect(implicit conn: Connection): Unit = {
      printInspectTx("INSERT", insert.elements, getStmts)
    }
    private def getStmts: Data = {
      (new InsertExtraction with Insert_stmts).getStmts(insert.elements, insert.tpls)
    }
  }


  implicit class datomicUpdateApiSync[Tpl](update: DatomicUpdate) extends Transaction {
    override def transact(implicit conn0: Connection): TxReport = catchMoleculeError {
      val conn = conn0.asInstanceOf[DatomicConn_JVM]
      conn.transact_sync(getStmts(conn))
    }
    override def inspect(implicit conn0: Connection): Unit = {
      printInspectTx("UPDATE", update.elements, getStmts(conn0.asInstanceOf[DatomicConn_JVM]))
    }
    private def getStmts(conn: DatomicConn_JVM): Data =
      (new UpdateExtraction(conn.proxy.uniqueAttrs, update.isUpsert) with Update_stmts)
        .getStmts(conn, update.elements)
  }


  implicit class datomicDeleteApiSync[Tpl](delete: DatomicDelete) extends Transaction {
    override def transact(implicit conn0: Connection): TxReport = catchMoleculeError {
      val conn = conn0.asInstanceOf[DatomicConn_JVM]
      conn.transact_sync(getStmts(conn))
    }
    override def inspect(implicit conn0: Connection): Unit = {
      printInspectTx("DELETE", delete.elements, getStmts(conn0.asInstanceOf[DatomicConn_JVM]))
    }
    private def getStmts(conn: DatomicConn_JVM): Data =
      (new DeleteExtraction with Delete_stmts).getStmtsData(conn, delete.elements)
  }


  private def printInspectQuery(label: String, elements: List[Element]): Unit = {
    val queries = new DatomicModel2Query(elements).getQueries(true)._3
    printInspect(label, elements, queries)
  }

  private def printInspectTx(label: String, elements: List[Element], stmts: Data): Unit = {
    printInspect(label, elements, stmts.toArray().toList.mkString("\n"))
  }

  private def catchMoleculeError(tx: => TxReport): TxReport = {
    try {
      tx
    } catch {
      case t: Throwable => throw MoleculeError(t.toString)
    }
  }
}
