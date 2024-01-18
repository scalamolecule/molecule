package molecule.document.mongodb.spi

import molecule.base.error._
import molecule.base.util.BaseHelpers
import molecule.boilerplate.ast.Model._
import molecule.core.action._
import molecule.core.marshalling.dbView.{AsOf, DbView, Since}
import molecule.core.spi._
import molecule.core.transaction.{ResolveDelete, ResolveInsert, ResolveSave, ResolveUpdate}
import molecule.core.util.ModelUtils
import molecule.core.validation.TxModelValidation
import molecule.core.validation.insert.InsertValidation
import molecule.document.mongodb.facade.MongoConn_JVM
import molecule.document.mongodb.query.{Model2MongoQuery, QueryResolveCursor_mongodb, QueryResolveOffset_mongodb}
import molecule.document.mongodb.transaction._
import molecule.document.mongodb.util.BsonUtils
import org.bson._
import scala.annotation.nowarn
import scala.collection.mutable.ListBuffer
import scala.util.control.NonFatal


object SpiSync_mongodb extends SpiSync_mongodb

trait SpiSync_mongodb
  extends SpiSync
    with Base_JVM_mongodb
    with ModelUtils
    with Renderer
    with BaseHelpers
    with BsonUtils {

  // Query --------------------------------------------------------

  override def query_get[Tpl](q: Query[Tpl])(implicit conn0: Conn): List[Tpl] = {
    val conn = conn0.asInstanceOf[MongoConn_JVM]

//    q.elements.foreach(println)
//    println("================================================================ 2")

    if (q.doInspect)
      query_inspect(q)
    q.dbView.foreach(noTime)
    val m2q = new Model2MongoQuery[Tpl](q.elements)
    QueryResolveOffset_mongodb[Tpl](q.elements, q.optLimit, None, m2q)
      .getListFromOffset_sync(conn)._1
  }

  def query_getRaw[Tpl](q: Query[Tpl])(implicit conn0: Conn): List[Tpl] = {
    val conn = conn0.asInstanceOf[MongoConn_JVM]
    val m2q  = new Model2MongoQuery[Tpl](q.elements)
    QueryResolveOffset_mongodb[Tpl](q.elements, q.optLimit, None, m2q)
      .getListFromOffset_sync(conn)._1
  }

  private def noTime(dbView: DbView): Unit = dbView match {
    case _: AsOf  => throw ModelError("Time function 'asOf' is only implemented for Datomic.")
    case _: Since => throw ModelError("Time function 'since' is only implemented for Datomic.")
  }

  override def query_subscribe[Tpl](q: Query[Tpl], callback: List[Tpl] => Unit)(implicit conn0: Conn): Unit = {
    val conn = conn0.asInstanceOf[MongoConn_JVM]
    val m2q  = new Model2MongoQuery[Tpl](q.elements)
    QueryResolveOffset_mongodb[Tpl](q.elements, q.optLimit, None, m2q)
      .subscribe(conn, callback, (elements: List[Element]) => new Model2MongoQuery[Tpl](elements))
  }
  override def query_unsubscribe[Tpl](q: Query[Tpl])(implicit conn0: Conn): Unit = {
    val conn = conn0.asInstanceOf[MongoConn_JVM]
    val m2q  = new Model2MongoQuery[Tpl](q.elements)
    QueryResolveOffset_mongodb[Tpl](q.elements, q.optLimit, None, m2q)
      .unsubscribe(conn)
  }

  override def query_inspect[Tpl](q: Query[Tpl])(implicit conn: Conn): Unit = {
    printInspectQuery("QUERY", q.elements, q.optLimit, None)
  }


  override def queryOffset_get[Tpl](q: QueryOffset[Tpl])(implicit conn0: Conn): (List[Tpl], Int, Boolean) = {
    val conn = conn0.asInstanceOf[MongoConn_JVM]
    if (q.doInspect)
      queryOffset_inspect(q)
    q.dbView.foreach(noTime)
    val m2q = new Model2MongoQuery[Tpl](q.elements)
    QueryResolveOffset_mongodb[Tpl](q.elements, q.optLimit, Some(q.offset), m2q)
      .getListFromOffset_sync(conn)
  }

  override def queryOffset_inspect[Tpl](q: QueryOffset[Tpl])(implicit conn: Conn): Unit = {
    printInspectQuery("QUERY (offset)", q.elements, q.optLimit, Some(q.offset))
  }

  override def queryCursor_get[Tpl](q: QueryCursor[Tpl])(implicit conn0: Conn): (List[Tpl], String, Boolean) = {
    val conn = conn0.asInstanceOf[MongoConn_JVM]
    if (q.doInspect)
      queryCursor_inspect(q)
    q.dbView.foreach(noTime)
    val m2q = new Model2MongoQuery[Tpl](q.elements)
    QueryResolveCursor_mongodb[Tpl](q.elements, q.optLimit, Some(q.cursor), m2q)
      .getListFromCursor_sync(conn)
  }

  override def queryCursor_inspect[Tpl](q: QueryCursor[Tpl])(implicit conn: Conn): Unit = {
    printInspectQuery("QUERY (cursor)", q.elements, q.optLimit, None)
  }

  private def printInspectQuery(
    label: String,
    elements: List[Element],
    optLimit: Option[Int],
    optOffset: Option[Int]
  ): Unit = {
    tryInspect("query", elements) {
      val (ns, pipeline) = new Model2MongoQuery[Any](elements).getBsonQuery(Nil, optLimit, optOffset)
      //      val (elements1, pipeline) = new Model2MongoQuery[Any](elements).getBsonQuery(Nil, optLimit, optOffset, None)
      //      val (elements1, pipeline) = new Model2MongoQuery[Any](elements).getBsonQuery(Nil, optLimit, optOffset)

//      printRaw(label, elements, pipeline2json(pipeline, Some(ns)))
      printRaw(label, Nil, pipeline2json(pipeline, Some(ns)))

      //      val (ns, pipeline) = getModel2SqlQuery[Any](elements).getBsonQuery(Nil, optLimit, optOffset, None)
      //      val (_, pipeline2) = getModel2SqlQuery[Any](elements).getBsonQuery2(Nil, optLimit, optOffset, None)
      //      diff(pipeline2json(pipeline, Some(ns)), pipeline2json(pipeline2, Some(ns)))
    }
  }


  // Save --------------------------------------------------------

  override def save_transact(save: Save)(implicit conn0: Conn): TxReport = {
    val conn = conn0.asInstanceOf[MongoConn_JVM]
    if (save.doInspect)
      save_inspect(save)
    val errors = save_validate(save)
    if (errors.isEmpty) {
      val txReport = conn.transact_sync(save_getData(save))
      conn.callback(save.elements)
      txReport
    } else {
      throw ValidationErrors(errors)
    }
  }

  override def save_inspect(save: Save)(implicit conn0: Conn): Unit = {
    //    val conn = conn0.asInstanceOf[MongoConn_JVM]
    tryInspect("save", save.elements) {
      printInspectTx("SAVE", save.elements, save_getData(save))
    }
  }

  private def save_getData(save: Save): Data = {
    new ResolveSave with Save_mongodb().getData(save.elements)
  }

  override def save_validate(save: Save)(implicit conn: Conn): Map[String, Seq[String]] = {
    val proxy = conn.proxy
    TxModelValidation(proxy.nsMap, proxy.attrMap, "save").validate(save.elements)
  }


  // Insert --------------------------------------------------------

  override def insert_transact(insert0: Insert)(implicit conn0: Conn): TxReport = {
    val conn   = conn0.asInstanceOf[MongoConn_JVM]
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

  override def insert_inspect(insert: Insert)(implicit conn0: Conn): Unit = {
    tryInspect("insert", insert.elements) {
      val conn = conn0.asInstanceOf[MongoConn_JVM]
      //      printInspectTx("INSERT", insert.elements, insert_getData(insert, conn), insert.tpls)
      printInspectTx("INSERT", Nil, insert_getData(insert, conn), insert.tpls)
    }
  }

  // Implement for each sql database
  def insert_getData(insert: Insert, conn: MongoConn_JVM): Data = {
    new ResolveInsert with Insert_mongodb().getData(conn.proxy.nsMap, insert.elements, insert.tpls)
  }

  override def insert_validate(insert: Insert)(implicit conn: Conn): Seq[(Int, Seq[InsertError])] = {
    InsertValidation.validate(conn, insert.elements, insert.tpls)
  }


  // Update --------------------------------------------------------

  override def update_transact(update0: Update)(implicit conn0: Conn): TxReport = {
    val conn   = conn0.asInstanceOf[MongoConn_JVM]
    val update = update0.copy(elements = noKeywords(update0.elements, Some(conn.proxy)))
    if (update.doInspect)
      update_inspect(update)

    //    TxModelValidation(conn.proxy.nsMap, conn.proxy.attrMap, "update", Some(curSetValues)).validate(elements)
    TxModelValidation(conn.proxy.nsMap, conn.proxy.attrMap, "update", None).validate(update.elements)

    val errors = update_validate(update0) // validate original elements against meta model
    //    val errors = Map.empty[String, Seq[String]]
    if (errors.isEmpty) {
      //      val txReport = if (isRefUpdate(update.elements)) {
      //        // Atomic transaction with updates for each ref namespace
      //        conn.atomicTransaction(refUpdates(update)(conn))
      //      } else {
      //        conn.transact_sync(update_getData(conn, update))
      //      }
      val txReport = conn.transact_sync(update_getData(conn, update))
      conn.callback(update.elements)
      txReport
    } else {
      throw ValidationErrors(errors)
    }
  }

  //  def refIdsQuery(idsModel: List[Element], proxy: ConnProxy): String = {
  //    //    new Model2MongoQuery(idsModel).getBsonQuery(Nil, None, None, Some(proxy))
  //    ???
  //  }

  override def update_inspect(update: Update)(implicit conn0: Conn): Unit = {
    val conn   = conn0.asInstanceOf[MongoConn_JVM]
    val action = if (update.isUpsert) "UPSERT" else "UPDATE"
    //    tryInspect(action, update.elements) {
    //      if (isRefUpdate(update.elements)) {
    //        val (idsModel, updateModels) = prepareMultipleUpdates(update.elements, update.isUpsert)
    //        val refIds                   =
    //          s"""REF IDS MODEL ----------------
    //             |${idsModel.mkString("\n")}
    //             |
    //             |${refIdsQuery(idsModel, conn.proxy)}
    //             |""".stripMargin
    //        val updates                  =
    //          updateModels
    //            .map(_(42))
    //            .map { m =>
    //              val elements = m.mkString("\n")
    //              val tables   = update_getData(conn, m, update.isUpsert)._1
    //              tables.headOption.fold(elements)(table => elements + "\n" + table.stmt)
    //            }
    //            .mkString(action + "S ----------------------\n", "\n------------\n", "")
    //
    //        printRaw(action, update.elements, refIds + "\n" + updates)
    //      } else {
    //        printInspectTx(action, update.elements, update_getData(conn, update))
    //      }
    //    }
    ???
  }

  def update_getData(conn: MongoConn_JVM, update: Update): Data = {
    new ResolveUpdate(conn.proxy, update.isUpsert) with Update_mongodb {}.getData(update.elements, conn)
  }

  //  def update_getData(conn: MongoConn_JVM, elements: List[Element], isUpsert: Boolean): Data = {
  //    new ResolveUpdate(conn.proxy, isUpsert) with Update_mongodb {}.getData(elements, conn)
  //  }

  override def update_validate(update: Update)(implicit conn0: Conn): Map[String, Seq[String]] = {
    val conn = conn0.asInstanceOf[MongoConn_JVM]
    if (update.isUpsert && isRefUpdate(update.elements))
      throw ModelError("Can't upsert referenced attributes. Please update instead.")

    //    val resolver = (query: String) => {
    //      val ps        = conn.sqlConn.prepareStatement(
    //        query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY
    //      )
    //      val resultSet = ps.executeQuery()
    //      new ResultSetImpl(resultSet)
    //    }

    // todo
    //     validateUpdateSet(conn.proxy, update.elements, update.isUpsert, resolver)
    Map.empty[String, Seq[String]]
  }


  // Delete --------------------------------------------------------

  override def delete_transact(delete: Delete)(implicit conn0: Conn): TxReport = {
    val conn = conn0.asInstanceOf[MongoConn_JVM]
    if (delete.doInspect)
      delete_inspect(delete)
    val txReport = conn.transact_sync(delete_getData(conn, delete))
    conn.callback(delete.elements, true)
    txReport
  }

  override def delete_inspect(delete: Delete)(implicit conn0: Conn): Unit = {
    val conn = conn0.asInstanceOf[MongoConn_JVM]
    tryInspect("delete", delete.elements) {
      printRaw("DELETE", delete.elements, delete_getData(conn, delete).toJson(pretty))
    }
  }

  //  private def delete_getData(conn: MongoConn_JVM, delete: Delete): Option[Bson] = {
  private def delete_getData(conn: MongoConn_JVM, delete: Delete): Data = {
    new ResolveDelete with Delete_mongodb().getData(delete.elements, conn)
  }


  // Inspect --------------------------------------------------------

  private def tryInspect(action: String, elements: List[Element])(body: => Unit): Unit = try {
    body
  } catch {
    case NonFatal(e) =>
      println(s"\n------------------ Error inspecting $action -----------------------")
      elements.foreach(println)
      throw e
  }

  private def printInspectTx(label: String, elements: List[Element], data: Data, tpls: Seq[Product] = Nil): Unit = {
    //    printRaw(label, elements, data2json(data), tpls.mkString("\n"))
    //    printRaw(label, elements, data._2.toJson(pretty), tpls.mkString("\n"))
    printRaw(label, elements, data.toJson(pretty), tpls.mkString("\n"))
  }


  // Util --------------------------------------

  @nowarn // Accept dynamic type parameter of returned Query
  private def refUpdates(update: Update)(implicit conn: MongoConn_JVM): () => Map[List[String], List[Long]] = {
    //    val (idQuery, updateModels) = getIdQuery(update.elements, update.isUpsert)
    //    val refIds: List[Long]      = getRefIds(query_getRaw(idQuery))
    //    () => {
    //      val refIdMaps = refIds.zipWithIndex.map {
    //        case (refId: Long, i) =>
    //          val updateModel = updateModels(i)(refId)
    //          conn.populateStmts(update_getData(conn, updateModel, update.isUpsert))
    //      }
    //      // Return TxReport with initial update ids
    //      refIdMaps.head
    //    }
    ???
  }


  override def fallback_rawTransact(json: String, print: Boolean = false)(implicit conn: Conn): TxReport = {
    if (print) {
      println("\nRAW TRANSACT ======================================================")
      println(json)
    }
    conn.asInstanceOf[MongoConn_JVM].transact_sync(json2data(json, conn.proxy.nsMap))
  }

  override def fallback_rawQuery(
    query: String,
    debugFlag: Boolean = false,
  )(implicit conn: Conn): List[List[Any]] = {
    val (ns, pipeline) = json2pipeline(query)
    val collection     = conn.asInstanceOf[MongoConn_JVM].mongoDb.getCollection(ns, classOf[BsonDocument])
    val bsonDocs       = collection.aggregate(pipeline)
    val rows           = ListBuffer.empty[List[Any]]
    val row            = ListBuffer.empty[Any]
    val cast           = caster(conn.proxy.nsMap.get(ns))

    val debug = if (debugFlag) (s: String) => println(s) else (_: String) => ()
    debug("\nRAW QUERY RESULT ==================================================")

    bsonDocs.forEach { doc =>
      debug(doc.toJson(pretty))
      row.clear
      val docIterator = doc.asDocument().entrySet().iterator()
      while (docIterator.hasNext) {
        val pair           = docIterator.next()
        val (field, value) = (pair.getKey, pair.getValue)
        row += cast(field, value)
      }
      rows += row.toList
    }
    debug("")
    rows.toList
  }
}