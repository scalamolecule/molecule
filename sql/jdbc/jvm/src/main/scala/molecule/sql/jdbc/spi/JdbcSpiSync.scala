package molecule.sql.jdbc.spi

import java.sql
import molecule.base.error._
import molecule.base.util.BaseHelpers
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
import scala.collection.mutable.ListBuffer

object JdbcSpiSync extends JdbcSpiSync

trait JdbcSpiSync
  extends SpiSync
    with JVMJdbcSpiBase
    with SubscriptionStarter
    with PrintInspect {

  // Query --------------------------------------------------------

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


  // Save --------------------------------------------------------

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
    new ResolveSave() with Data_Save {
      override protected val sqlConn = conn.sqlConn
    }.getData(save.elements)
  }

  override def save_validate(save: Save)(implicit conn: Conn): Map[String, Seq[String]] = {
    val proxy = conn.proxy
    ModelValidation(proxy.nsMap, proxy.attrMap, "save").validate(save.elements)
  }


  // Insert --------------------------------------------------------

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
    new ResolveInsert with Data_Insert {
      override protected val sqlConn: sql.Connection = conn.sqlConn
    }.getData(conn.proxy.nsMap, insert.elements, insert.tpls)
  }

  override def insert_validate(insert: Insert)(implicit conn: Conn): Seq[(Int, Seq[InsertError])] = {
    InsertValidation.validate(conn, insert.elements, insert.tpls)
  }


  // Update --------------------------------------------------------

  override def update_transact(update: Update)(implicit conn0: Conn): TxReport = {
    val errors = update_validate(update)
    if (errors.isEmpty) {
      val conn = conn0.asInstanceOf[JdbcConn_jvm]
      if (isRefUpdate(update.elements)) {
        // Atomic transaction with updates for each ref namespace
        conn.atomicTransaction(refUpdates(update)(conn))
      } else {
        conn.transact_sync(update_getData(conn, update))
      }
    } else {
      throw ValidationErrors(errors)
    }
  }

  override def update_inspect(update: Update)(implicit conn0: Conn): Unit = {
    printInspectTx("UPDATE", update.elements, update_getData(conn0.asInstanceOf[JdbcConn_jvm], update))
  }

  private def update_getData(conn: JdbcConn_jvm, update: Update): Data = {
    new ResolveUpdate(conn.proxy.uniqueAttrs, update.isUpsert) with Data_Update {
      override protected val sqlConn = conn.sqlConn
    }.getData(update.elements)
  }

  private def update_getData(conn: JdbcConn_jvm, elements: List[Element], isUpsert: Boolean): Data = {
    new ResolveUpdate(conn.proxy.uniqueAttrs, isUpsert) with Data_Update {
      override protected val sqlConn = conn.sqlConn
    }.getData(elements)
  }

  override def update_validate(update: Update)(implicit conn: Conn): Map[String, Seq[String]] = {
    validateUpdate(conn, update)
  }


  // Delete --------------------------------------------------------

  override def delete_transact(delete: Delete)(implicit conn0: Conn): TxReport = {
    val conn = conn0.asInstanceOf[JdbcConn_jvm]
    conn.transact_sync(delete_getData(conn, delete))
  }

  override def delete_inspect(delete: Delete)(implicit conn0: Conn): Unit = {
    printInspectTx("DELETE", delete.elements, delete_getData(conn0.asInstanceOf[JdbcConn_jvm], delete))
  }

  private def delete_getData(conn: JdbcConn_jvm, delete: Delete): Data = {
    new ResolveDelete with Data_Delete {
      override protected val sqlConn = conn.sqlConn
    }.getData(delete.elements)
  }


  // Inspect --------------------------------------------------------

  private def printInspectQuery(label: String, elements: List[Element]): Unit = {
    val queries = new SqlModel2Query(elements).getQuery(Nil) //._3
    printInspect(label, elements, queries)
  }

  private def printInspectTx(label: String, elements: List[Element], data: Data): Unit = {
    // Simply print the statement (with no data)
    printInspect(label, elements, data._1.head.stmt)
  }


  // Util --------------------------------------

  private def refUpdates(update: Update)(implicit conn: JdbcConn_jvm): () => Map[List[String], List[Long]] = {
    if (update.isUpsert)
      throw ModelError("Can't upsert referenced attributes. Please update instead.")

    val (idsModel, updateModels) = prepareMultipleUpdates(update.elements, update.isUpsert)
    type L = Long
    val idQuery = updateModels.size match {
      case 1  => Query[L](idsModel)
      case 2  => Query[(L, L)](idsModel)
      case 3  => Query[(L, L, L)](idsModel)
      case 4  => Query[(L, L, L, L)](idsModel)
      case 5  => Query[(L, L, L, L, L)](idsModel)
      case 6  => Query[(L, L, L, L, L, L)](idsModel)
      case 7  => Query[(L, L, L, L, L, L, L)](idsModel)
      case 8  => Query[(L, L, L, L, L, L, L, L)](idsModel)
      case 9  => Query[(L, L, L, L, L, L, L, L, L)](idsModel)
      case 10 => Query[(L, L, L, L, L, L, L, L, L, L)](idsModel)
      case 11 => Query[(L, L, L, L, L, L, L, L, L, L, L)](idsModel)
      case 12 => Query[(L, L, L, L, L, L, L, L, L, L, L, L)](idsModel)
      case 13 => Query[(L, L, L, L, L, L, L, L, L, L, L, L, L)](idsModel)
      case 14 => Query[(L, L, L, L, L, L, L, L, L, L, L, L, L, L)](idsModel)
      case 15 => Query[(L, L, L, L, L, L, L, L, L, L, L, L, L, L, L)](idsModel)
      case 16 => Query[(L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L)](idsModel)
      case 17 => Query[(L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L)](idsModel)
      case 18 => Query[(L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L)](idsModel)
      case 19 => Query[(L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L)](idsModel)
      case 20 => Query[(L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L)](idsModel)
      case 21 => Query[(L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L)](idsModel)
      case 22 => Query[(L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L)](idsModel)
    }

    val ids: List[Long] = query_get(idQuery).head match {
      case a: L                                                                                                                                 => List(0L, a)
      case (a: L, b: L)                                                                                                                         => List(0L, a, b)
      case (a: L, b: L, c: L)                                                                                                                   => List(0L, a, b, c)
      case (a: L, b: L, c: L, d: L)                                                                                                             => List(0L, a, b, c, d)
      case (a: L, b: L, c: L, d: L, e: L)                                                                                                       => List(0L, a, b, c, d, e)
      case (a: L, b: L, c: L, d: L, e: L, f: L)                                                                                                 => List(0L, a, b, c, d, e, f)
      case (a: L, b: L, c: L, d: L, e: L, f: L, g: L)                                                                                           => List(0L, a, b, c, d, e, f, g)
      case (a: L, b: L, c: L, d: L, e: L, f: L, g: L, h: L)                                                                                     => List(0L, a, b, c, d, e, f, g, h)
      case (a: L, b: L, c: L, d: L, e: L, f: L, g: L, h: L, i: L)                                                                               => List(0L, a, b, c, d, e, f, g, h, i)
      case (a: L, b: L, c: L, d: L, e: L, f: L, g: L, h: L, i: L, j: L)                                                                         => List(0L, a, b, c, d, e, f, g, h, i, j)
      case (a: L, b: L, c: L, d: L, e: L, f: L, g: L, h: L, i: L, j: L, k: L)                                                                   => List(0L, a, b, c, d, e, f, g, h, i, j, k)
      case (a: L, b: L, c: L, d: L, e: L, f: L, g: L, h: L, i: L, j: L, k: L, l: L)                                                             => List(0L, a, b, c, d, e, f, g, h, i, j, k, l)
      case (a: L, b: L, c: L, d: L, e: L, f: L, g: L, h: L, i: L, j: L, k: L, l: L, m: L)                                                       => List(0L, a, b, c, d, e, f, g, h, i, j, k, l, m)
      case (a: L, b: L, c: L, d: L, e: L, f: L, g: L, h: L, i: L, j: L, k: L, l: L, m: L, n: L)                                                 => List(0L, a, b, c, d, e, f, g, h, i, j, k, l, m, n)
      case (a: L, b: L, c: L, d: L, e: L, f: L, g: L, h: L, i: L, j: L, k: L, l: L, m: L, n: L, o: L)                                           => List(0L, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o)
      case (a: L, b: L, c: L, d: L, e: L, f: L, g: L, h: L, i: L, j: L, k: L, l: L, m: L, n: L, o: L, p: L)                                     => List(0L, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p)
      case (a: L, b: L, c: L, d: L, e: L, f: L, g: L, h: L, i: L, j: L, k: L, l: L, m: L, n: L, o: L, p: L, q: L)                               => List(0L, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q)
      case (a: L, b: L, c: L, d: L, e: L, f: L, g: L, h: L, i: L, j: L, k: L, l: L, m: L, n: L, o: L, p: L, q: L, r: L)                         => List(0L, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r)
      case (a: L, b: L, c: L, d: L, e: L, f: L, g: L, h: L, i: L, j: L, k: L, l: L, m: L, n: L, o: L, p: L, q: L, r: L, s: L)                   => List(0L, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s)
      case (a: L, b: L, c: L, d: L, e: L, f: L, g: L, h: L, i: L, j: L, k: L, l: L, m: L, n: L, o: L, p: L, q: L, r: L, s: L, t: L)             => List(0L, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t)
      case (a: L, b: L, c: L, d: L, e: L, f: L, g: L, h: L, i: L, j: L, k: L, l: L, m: L, n: L, o: L, p: L, q: L, r: L, s: L, t: L, u: L)       => List(0L, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u)
      case (a: L, b: L, c: L, d: L, e: L, f: L, g: L, h: L, i: L, j: L, k: L, l: L, m: L, n: L, o: L, p: L, q: L, r: L, s: L, t: L, u: L, v: L) => List(0L, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v)
    }

    () => {
      val idMaps = ids.zipWithIndex.map {
        case (id: Long, i) =>
          val updateModel = updateModels(i)(id)
          conn.populateStmts(update_getData(conn, updateModel, update.isUpsert))
      }
      // Return TxReport with initial update ids
      idMaps.head
    }
  }
}
