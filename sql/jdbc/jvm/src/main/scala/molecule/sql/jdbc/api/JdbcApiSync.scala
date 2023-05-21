package molecule.sql.jdbc.api

import java.sql
import molecule.base.error._
import molecule.boilerplate.ast.Model._
import molecule.core.action._
import molecule.core.api._
import molecule.core.transaction._
import molecule.core.validation.ModelValidation
import molecule.core.validation.insert.InsertValidation
import molecule.sql.core.query.SqlModel2Query
import molecule.sql.jdbc.facade.JdbcConn_JVM
import molecule.sql.jdbc.marshalling.JdbcRpcJVM.Data
import molecule.sql.jdbc.query.{JdbcQueryResolveCursor, JdbcQueryResolveOffset}
import molecule.sql.jdbc.subscription.SubscriptionStarter
import molecule.sql.jdbc.transaction._


object JdbcApiSync extends JdbcApiSync

trait JdbcApiSync extends JVMJdbcApiBase with SubscriptionStarter with ApiSync {

  implicit class jdbcQueryApiSync[Tpl](q: Query[Tpl]) extends QueryApi[Tpl] {
    override def get(implicit conn: Connection): List[Tpl] = {
      JdbcQueryResolveOffset[Tpl](q.elements, q.limit, None, q.dbView)
        .getListFromOffset_sync(conn.asInstanceOf[JdbcConn_JVM])._1
    }

    override def subscribe(callback: List[Tpl] => Unit)(implicit conn: Connection): Unit = {
      val jdbcConn = conn.asInstanceOf[JdbcConn_JVM]
      JdbcQueryResolveOffset[Tpl](q.elements, q.limit, None, q.dbView)
        .subscribe(jdbcConn, getWatcher(jdbcConn), callback)
    }

    override def inspect(implicit conn: Connection): Unit = {
      printInspectQuery("QUERY", q.elements)
    }
  }


  implicit class jdbcQueryOffsetApiSync[Tpl](q: QueryOffset[Tpl]) extends QueryOffsetApi[Tpl] {
    override def get(implicit conn: Connection): (List[Tpl], Int, Boolean) = {
      JdbcQueryResolveOffset[Tpl](q.elements, q.limit, Some(q.offset), q.dbView)
        .getListFromOffset_sync(conn.asInstanceOf[JdbcConn_JVM])
    }

    override def inspect(implicit conn: Connection): Unit =
      printInspectQuery("QUERY (offset)", q.elements)
  }

  implicit class jdbcQueryCursorApiSync[Tpl](q: QueryCursor[Tpl]) extends QueryCursorApi[Tpl] {
    override def get(implicit conn: Connection): (List[Tpl], String, Boolean) = {
      JdbcQueryResolveCursor[Tpl](q.elements, q.limit, Some(q.cursor), q.dbView)
        .getListFromCursor_sync(conn.asInstanceOf[JdbcConn_JVM])
    }

    override def inspect(implicit conn: Connection): Unit = {
      printInspectQuery("QUERY (cursor)", q.elements)
    }
  }


  implicit class jdbcSaveApiSync[Tpl](save: Save) extends SaveTransaction {
    override def transact(implicit conn0: Connection): TxReport = {
      val errors = validate
      if (errors.isEmpty) {
        val conn = conn0.asInstanceOf[JdbcConn_JVM]
        conn.transact_sync(getData(conn))
      } else {
        throw ValidationErrors(errors)
      }
    }

    override def inspect(implicit conn: Connection): Unit = {
      printInspectTx("SAVE", save.elements, getData(conn.asInstanceOf[JdbcConn_JVM]))
    }

    def getData(conn: JdbcConn_JVM): Data = {
      new SaveExtraction() with Data_Save {
        override protected val sqlConn = conn.sqlConn
      }.getData(save.elements)
    }

    override def validate(implicit conn: Connection): Map[String, Seq[String]] = {
      val proxy = conn.proxy
      ModelValidation(proxy.schema.nsMap, proxy.schema.attrMap, "save").validate(save.elements)
    }
  }


    implicit class jdbcInsertApiSync[Tpl](insert: Insert) extends InsertTransaction {
      override def transact(implicit conn0: Connection): TxReport = {
        val errors = validate
        if (errors.isEmpty) {
          val conn = conn0.asInstanceOf[JdbcConn_JVM]
          conn.transact_sync(getData(conn))
        } else {
          throw InsertErrors(errors)
        }
      }
      override def inspect(implicit conn: Connection): Unit = {
        val jdbcConn = conn.asInstanceOf[JdbcConn_JVM]
        printInspectTx("INSERT", insert.elements, getData(jdbcConn))
      }

      def getData(conn: JdbcConn_JVM): Data = {
        new InsertExtraction with Data_Insert {
          override protected val sqlConn: sql.Connection = conn.sqlConn
        }.getData(conn.proxy.schema.nsMap, insert.elements, insert.tpls)
      }

      override def validate(implicit conn: Connection): Seq[(Int, Seq[InsertError])] = {
        InsertValidation.validate(conn, insert.elements, insert.tpls)
      }
    }

  //
  //  implicit class jdbcUpdateApiSync[Tpl](update: Update) extends UpdateTransaction {
  //    override def transact(implicit conn0: Connection): TxReport = {
  //      val errors = validate
  //      if (errors.isEmpty) {
  //        val conn = conn0.asInstanceOf[JdbcConn_JVM]
  //        conn.transact_sync(getStmts(conn))
  //      } else {
  //        throw ValidationErrors(errors)
  //      }
  //    }
  //
  //    override def inspect(implicit conn0: Connection): Unit = {
  //      printInspectTx("UPDATE", update.elements, getStmts(conn0.asInstanceOf[JdbcConn_JVM]))
  //    }
  //
  //    def getStmts(conn: JdbcConn_JVM): PreparedStmt = {
  //      (new UpdateExtraction(conn.proxy.schema.uniqueAttrs, update.isUpsert) with Update_stmts {
  //        override protected val ps: PreparedStmt = ???
  //      })
  //        .getStmts(conn, update.elements)
  //    }
  //
  //    override def validate(implicit conn: Connection): Map[String, Seq[String]] = {
  //      validateUpdate(conn, update.elements)
  //    }
  //  }
  //
  //
  //  implicit class jdbcDeleteApiSync[Tpl](delete: Delete) extends DeleteTransaction {
  //    override def transact(implicit conn0: Connection): TxReport = {
  //      val conn = conn0.asInstanceOf[JdbcConn_JVM]
  //      conn.transact_sync(getStmts(conn))
  //    }
  //
  //    override def inspect(implicit conn0: Connection): Unit = {
  //      printInspectTx("DELETE", delete.elements, getStmts(conn0.asInstanceOf[JdbcConn_JVM]))
  //    }
  //
  //    def getStmts(conn: JdbcConn_JVM): PreparedStmt = {
  //      (new DeleteExtraction with Delete_stmts {
  //        override protected val ps: PreparedStmt = ???
  //      }).getStmtsData(conn, delete.elements)
  //    }
  //  }


  private def printInspectQuery(label: String, elements: List[Element]): Unit = {
    val queries = new SqlModel2Query(elements).getQuery(Nil) //._3
    printInspect(label, elements, queries)
  }

  private def printInspectTx(label: String, elements: List[Element], data: Data): Unit = {
    //    printInspect(label, elements, stmts.toArray().toList.mkString("\n"))
    ???
  }
}
