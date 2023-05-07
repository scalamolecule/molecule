package molecule.sql.jdbc.api

import molecule.base.error._
import molecule.boilerplate.ast.Model._
import molecule.core.action._
import molecule.core.api.{ApiSync, Connection, TxReport}
import molecule.core.transaction.{DeleteExtraction, InsertExtraction, SaveExtraction, UpdateExtraction}
import molecule.core.validation.ModelValidation
import molecule.core.validation.insert.InsertValidation
import molecule.sql.core.query.SqlModel2Query
import molecule.sql.jdbc.marshalling.JdbcRpcJVM.Data
import molecule.sql.jdbc.facade.JdbcConn_JVM
import molecule.sql.jdbc.query.{JdbcQueryResolveCursor, JdbcQueryResolveOffset}
import molecule.sql.jdbc.subscription.SubscriptionStarter
import molecule.sql.jdbc.transaction.{Delete_stmts, Insert_stmts, Save_stmts, Update_stmts}


trait JdbcApiSync extends JVMJdbcApiBase with SubscriptionStarter with ApiSync {

  implicit class datomicQueryApiSync[Tpl](q: Query[Tpl]) extends QueryApi[Tpl] {
    override def get(implicit conn: Connection): List[Tpl] = {
      JdbcQueryResolveOffset[Tpl](q.elements, q.limit, None, q.dbView)
        .getListFromOffset_sync(conn.asInstanceOf[JdbcConn_JVM])._1
    }

    override def subscribe(callback: List[Tpl] => Unit)(implicit conn: Connection): Unit = {
      val datomicConn = conn.asInstanceOf[JdbcConn_JVM]
      JdbcQueryResolveOffset[Tpl](q.elements, q.limit, None, q.dbView)
        .subscribe(datomicConn, getWatcher(datomicConn), callback)
    }

    override def inspect(implicit conn: Connection): Unit = {
      printInspectQuery("QUERY", q.elements)
    }
  }


  implicit class datomicQueryOffsetApiSync[Tpl](q: QueryOffset[Tpl]) extends QueryOffsetApi[Tpl] {
    override def get(implicit conn: Connection): (List[Tpl], Int, Boolean) = {
      JdbcQueryResolveOffset[Tpl](q.elements, q.limit, Some(q.offset), q.dbView)
        .getListFromOffset_sync(conn.asInstanceOf[JdbcConn_JVM])
    }

    override def inspect(implicit conn: Connection): Unit =
      printInspectQuery("QUERY (offset)", q.elements)
  }

  implicit class datomicQueryCursorApiSync[Tpl](q: QueryCursor[Tpl]) extends QueryCursorApi[Tpl] {
    override def get(implicit conn: Connection): (List[Tpl], String, Boolean) = {
      JdbcQueryResolveCursor[Tpl](q.elements, q.limit, Some(q.cursor), q.dbView)
        .getListFromCursor_sync(conn.asInstanceOf[JdbcConn_JVM])
    }

    override def inspect(implicit conn: Connection): Unit = {
      printInspectQuery("QUERY (cursor)", q.elements)
    }
  }


  implicit class datomicSaveApiSync[Tpl](save: Save) extends SaveTransaction {
    override def transact(implicit conn: Connection): TxReport = {
      val errors = validate
      if (errors.isEmpty) {
        conn.asInstanceOf[JdbcConn_JVM].transact_sync(getStmts())
      } else {
        throw ValidationErrors(errors)
      }
    }

    override def inspect(implicit conn: Connection): Unit = {
      printInspectTx("SAVE", save.elements, getStmts())
    }

    def getStmts(eidIndex: Int = 0): Data = {
      (new SaveExtraction() with Save_stmts).getStmts(save.elements, eidIndex)
    }

    override def validate(implicit conn: Connection): Map[String, Seq[String]] = {
      val proxy = conn.proxy
      ModelValidation(proxy.nsMap, proxy.attrMap, "save").validate(save.elements)
    }
  }


  implicit class datomicInsertApiSync[Tpl](insert0: Insert) extends InsertTransaction {
    val insert = insert0.asInstanceOf[InsertTpls]
    override def transact(implicit conn: Connection): TxReport = {
      val errors = validate
      if (errors.isEmpty) {
        val datomicConn = conn.asInstanceOf[JdbcConn_JVM]
        datomicConn.transact_sync(getStmts(datomicConn))
      } else {
        throw InsertErrors(errors)
      }
    }
    override def inspect(implicit conn: Connection): Unit = {
      val datomicConn = conn.asInstanceOf[JdbcConn_JVM]
      printInspectTx("INSERT", insert.elements, getStmts(datomicConn))
    }

    def getStmts(conn: JdbcConn_JVM, eidIndex: Int = 0): Data = {
      (new InsertExtraction with Insert_stmts)
        .getStmts(conn.proxy.nsMap, insert.elements, insert.tpls, eidIndex)
    }

    override def validate(implicit conn: Connection): Seq[(Int, Seq[InsertError])] = {
      InsertValidation.validate(conn, insert.elements, insert.tpls)
    }
  }


  implicit class datomicUpdateApiSync[Tpl](update: Update) extends UpdateTransaction {
    override def transact(implicit conn0: Connection): TxReport = {
      val errors = validate
      if (errors.isEmpty) {
        val conn = conn0.asInstanceOf[JdbcConn_JVM]
        conn.transact_sync(getStmts(conn))
      } else {
        throw ValidationErrors(errors)
      }
    }

    override def inspect(implicit conn0: Connection): Unit = {
      printInspectTx("UPDATE", update.elements, getStmts(conn0.asInstanceOf[JdbcConn_JVM]))
    }

    def getStmts(conn: JdbcConn_JVM): Data = {
      (new UpdateExtraction(conn.proxy.uniqueAttrs, update.isUpsert) with Update_stmts)
        .getStmts(conn, update.elements)
    }

    override def validate(implicit conn: Connection): Map[String, Seq[String]] = {
      validateUpdate(conn, update.elements)
    }
  }


  implicit class datomicDeleteApiSync[Tpl](delete: Delete) extends DeleteTransaction {
    override def transact(implicit conn0: Connection): TxReport = {
      val conn = conn0.asInstanceOf[JdbcConn_JVM]
      conn.transact_sync(getStmts(conn))
    }

    override def inspect(implicit conn0: Connection): Unit = {
      printInspectTx("DELETE", delete.elements, getStmts(conn0.asInstanceOf[JdbcConn_JVM]))
    }

    def getStmts(conn: JdbcConn_JVM): Data = {
      (new DeleteExtraction with Delete_stmts).getStmtsData(conn, delete.elements)
    }
  }


  private def printInspectQuery(label: String, elements: List[Element]): Unit = {
    val queries = new SqlModel2Query(elements).getQueries(true)._3
    printInspect(label, elements, queries)
  }

  private def printInspectTx(label: String, elements: List[Element], stmts: Data): Unit = {
//    printInspect(label, elements, stmts.toArray().toList.mkString("\n"))
    ???
  }
}
