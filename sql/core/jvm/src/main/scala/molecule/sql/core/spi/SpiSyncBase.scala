package molecule.sql.core.spi

import java.sql.Statement
import molecule.base.error._
import molecule.base.util.BaseHelpers
import molecule.boilerplate.ast.Model._
import molecule.core.action._
import molecule.core.marshalling.ConnProxy
import molecule.core.marshalling.dbView.{AsOf, DbView, Since}
import molecule.core.spi._
import molecule.core.validation.TxModelValidation
import molecule.core.validation.insert.InsertValidation
import molecule.sql.core.facade.JdbcConn_JVM
import molecule.sql.core.query.{Model2SqlQuery, SqlQueryBase, SqlQueryResolveCursor, SqlQueryResolveOffset}
import molecule.sql.core.transaction.update.UpdateHelper
import molecule.sql.core.transaction.{SqlBase_JVM, SqlUpdateSetValidator}
import scala.util.control.NonFatal


trait SpiSyncBase
  extends SpiSync
    with UpdateHelper
    with SqlBase_JVM
    with SpiHelpers
    with SqlUpdateSetValidator
    with Renderer
    with BaseHelpers {

  // Query --------------------------------------------------------

  def getModel2SqlQuery[Tpl](elements: List[Element]): Model2SqlQuery[Tpl] with SqlQueryBase

  override def query_get[Tpl](q0: Query[Tpl])(implicit conn0: Conn): List[Tpl] = {
    val conn = conn0.asInstanceOf[JdbcConn_JVM]
    val q    = q0.copy(elements = noKeywords(q0.elements, Some(conn.proxy)))
    if (q.doInspect)
      query_inspect(q)
    q.dbView.foreach(noTime)
    val m2q = getModel2SqlQuery[Tpl](q.elements)
    SqlQueryResolveOffset[Tpl](q.elements, q.optLimit, None, m2q)
      .getListFromOffset_sync(conn)._1
  }

  protected def query_getRaw[Tpl](q: Query[Tpl])(implicit conn0: Conn): List[Tpl] = {
    val conn = conn0.asInstanceOf[JdbcConn_JVM]
    val m2q  = getModel2SqlQuery[Tpl](q.elements)
    SqlQueryResolveOffset[Tpl](q.elements, q.optLimit, None, m2q)
      .getListFromOffset_sync(conn)._1
  }

  private def noTime(dbView: DbView): Unit = dbView match {
    case _: AsOf  => throw ModelError("Time function 'asOf' is only implemented for Datomic.")
    case _: Since => throw ModelError("Time function 'since' is only implemented for Datomic.")
  }

  override def query_subscribe[Tpl](q0: Query[Tpl], callback: List[Tpl] => Unit)
                                   (implicit conn0: Conn): Unit = {
    val conn = conn0.asInstanceOf[JdbcConn_JVM]
    val q    = q0.copy(elements = noKeywords(q0.elements, Some(conn.proxy)))
    val m2q  = getModel2SqlQuery[Tpl](q.elements)
    SqlQueryResolveOffset[Tpl](q.elements, q.optLimit, None, m2q)
      .subscribe(conn, callback, (elements: List[Element]) => getModel2SqlQuery[Tpl](elements))
  }
  override def query_unsubscribe[Tpl](q0: Query[Tpl])(implicit conn0: Conn): Unit = {
    val conn = conn0.asInstanceOf[JdbcConn_JVM]
    val q    = q0.copy(elements = noKeywords(q0.elements, Some(conn.proxy)))
    val m2q  = getModel2SqlQuery[Tpl](q.elements)
    SqlQueryResolveOffset[Tpl](q.elements, q.optLimit, None, m2q)
      .unsubscribe(conn)
  }

  override def query_inspect[Tpl](q: Query[Tpl])(implicit conn: Conn): Unit = {
    printInspectQuery("QUERY", q.elements, q.optLimit, None, Some(conn.proxy))
  }


  override def queryOffset_get[Tpl](q0: QueryOffset[Tpl])
                                   (implicit conn0: Conn): (List[Tpl], Int, Boolean) = {
    val conn = conn0.asInstanceOf[JdbcConn_JVM]
    val q    = q0.copy(elements = noKeywords(q0.elements, Some(conn.proxy)))
    if (q.doInspect)
      queryOffset_inspect(q)
    q.dbView.foreach(noTime)
    val m2q = getModel2SqlQuery[Tpl](q.elements)
    SqlQueryResolveOffset[Tpl](q.elements, q.optLimit, Some(q.offset), m2q)
      .getListFromOffset_sync(conn)
  }

  override def queryOffset_inspect[Tpl](q: QueryOffset[Tpl])(implicit conn: Conn): Unit = {
    printInspectQuery("QUERY (offset)", q.elements, q.optLimit, Some(q.offset), Some(conn.proxy))
  }

  override def queryCursor_get[Tpl](q0: QueryCursor[Tpl])
                                   (implicit conn0: Conn): (List[Tpl], String, Boolean) = {
    val conn = conn0.asInstanceOf[JdbcConn_JVM]
    val q    = q0.copy(elements = noKeywords(q0.elements, Some(conn.proxy)))
    if (q.doInspect)
      queryCursor_inspect(q)
    q.dbView.foreach(noTime)
    val m2q = getModel2SqlQuery[Tpl](q.elements)
    SqlQueryResolveCursor[Tpl](q.elements, q.optLimit, Some(q.cursor), m2q)
      .getListFromCursor_sync(conn)
  }

  override def queryCursor_inspect[Tpl](q: QueryCursor[Tpl])(implicit conn: Conn): Unit = {
    printInspectQuery("QUERY (cursor)", q.elements, q.optLimit, None, Some(conn.proxy))
  }

  private def printInspectQuery(
    label: String,
    elements: List[Element],
    optLimit: Option[Int],
    optOffset: Option[Int],
    optProxy: Option[ConnProxy]
  ): Unit = {
    tryInspect("query", elements) {
      val query = getModel2SqlQuery[Any](elements).getSqlQuery(Nil, optLimit, optOffset, optProxy)
      printRaw(label, elements, query)
    }
  }


  // Save --------------------------------------------------------

  override def save_transact(save0: Save)(implicit conn0: Conn): TxReport = {
    val conn = conn0.asInstanceOf[JdbcConn_JVM]
    val save = save0.copy(elements = noKeywords(save0.elements, Some(conn.proxy)))
    if (save.doInspect)
      save_inspect(save)
    val errors = save_validate(save0) // validate original elements against meta model
    if (errors.isEmpty) {
      val txReport = conn.transact_sync(save_getData(save, conn))
      conn.callback(save.elements)
      txReport
    } else {
      throw ValidationErrors(errors)
    }
  }

  override def save_inspect(save: Save)(implicit conn0: Conn): Unit = {
    val conn = conn0.asInstanceOf[JdbcConn_JVM]
    tryInspect("save", save.elements) {
      printInspectTx("SAVE", save.elements, save_getData(save, conn))
    }
  }

  // Implement for each sql database
  def save_getData(save: Save, conn: JdbcConn_JVM): Data

  override def save_validate(save: Save)(implicit conn: Conn): Map[String, Seq[String]] = {
    val proxy = conn.proxy
    TxModelValidation(proxy.nsMap, proxy.attrMap, "save").validate(save.elements)
  }


  // Insert --------------------------------------------------------

  override def insert_transact(insert0: Insert)(implicit conn0: Conn): TxReport = {
    val conn   = conn0.asInstanceOf[JdbcConn_JVM]
    val insert = insert0.copy(elements = noKeywords(insert0.elements, Some(conn.proxy)))
    if (insert.doInspect)
      insert_inspect(insert)
    val errors = insert_validate(insert0) // validate original elements against meta model
    if (errors.isEmpty) {
      val txReport = conn.transact_sync(insert_getData(insert, conn))
      conn.callback(insert.elements)
      txReport
    } else {
      throw InsertErrors(errors)
    }
  }
  override def insert_inspect(insert: Insert)(implicit conn: Conn): Unit = {
    tryInspect("insert", insert.elements) {
      val jdbcConn = conn.asInstanceOf[JdbcConn_JVM]
      printInspectTx("INSERT", insert.elements, insert_getData(insert, jdbcConn), insert.tpls)
    }
  }

  // Implement for each sql database
  def insert_getData(insert: Insert, conn: JdbcConn_JVM): Data

  override def insert_validate(insert: Insert)(implicit conn: Conn): Seq[(Int, Seq[InsertError])] = {
    InsertValidation.validate(conn, insert.elements, insert.tpls)
  }


  // Update --------------------------------------------------------

  override def update_transact(update0: Update)(implicit conn0: Conn): TxReport = {
    val conn   = conn0.asInstanceOf[JdbcConn_JVM]
    val update = update0.copy(elements = noKeywords(update0.elements, Some(conn.proxy)))
    if (update.doInspect)
      update_inspect(update)
    val errors = update_validate(update0) // validate original elements against meta model
    if (errors.isEmpty) {
      val data     = if (isRefUpdate(update.elements)) {
        if (update0.isUpsert)
          refUpserts(update)(conn)
        else
          refUpdates(update)(conn)
      } else {
        update_getData(conn, update)
      }
      val txReport = conn.transact_sync(data)
      conn.callback(update.elements)
      txReport
    } else {
      throw ValidationErrors(errors)
    }
  }

  override def update_inspect(update: Update)(implicit conn0: Conn): Unit = {
    val conn   = conn0.asInstanceOf[JdbcConn_JVM]
    val action = if (update.isUpsert) "UPSERT" else "UPDATE"
    tryInspect(action, update.elements) {
      if (isRefUpdate(update.elements)) {
        val (idsModel, updateModels) = prepareMultipleUpdates(update.elements, update.isUpsert)
        val refIds                   =
          s"""REF IDS MODEL ----------------
             |${idsModel.mkString("\n")}
             |
             |${refIdsQuery(idsModel, conn.proxy)}
             |""".stripMargin
        val updates                  =
          updateModels
            .map(_(42L)) // dummy value
            .map { m =>
              val elements = m.mkString("\n")
              val tables   = update_getData(conn, m, update.isUpsert)._1
              tables.headOption.fold(elements)(table => elements + "\n" + table.stmt)
            }
            .mkString(action + "S ----------------------\n", "\n------------\n", "")

        printRaw(action, update.elements, refIds + "\n" + updates)
      } else {
        printInspectTx(action, update.elements, update_getData(conn, update))
      }
    }
  }

  // Implement for each sql database
  def update_getData(conn: JdbcConn_JVM, update: Update): Data
  def update_getData(conn: JdbcConn_JVM, elements: List[Element], isUpsert: Boolean): Data


  // Delete --------------------------------------------------------

  override def delete_transact(delete0: Delete)(implicit conn0: Conn): TxReport = {
    val conn   = conn0.asInstanceOf[JdbcConn_JVM]
    val delete = delete0.copy(elements = noKeywords(delete0.elements, Some(conn.proxy)))
    if (delete.doInspect)
      delete_inspect(delete)
    val txReport = conn.transact_sync(delete_getData(conn, delete))
    conn.callback(delete.elements, true)
    txReport
  }

  override def delete_inspect(delete: Delete)(implicit conn0: Conn): Unit = {
    val conn = conn0.asInstanceOf[JdbcConn_JVM]
    tryInspect("delete", delete.elements) {
      printInspectTx("DELETE", delete.elements, delete_getData(conn, delete))
    }
  }

  // Implement for each sql database
  def delete_getData(conn: JdbcConn_JVM, delete: Delete): Data


  // Inspect --------------------------------------------------------

  private def tryInspect(action: String, elements: List[Element])(body: => Unit): Unit = try {
    body
  } catch {
    case NonFatal(e) =>
      println(s"\n------------------ Error inspecting $action -----------------------")
      elements.foreach(println)
      throw e
  }

  private def printInspectTx(
    label: String, elements: List[Element], stmts: Data, tpls: Seq[Product] = Nil
  ): Unit = {
    val (tables, joinTables) = stmts
    val tableStmts           = tables.reverse.map(_.stmt).mkString("\n--------\n")
    val joinTableStmts       = if (joinTables.isEmpty) {
      ""
    } else {
      "\n\n--------------\n\n" + joinTables.map(_.stmt).mkString("\n--------\n")
    }
    printRaw(label, elements, tableStmts + joinTableStmts, tpls.mkString("\n"))
  }


  // Util --------------------------------------

  override def fallback_rawTransact(
    txData: String,
    doPrint: Boolean = false
  )(implicit conn0: Conn): TxReport = {
    val conn  = conn0.asInstanceOf[JdbcConn_JVM]
    val debug = if (doPrint) (s: String) => println(s) else (_: String) => ()
    debug("\n=============================================================================")
    debug(txData)

    val ps = conn.sqlConn.prepareStatement(txData, Statement.RETURN_GENERATED_KEYS)
    ps.execute()

    val resultSet = ps.getGeneratedKeys // is empty if no nested data
    var ids       = List.empty[String]
    while (resultSet.next()) {
      ids = ids :+ resultSet.getLong(1).toString
    }
    ps.close()

    debug("---------------")
    debug("Ids: " + ids)
    TxReport(ids)
  }
}