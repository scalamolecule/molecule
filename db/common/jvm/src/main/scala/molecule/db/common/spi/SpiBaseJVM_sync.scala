package molecule.db.common.spi

import java.sql.{ResultSet, ResultSetMetaData, Statement}
import geny.Generator
import molecule.base.error.{InsertError, InsertErrors, ModelError, ValidationErrors}
import molecule.base.util.BaseHelpers
import molecule.core.dataModel.{DataModel, Element}
import molecule.db.common.action.*
import molecule.db.common.facade.JdbcConn_JVM
import molecule.db.common.javaSql.{PrepStmtImpl, ResultSetInterface as RS}
import molecule.db.common.marshalling.ConnProxy
import molecule.db.common.query.{SqlQueryResolveCursor, SqlQueryResolveOffset}
import molecule.db.common.query.casting.strategy.{CastOptEntity, CastOptRefs, CastTuple}
import molecule.db.common.spi.{Conn, Renderer, Spi_sync, TxReport}
import molecule.db.common.util.Executor.*
import molecule.db.common.util.FutureUtils
import molecule.db.common.validation.TxModelValidation
import molecule.db.common.validation.insert.InsertValidation
import molecule.db.common.query.{Model2SqlQuery, SqlQueryBase}
import molecule.db.common.transaction.strategy.SqlAction
import molecule.db.common.transaction.strategy.delete.DeleteAction
import molecule.db.common.transaction.strategy.insert.InsertAction
import molecule.db.common.transaction.strategy.save.SaveAction
import molecule.db.common.transaction.strategy.update.UpdateAction
import molecule.db.common.transaction.{CachedConnection, SqlUpdateSetValidator}
import scala.collection.mutable.ListBuffer
import scala.util.control.NonFatal


trait SpiBaseJVM_sync
  extends Spi_sync
    with CachedConnection
    with SpiHelpers
    with SqlUpdateSetValidator
    with Renderer
    with FutureUtils
    with BaseHelpers {


  // Query --------------------------------------------------------

  override def query_get[Tpl](query: Query[Tpl])(implicit conn0: Conn): List[Tpl] = {
    val conn = conn0.asInstanceOf[JdbcConn_JVM]
    if (query.printInspect)
      query_inspect(query)
    val cleanElements  = keywordsSuffixed(query.dataModel.elements, conn.proxy)
    val cleanDataModel = query.dataModel.copy(elements = cleanElements)
    val m2q            = getModel2SqlQuery(cleanElements)
    m2q.bindValues.addAll(query.bindValues)
    SqlQueryResolveOffset[Tpl](cleanDataModel, query.optLimit, None, m2q)
      .getListFromOffset_sync(using conn)._1
  }

  override def query_inspect[Tpl](q: Query[Tpl])(implicit conn: Conn): String = {
    inspectQuery("QUERY", q.dataModel, q.optLimit, None, conn.proxy)
  }


  override def queryOffset_get[Tpl](query: QueryOffset[Tpl])
                                   (implicit conn0: Conn): (List[Tpl], Int, Boolean) = {
    val conn = conn0.asInstanceOf[JdbcConn_JVM]
    if (query.printInspect)
      queryOffset_inspect(query)
    val cleanElements = keywordsSuffixed(query.dataModel.elements, conn.proxy)
    val queryClean    = query.copy(dataModel = query.dataModel.copy(elements = cleanElements))
    val m2q           = getModel2SqlQuery(cleanElements)
    m2q.bindValues.addAll(query.bindValues)
    SqlQueryResolveOffset[Tpl](queryClean.dataModel, queryClean.optLimit, Some(queryClean.offset), m2q)
      .getListFromOffset_sync(using conn)
  }

  override def queryOffset_inspect[Tpl](q: QueryOffset[Tpl])(implicit conn: Conn): String = {
    inspectQuery("QUERY (offset)", q.dataModel, q.optLimit, Some(q.offset), conn.proxy)
  }

  override def queryCursor_get[Tpl](query: QueryCursor[Tpl])
                                   (implicit conn0: Conn): (List[Tpl], String, Boolean) = {
    val conn = conn0.asInstanceOf[JdbcConn_JVM]
    if (query.printInspect)
      queryCursor_inspect(query)
    val cleanElements  = keywordsSuffixed(query.dataModel.elements, conn.proxy)
    val cleanDataModel = query.dataModel.copy(elements = cleanElements)
    val m2q            = getModel2SqlQuery(cleanElements)
    m2q.bindValues.addAll(query.bindValues)
    SqlQueryResolveCursor[Tpl](cleanDataModel, query.optLimit, Some(query.cursor), m2q)
      .getListFromCursor_sync(using conn)
  }

  override def queryCursor_inspect[Tpl](q: QueryCursor[Tpl])(implicit conn: Conn): String = {
    inspectQuery("QUERY (cursor)", q.dataModel, q.optLimit, None, conn.proxy)
  }


  protected def inspectQuery(
    label: String,
    dataModel: DataModel,
    optLimit: Option[Int],
    optOffset: Option[Int],
    proxy: ConnProxy
  ): String = {
    tryInspect("query", dataModel) {
      val elementsClean = keywordsSuffixed(dataModel.elements, proxy)
      val query         = getModel2SqlQuery(elementsClean)
        .getSqlQuery(Nil, optLimit, optOffset, Some(proxy))
      renderInspection(label, dataModel, query)
    }
  }


  // Simple geny Generator stream implementation.
  // Plugs in nicely with the Lihaoyi ecosystem.
  // See https://github.com/com-lihaoyi/geny
  override def query_stream[Tpl](
    q: Query[Tpl], chunkSize: Int
  )(implicit conn0: Conn): Generator[Tpl] = new Generator[Tpl] {
    // callback function
    def generate(handleTuple: Tpl => Generator.Action): Generator.Action = {
      if (q.printInspect)
        query_inspect(q)
      val (rs, rs2row)             = getResultSetAndRowResolver(q, conn0)
      var action: Generator.Action = Generator.Continue
      while (rs.next() && action == Generator.Continue) {
        action = handleTuple(rs2row(rs).asInstanceOf[Tpl])
      }
      action
    }
  }


  override def query_subscribe[Tpl](query: Query[Tpl], callback: List[Tpl] => Unit)
                                   (implicit conn0: Conn): Unit = {
    val conn      = conn0.asInstanceOf[JdbcConn_JVM]
    val elements  = keywordsSuffixed(query.dataModel.elements, conn.proxy)
    val dataModel = query.dataModel.copy(elements = elements)
    conn.addCallback(dataModel, () =>
      callback {
        SqlQueryResolveOffset(dataModel, query.optLimit, None, getModel2SqlQuery(elements))
          .getListFromOffset_sync(using conn)._1
      }
    )
  }

  override def query_unsubscribe[Tpl](query: Query[Tpl])(implicit conn: Conn): Unit = {
    conn.removeCallback(query.dataModel)
  }


  // Save --------------------------------------------------------

  override def save_transact(save: Save)(implicit conn0: Conn): TxReport = {
    val conn = conn0.asInstanceOf[JdbcConn_JVM]
    if (save.printInspect)
      save_inspect(save)
    val cleanElements  = keywordsSuffixed(save.dataModel.elements, conn.proxy)
    val cleanDataModel = save.dataModel.copy(elements = cleanElements)
    val saveClean      = save.copy(dataModel = cleanDataModel)
    val errors         = save_validate(save)
    if (errors.isEmpty) {
      val txReport = conn.transact_sync(save_getAction(saveClean, conn))
      await(conn.callback(cleanDataModel))
      txReport
    } else {
      throw ValidationErrors(errors)
    }
  }

  override def save_inspect(save: Save)(implicit conn0: Conn): String = {
    val conn = conn0.asInstanceOf[JdbcConn_JVM]
    tryInspect("save", save.dataModel) {
      val cleanElements  = keywordsSuffixed(save.dataModel.elements, conn.proxy)
      val cleanDataModel = save.dataModel.copy(elements = cleanElements)
      val saveClean      = save.copy(dataModel = cleanDataModel)
      renderInspectTx("SAVE", save.dataModel, save_getAction(saveClean, conn))
    }
  }

  // Implement for each sql database
  def save_getAction(save: Save, conn: JdbcConn_JVM): SaveAction


  override def save_validate(save: Save)(implicit conn: Conn): Map[String, Seq[String]] = {
    if (save.doValidate) {
      TxModelValidation(conn.proxy.metaDb, "save").validate(save.dataModel.elements)
    } else {
      Map.empty[String, Seq[String]]
    }
  }


  // Insert --------------------------------------------------------

  override def insert_transact(insert: Insert)(implicit conn0: Conn): TxReport = {
    val conn = conn0.asInstanceOf[JdbcConn_JVM]
    if (insert.printInspect)
      insert_inspect(insert)
    val cleanElements  = keywordsSuffixed(insert.dataModel.elements, conn.proxy)
    val cleanDataModel = insert.dataModel.copy(elements = cleanElements)
    val insertClean    = insert.copy(dataModel = cleanDataModel)
    val errors         = insert_validate(insert) // validate original elements against meta model
    if (errors.isEmpty) {
      if (insertClean.tpls.isEmpty) {
        TxReport(Nil)
      } else {
        val txReport = conn.transact_sync(insert_getAction(insertClean, conn))
        await(conn.callback(cleanDataModel))
        txReport
      }
    } else {
      throw InsertErrors(errors)
    }
  }
  override def insert_inspect(insert: Insert)(implicit conn: Conn): String = {
    tryInspect("insert", insert.dataModel) {
      val jdbcConn       = conn.asInstanceOf[JdbcConn_JVM]
      val cleanElements  = keywordsSuffixed(insert.dataModel.elements, conn.proxy)
      val cleanDataModel = insert.dataModel.copy(elements = cleanElements)
      val insertClean    = insert.copy(dataModel = cleanDataModel)
      renderInspectTx("INSERT", insert.dataModel, insert_getAction(insertClean, jdbcConn), insert.tpls)
    }
  }

  // Implement for each sql database
  def insert_getAction(insert: Insert, conn: JdbcConn_JVM): InsertAction

  override def insert_validate(insert: Insert)(implicit conn: Conn): Seq[(Int, Seq[InsertError])] = {
    if (insert.doValidate) {
      InsertValidation.validate(conn, insert.dataModel.elements, insert.tpls)
    } else {
      Seq.empty[(Int, Seq[InsertError])]
    }
  }


  // Update --------------------------------------------------------

  override def update_transact(update: Update)(implicit conn0: Conn): TxReport = {
    val conn = conn0.asInstanceOf[JdbcConn_JVM]
    if (update.printInspect)
      update_inspect(update)
    val cleanElements  = keywordsSuffixed(update.dataModel.elements, conn.proxy)
    val cleanDataModel = update.dataModel.copy(elements = cleanElements)
    val updateClean    = update.copy(dataModel = cleanDataModel)
    val errors         = update_validate(update) // validate original elements against meta model
    if (errors.isEmpty) {
      val action   = update_getAction(updateClean, conn)
      val txReport = conn.transact_sync(action)
      await(conn.callback(cleanDataModel))
      txReport
    } else {
      throw ValidationErrors(errors)
    }
  }

  override def update_inspect(update: Update)(implicit conn0: Conn): String = {
    val conn   = conn0.asInstanceOf[JdbcConn_JVM]
    val action = if (update.isUpsert) "UPSERT" else "UPDATE"
    tryInspect(action, update.dataModel) {
      val cleanElements  = keywordsSuffixed(update.dataModel.elements, conn.proxy)
      val cleanDataModel = update.dataModel.copy(elements = cleanElements)
      val updateClean    = update.copy(dataModel = cleanDataModel)
      renderInspectTx(action, update.dataModel, update_getAction(updateClean, conn))
    }
  }

  // Implement for each sql database
  def update_getAction(update: Update, conn: JdbcConn_JVM): UpdateAction


  override def update_validate(
    update: Update
  )(implicit conn0: Conn): Map[String, Seq[String]] = {
    if (update.doValidate) {
      val conn            = conn0.asInstanceOf[JdbcConn_JVM]
      val query2resultSet = (query: String) => {
        val ps = conn.sqlConn.prepareStatement(
          query, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY
        )
        conn.resultSet(ps.executeQuery())
      }
      validateUpdateSet(conn.proxy, update.dataModel.elements, query2resultSet)
    } else {
      Map.empty[String, Seq[String]]
    }
  }

  def validateUpdateSet(
    proxy: ConnProxy,
    elements: List[Element],
    query2resultSet: String => RS
  ): Map[String, Seq[String]]


  // Delete --------------------------------------------------------

  override def delete_transact(delete: Delete)(implicit conn0: Conn): TxReport = {
    val conn = conn0.asInstanceOf[JdbcConn_JVM]
    if (delete.printInspect)
      delete_inspect(delete)
    val cleanElements  = keywordsSuffixed(delete.dataModel.elements, conn.proxy)
    val cleanDataModel = delete.dataModel.copy(elements = cleanElements)
    val deleteClean    = delete.copy(dataModel = cleanDataModel)
    val action         = delete_getAction(deleteClean, conn)
    val txReport       = conn.transact_sync(action)
    await(conn.callback(cleanDataModel, true))
    txReport
  }

  override def delete_inspect(delete: Delete)(implicit conn0: Conn): String = {
    val conn = conn0.asInstanceOf[JdbcConn_JVM]
    tryInspect("delete", delete.dataModel) {
      val cleanElements  = keywordsSuffixed(delete.dataModel.elements, conn.proxy)
      val cleanDataModel = delete.dataModel.copy(elements = cleanElements)
      val deleteClean    = delete.copy(dataModel = cleanDataModel)
      renderInspectTx("DELETE", delete.dataModel, delete_getAction(deleteClean, conn))
    }
  }

  def delete_getAction(delete: Delete, conn: JdbcConn_JVM): DeleteAction = ???


  // Inspect --------------------------------------------------------

  private def tryInspect(action: String, dataModel: DataModel)
                        (body: => String): String = try {
    body
  } catch {
    case NonFatal(e) =>
      println(
        s"""
           |------------------ Error inspecting $action -----------------------
           |$dataModel""".stripMargin)
      throw e
  }

  private def renderInspectTx(
    label: String,
    dataModel: DataModel,
    action: SqlAction,
    tpls: Seq[Product] = Nil
  ): String = {
    renderInspection(label, dataModel, action.toString, tpls.mkString("\n"))
  }


  // Fallback --------------------------------------

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


  // Util --------------------------------------

  def getModel2SqlQuery(elements: List[Element]): Model2SqlQuery & SqlQueryBase


  def getResultSetAndRowResolver[Tpl](q0: Query[Tpl], conn0: Conn): (RS, RS => Any) = {
    val conn          = conn0.asInstanceOf[JdbcConn_JVM]
    val cleanElements = keywordsSuffixed(q0.dataModel.elements, conn.proxy)
    val queryClean    = q0.dataModel.copy(elements = cleanElements)
    val m2q           = getModel2SqlQuery(queryClean.elements)
    val castStrategy  = m2q.castStrategy match {
      case c: CastTuple     => c
      case c: CastOptRefs   => c
      case c: CastOptEntity => c
      case _                => throw ModelError("Nested data not allowed for streaming.")
    }
    val query         = m2q.getSqlQuery(Nil, None, None, Some(conn.proxy))
    val ps            = conn.queryStmt(query)
    val ps1           = new PrepStmtImpl(ps)
    val inputs        = m2q.binders.toList
    inputs.foreach(_(ps1)) // set input values corresponding to '?' in queries
    (conn.resultSet(ps.executeQuery()), castStrategy.rs2row)
  }

  private def renderRawQueryData(
    query: String,
    rows: ListBuffer[List[Any]],
    types: Array[String],
    metaData: ResultSetMetaData
  ): Unit = {
    println("\n=============================================================================")
    println(query.trim)
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

    println("\n" + showRows.mkString("List(\n  ", ",\n  ", "\n)\n"))
    tpeLine(col, tpe, dbTpe)
    println("-" * (maxCol + sep + maxTpe + sep + maxDbTpe))
    typeData.foreach {
      case (col, tpe, dbTpe) => tpeLine(col, tpe, dbTpe)
    }
  }
}