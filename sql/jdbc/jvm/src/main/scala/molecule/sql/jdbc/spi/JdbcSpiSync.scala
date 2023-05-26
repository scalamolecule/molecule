package molecule.sql.jdbc.spi

import java.sql
import molecule.base.error._
import molecule.boilerplate.ast.Model._
import molecule.core.action._
import molecule.core.spi._
import molecule.core.transaction._
import molecule.core.validation.ModelValidation
import molecule.core.validation.insert.InsertValidation
import molecule.sql.core.query.SqlModel2Query
import molecule.sql.jdbc.facade.JdbcConn_jvm
import molecule.sql.jdbc.marshalling.JdbcRpcJVM.Data
import molecule.sql.jdbc.query.{JdbcQueryResolveCursor, JdbcQueryResolveOffset}
import molecule.sql.jdbc.subscription.SubscriptionStarter
import molecule.sql.jdbc.transaction._

object JdbcSpiSync extends JdbcSpiSync

trait JdbcSpiSync
  extends SpiSync
    with JVMJdbcSpiBase
    with SubscriptionStarter
    with PrintInspect {

  override def query_get[Tpl](q: Query[Tpl])(implicit conn: Conn): List[Tpl] = {
    JdbcQueryResolveOffset[Tpl](q.elements, q.limit, None, q.dbView)
      .getListFromOffset_sync(conn.asInstanceOf[JdbcConn_jvm])._1
  }

  override def query_subscribe[Tpl](q: Query[Tpl], callback: List[Tpl] => Unit)(implicit conn: Conn): Unit = {
    val jdbcConn = conn.asInstanceOf[JdbcConn_jvm]
    JdbcQueryResolveOffset[Tpl](q.elements, q.limit, None, q.dbView)
      .subscribe(jdbcConn, getWatcher(jdbcConn), callback)
  }

  override def query_inspect[Tpl](q: Query[Tpl])(implicit conn: Conn): Unit = {
    printInspectQuery("QUERY", q.elements)
  }


  override def queryOffset_get[Tpl](q: QueryOffset[Tpl])(implicit conn: Conn): (List[Tpl], Int, Boolean) = {
    JdbcQueryResolveOffset[Tpl](q.elements, q.limit, Some(q.offset), q.dbView)
      .getListFromOffset_sync(conn.asInstanceOf[JdbcConn_jvm])
  }

  override def queryOffset_inspect[Tpl](q: QueryOffset[Tpl])(implicit conn: Conn): Unit = {
    printInspectQuery("QUERY (offset)", q.elements)
  }

  override def queryCursor_get[Tpl](q: QueryCursor[Tpl])(implicit conn: Conn): (List[Tpl], String, Boolean) = {
    JdbcQueryResolveCursor[Tpl](q.elements, q.limit, Some(q.cursor), q.dbView)
      .getListFromCursor_sync(conn.asInstanceOf[JdbcConn_jvm])
  }

  override def queryCursor_inspect[Tpl](q: QueryCursor[Tpl])(implicit conn: Conn): Unit = {
    printInspectQuery("QUERY (cursor)", q.elements)
  }


  //  override def save_transact(save: Save)(implicit conn0: Conn): TxReport = {
  //    val errors = save_validate(save)
  //    if (errors.isEmpty) {
  //      val conn                  = conn0.asInstanceOf[JdbcConn_jvm]
  ////      val a = System.nanoTime()
  //      val (insertElements, tpl) = Save2insert.hydrate(save)
  ////      val b = System.nanoTime()
  ////      println("delta: " + (b - a) / 1000)
  //      val data                  = new InsertExtraction with Data_Insert {
  //        override protected val sqlConn: sql.Connection = conn.sqlConn
  //      }.getData(conn.proxy.nsMap, insertElements, List(tpl))
  //
  //      val a                     = System.nanoTime()
  //      val res = conn.transact_sync(data)
  //      val b                     = System.nanoTime()
  //      println("delta: " + (b - a) / 1000)
  //      res
  //    } else {
  //      throw ValidationErrors(errors)
  //    }
  //  }

  override def save_transact(save: Save)(implicit conn0: Conn): TxReport = {
    val errors = save_validate(save)
    if (errors.isEmpty) {
      val conn = conn0.asInstanceOf[JdbcConn_jvm]
      conn.transact_sync(save_getData(save, conn))
    } else {
      throw ValidationErrors(errors)
    }
  }

  override def save_inspect(save: Save)(implicit conn: Conn): Unit = {
    printInspectTx("SAVE", save.elements, save_getData(save, conn.asInstanceOf[JdbcConn_jvm]))
  }

  private def save_getData(save: Save, conn: JdbcConn_jvm): Data = {
    new SaveExtraction() with Data_Save {
      override protected val sqlConn = conn.sqlConn
    }.getData(save.elements)
  }

  override def save_validate(save: Save)(implicit conn: Conn): Map[String, Seq[String]] = {
    val proxy = conn.proxy
    ModelValidation(proxy.nsMap, proxy.attrMap, "save").validate(save.elements)
  }


  override def insert_transact(insert: Insert)(implicit conn0: Conn): TxReport = {
    val errors = insert_validate(insert)
    if (errors.isEmpty) {
      val conn = conn0.asInstanceOf[JdbcConn_jvm]
      conn.transact_sync(insert_getData(insert, conn))
    } else {
      throw InsertErrors(errors)
    }
  }
  override def insert_inspect(insert: Insert)(implicit conn: Conn): Unit = {
    val jdbcConn = conn.asInstanceOf[JdbcConn_jvm]
    printInspectTx("INSERT", insert.elements, insert_getData(insert, jdbcConn))
  }

  private def insert_getData(insert: Insert, conn: JdbcConn_jvm): Data = {
    new InsertExtraction with Data_Insert {
      override protected val sqlConn: sql.Connection = conn.sqlConn
    }.getData(conn.proxy.nsMap, insert.elements, insert.tpls)
  }

  override def insert_validate(insert: Insert)(implicit conn: Conn): Seq[(Int, Seq[InsertError])] = {
    InsertValidation.validate(conn, insert.elements, insert.tpls)
  }


  override def update_transact(update: Update)(implicit conn0: Conn): TxReport = {
    //        val errors = validate
    //        if (errors.isEmpty) {
    //          val conn = conn0.asInstanceOf[JdbcConn_JVM]
    //          conn.transact_sync(getStmts(conn))
    //        } else {
    //          throw ValidationErrors(errors)
    //        }
    ???
  }

  override def update_inspect(update: Update)(implicit conn0: Conn): Unit = {
    //        printInspectTx("UPDATE", update.elements, getStmts(conn0.asInstanceOf[JdbcConn_JVM]))
    ???
  }

  //      def getStmts(conn: JdbcConn_JVM): PreparedStmt = {
  //        (new UpdateExtraction(conn.proxy.uniqueAttrs, update.isUpsert) with Update_stmts {
  //          override protected val ps: PreparedStmt = ???
  //        })
  //          .getStmts(conn, update.elements)
  //      }

  override def update_validate(update: Update)(implicit conn: Conn): Map[String, Seq[String]] = {
    validateUpdate(conn, update.elements)
  }


  override def delete_transact(delete: Delete)(implicit conn0: Conn): TxReport = {
    //        val conn = conn0.asInstanceOf[JdbcConn_JVM]
    //        conn.transact_sync(getStmts(conn))
    ???
  }

  override def delete_inspect(delete: Delete)(implicit conn0: Conn): Unit = {
    //        printInspectTx("DELETE", delete.elements, getStmts(conn0.asInstanceOf[JdbcConn_JVM]))
    ???
  }

  //      def getStmts(conn: JdbcConn_JVM): PreparedStmt = {
  //        (new DeleteExtraction with Delete_stmts {
  //          override protected val ps: PreparedStmt = ???
  //        }).getStmtsData(conn, delete.elements)
  //      }


  private def printInspectQuery(label: String, elements: List[Element]): Unit = {
    val queries = new SqlModel2Query(elements).getQuery(Nil) //._3
    printInspect(label, elements, queries)
  }

  private def printInspectTx(label: String, elements: List[Element], data: Data): Unit = {
    //    printInspect(label, elements, stmts.toArray().toList.mkString("\n"))
    ???
  }
}
