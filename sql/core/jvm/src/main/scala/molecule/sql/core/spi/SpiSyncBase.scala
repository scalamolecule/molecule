package molecule.sql.core.spi

import java.sql.{ResultSet, ResultSetMetaData, Statement}
import molecule.base.error._
import molecule.base.util.BaseHelpers
import molecule.boilerplate.ast.Model._
import molecule.core.action._
import molecule.core.marshalling.ConnProxy
import molecule.core.spi._
import molecule.core.validation.TxModelValidation
import molecule.core.validation.insert.InsertValidation
import molecule.sql.core.facade.JdbcConn_JVM
import molecule.sql.core.query.{Model2SqlQuery, SqlQueryBase, SqlQueryResolveCursor, SqlQueryResolveOffset}
import molecule.sql.core.transaction.strategy.SqlAction
import molecule.sql.core.transaction.strategy.delete.DeleteAction
import molecule.sql.core.transaction.strategy.insert.InsertAction
import molecule.sql.core.transaction.strategy.save.SaveAction
import molecule.sql.core.transaction.strategy.update.UpdateAction
import molecule.sql.core.transaction.{ConnectionPool, SqlUpdateSetValidator}
import scala.collection.mutable.ListBuffer
import scala.util.control.NonFatal
import molecule.sql.core.javaSql.{ResultSetInterface => Row}


trait SpiSyncBase
  extends SpiSync
    with ConnectionPool
    with SpiHelpers
    with SqlUpdateSetValidator
    with Renderer
    with BaseHelpers {


  // Query --------------------------------------------------------

  override def query_get[Tpl](q0: Query[Tpl])(implicit conn0: Conn): List[Tpl] = {
    val conn = conn0.asInstanceOf[JdbcConn_JVM]
    val q    = q0.copy(elements = noKeywords(q0.elements, Some(conn.proxy)))
    if (q.doInspect)
      query_inspect(q)
    val m2q = getModel2SqlQuery(q.elements)
    SqlQueryResolveOffset[Tpl](q.elements, q.optLimit, None, m2q)
      .getListFromOffset_sync(conn)._1
  }

  override def query_subscribe[Tpl](q0: Query[Tpl], callback: List[Tpl] => Unit)
                                   (implicit conn0: Conn): Unit = {
    val conn = conn0.asInstanceOf[JdbcConn_JVM]
    val q    = q0.copy(elements = noKeywords(q0.elements, Some(conn.proxy)))
    val m2q  = getModel2SqlQuery(q.elements)
    SqlQueryResolveOffset[Tpl](q.elements, q.optLimit, None, m2q)
      .subscribe(conn, callback, (elements: List[Element]) => getModel2SqlQuery(elements))
  }
  override def query_unsubscribe[Tpl](q0: Query[Tpl])(implicit conn0: Conn): Unit = {
    val conn = conn0.asInstanceOf[JdbcConn_JVM]
    val q    = q0.copy(elements = noKeywords(q0.elements, Some(conn.proxy)))
    val m2q  = getModel2SqlQuery(q.elements)
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
    val m2q = getModel2SqlQuery(q.elements)
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
    val m2q = getModel2SqlQuery(q.elements)
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
      val query = getModel2SqlQuery(elements).getSqlQuery(Nil, optLimit, optOffset, optProxy)
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
      val txReport = conn.transact_sync(save_getAction(save, conn))
      conn.callback(save.elements)
      txReport
    } else {
      throw ValidationErrors(errors)
    }
  }

  override def save_inspect(save: Save)(implicit conn0: Conn): Unit = {
    val conn = conn0.asInstanceOf[JdbcConn_JVM]
    tryInspect("save", save.elements) {
      printInspectTx("SAVE", save.elements, save_getAction(save, conn))
    }
  }

  // Implement for each sql database
  def save_getAction(save: Save, conn: JdbcConn_JVM): SaveAction


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
      if (insert.tpls.isEmpty) {
        TxReport(Nil)
      } else {
        val txReport = conn.transact_sync(insert_getAction(insert, conn))
        conn.callback(insert.elements)
        txReport
      }
    } else {
      throw InsertErrors(errors)
    }
  }
  override def insert_inspect(insert: Insert)(implicit conn: Conn): Unit = {
    tryInspect("insert", insert.elements) {
      val jdbcConn = conn.asInstanceOf[JdbcConn_JVM]
      printInspectTx("INSERT", insert.elements, insert_getAction(insert, jdbcConn), insert.tpls)
    }
  }

  // Implement for each sql database
  def insert_getAction(insert: Insert, conn: JdbcConn_JVM): InsertAction

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
      val action   = update_getAction(update, conn)
      val txReport = conn.transact_sync(action)
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
      //      if (isRefUpdate(update.elements)) {
      //        val (idsModel, updateModels) = prepareMultipleUpdates(update.elements, update.isUpsert)
      //
      //        val refIds =
      //          s"""REF IDS MODEL ----------------
      //             |${idsModel.mkString("\n")}
      //             |
      //             |${refIdsQuery(idsModel, conn.proxy)}
      //             |""".stripMargin
      //
      //        val updates = updateModels
      //          .map(_(42L)) // dummy value
      //          .map { m =>
      //            val elements = m.mkString("\n")
      //            val tables   = update_getData(conn, Update(m, update.isUpsert))._1
      //            tables.headOption.fold(elements)(table => elements + "\n" + table.stmt)
      //          }
      //          .mkString(action + "S ----------------------\n", "\n------------\n", "")
      //
      //        printRaw(action, update.elements, refIds + "\n" + updates)
      //      } else {
      //        printInspectTx(action, update.elements, update_getData(conn, update))
      //      }
      printInspectTx(action, update.elements, update_getAction(update, conn))
    }
  }

  // Implement for each sql database
  def update_getAction(update: Update, conn: JdbcConn_JVM): UpdateAction


  override def update_validate(
    update: Update
  )(implicit conn0: Conn): Map[String, Seq[String]] = {
    val conn            = conn0.asInstanceOf[JdbcConn_JVM]
    val query2resultSet = (query: String) => {
      val ps = conn.sqlConn.prepareStatement(
        query, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY
      )
      conn.resultSet(ps.executeQuery())
    }
    validateUpdateSet(conn.proxy, update.elements, query2resultSet)
  }

  def validateUpdateSet(
    proxy: ConnProxy,
    elements: List[Element],
    query2resultSet: String => Row
  ): Map[String, Seq[String]]


  // Delete --------------------------------------------------------

  override def delete_transact(delete0: Delete)(implicit conn0: Conn): TxReport = {
    val conn   = conn0.asInstanceOf[JdbcConn_JVM]
    val delete = delete0.copy(elements = noKeywords(delete0.elements, Some(conn.proxy)))
    if (delete.doInspect) {
      delete_inspect(delete)
    }

    lazy val action = delete_getAction(conn, delete)
    val txReport = conn.transact_sync(action)
    conn.callback(delete.elements, true)
    txReport
  }

  override def delete_inspect(delete: Delete)(implicit conn0: Conn): Unit = {
    val conn = conn0.asInstanceOf[JdbcConn_JVM]
    tryInspect("delete", delete.elements) {
      printInspectTx("DELETE", delete.elements, delete_getAction(conn, delete))
    }
  }

  def delete_getAction(conn: JdbcConn_JVM, delete: Delete): DeleteAction


  // Inspect --------------------------------------------------------

  private def tryInspect(action: String, elements: List[Element])
                        (body: => Unit): Unit = try {
    body
  } catch {
    case NonFatal(e) =>
      println(s"\n------------------ Error inspecting $action -----------------------")
      elements.foreach(println)
      throw e
  }

  private def printInspectTx(
    label: String,
    elements: List[Element],
    action: SqlAction,
    tpls: Seq[Product] = Nil
  ): Unit = {
    printRaw(label, elements, action.toString, tpls.mkString("\n"))
  }


  // Util --------------------------------------

  def getModel2SqlQuery(elements: List[Element]): Model2SqlQuery with SqlQueryBase

  override def fallback_rawTransact(
    stmt: String,
    doPrint: Boolean = false
  )(implicit conn0: Conn): TxReport = {
    val conn  = conn0.asInstanceOf[JdbcConn_JVM]
    val debug = if (doPrint) (s: String) => println(s) else (_: String) => ()
    debug("\n=============================================================================")
    debug(stmt)

    val ps = conn.sqlConn.prepareStatement(
      stmt, Statement.RETURN_GENERATED_KEYS
    )
    ps.execute()

    val resultSet = ps.getGeneratedKeys // is empty if no nested data
    var ids       = List.empty[Long]
    while (resultSet.next()) {
      ids = ids :+ resultSet.getLong(1)
    }
    ps.close()

    debug("---------------")
    debug("Ids: " + ids)
    TxReport(ids)
  }

  override def fallback_rawQuery(
    query: String,
    debug: Boolean = false,
  )(implicit conn: Conn): List[List[Any]] = {
    val c            = conn.asInstanceOf[JdbcConn_JVM].sqlConn
    val statement    = c.createStatement()
    val resultSet    = statement.executeQuery(query)
    val metaData     = resultSet.getMetaData
    val columnsCount = metaData.getColumnCount
    val rows         = ListBuffer.empty[List[Any]]
    val row          = ListBuffer.empty[Any]
    val types        = new Array[String](columnsCount)
    while (resultSet.next()) {
      var n = 1
      row.clear()
      while (n <= columnsCount) {
        resultSet.getObject(n) match {
          case null => row += null
          case v    =>
            row += v
            types(n - 1) = v.getClass.toString
        }
        n += 1
      }
      rows += row.toList
    }
    if (debug) {
      renderRawQueryData(query, rows, types, metaData)
    }
    resultSet.close()
    rows.toList
  }

  def renderRawQueryData(
    query: String,
    rows: ListBuffer[List[Any]],
    types: Array[String],
    metaData: ResultSetMetaData
  ): Unit = {
    println("\n=============================================================================")
    println(query)
    val max      = 10
    val showRows = rows.length - max match {
      case 1          => rows.take(max) :+ "1 more row..."
      case n if n > 1 => rows.take(max) :+ s"$n more rows..."
      case _          => rows
    }

    val (col, tpe, dbTpe) = ("Column", "Raw type", "Db type")
    var maxCol            = col.length
    var maxTpe            = tpe.length
    var maxDbTpe          = dbTpe.length
    val sep               = 4

    val typeData = types.zipWithIndex.map {
      case (null, i) =>
        val col   = metaData.getColumnName(i + 1)
        val dbTpe = metaData.getColumnTypeName(i + 1)
        (col, "null", dbTpe)

      case (tpe, i) =>
        val col    = metaData.getColumnName(i + 1)
        val dbType = metaData.getColumnTypeName(i + 1)
        maxCol = maxCol.max(col.length)
        maxTpe = maxTpe.max(tpe.length)
        maxDbTpe = maxDbTpe.max(dbType.length)
        (col, tpe, dbType)
    }

    def tpeLine(col: String, tpe: String, dbTpe: String) = {
      println(col + padS(maxCol + sep, col) + tpe + padS(maxTpe + sep, tpe) + dbTpe)
    }

    println(showRows.mkString("List(\n  ", ",\n  ", "\n)\n"))
    tpeLine(col, tpe, dbTpe)
    println("-" * (maxCol + sep + maxTpe + sep + maxDbTpe))
    typeData.foreach {
      case (col, tpe, dbTpe) => tpeLine(col, tpe, dbTpe)
    }
  }
}