package molecule.document.mongodb.spi

import java.util
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
import molecule.document.mongodb.query.{LambdasSet, Model2MongoQuery, QueryResolveCursor_mongodb, QueryResolveOffset_mongodb}
import molecule.document.mongodb.transaction._
import molecule.document.mongodb.util.BsonUtils
import org.bson._
import org.bson.conversions.Bson
import scala.collection.mutable.ListBuffer
import scala.util.control.NonFatal


object SpiSync_mongodb extends SpiSync_mongodb

trait SpiSync_mongodb
  extends SpiSync
    with Base_JVM_mongodb
    with LambdasSet
    with ModelUtils
    with Renderer
    with BaseHelpers
    with BsonUtils {

  // Query --------------------------------------------------------

  override def query_get[Tpl](q: Query[Tpl])(implicit conn0: Conn): List[Tpl] = {
    val conn = conn0.asInstanceOf[MongoConn_JVM]
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
      printRaw(label, Nil, pipeline2json(pipeline, Some(ns)))
    }
  }


  // Save --------------------------------------------------------

  override def save_transact(save0: Save)(implicit conn0: Conn): TxReport = {
    val conn = conn0.asInstanceOf[MongoConn_JVM]
    val save = save0.copy(elements = noKeywords(save0.elements, Some(conn.proxy)))
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
      printInspectTx("INSERT", insert.elements, insert_getData(insert, conn), insert.tpls)
    }
  }

  // Implement for each sql database
  private def insert_getData(insert: Insert, conn: MongoConn_JVM): Data = {
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
    val errors = update_validate(update0) // validate original elements against meta model
    if (errors.isEmpty) {
      val txReport = conn.transact_sync(update_getData(conn, update))
      conn.callback(update.elements)
      txReport
    } else {
      throw ValidationErrors(errors)
    }
  }

  override def update_inspect(update: Update)(implicit conn0: Conn): Unit = {
    val conn   = conn0.asInstanceOf[MongoConn_JVM]
    val action = if (update.isUpsert) "UPSERT" else "UPDATE"
    tryInspect(action, update.elements)(
      printInspectTx(action, update.elements, update_getData(conn, update))
    )
  }

  private def update_getData(conn: MongoConn_JVM, update: Update): Data = {
    new ResolveUpdate(conn.proxy, update.isUpsert) with Update_mongodb {}.getData(update.elements, conn)
  }

  override def update_validate(update: Update)(implicit conn0: Conn): Map[String, Seq[String]] = {
    if (update.isUpsert && isRefUpdate(update.elements)) {
      throw ModelError("Can't upsert referenced attributes. Please update instead.")
    }
    val conn = conn0.asInstanceOf[MongoConn_JVM]
    TxModelValidation(
      conn.proxy.nsMap,
      conn.proxy.attrMap,
      "update",
      Some(getCurSetValues(conn))
    ).validate(update.elements)
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
    printRaw(label, elements, data.toJson(pretty), tpls.mkString("\n"))
  }


  // Util --------------------------------------

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
      row.clear()
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

  private def getCurSetValues(conn: MongoConn_JVM): Attr => Set[Any] = (attr: Attr) => {
    try {
      val field = attr.cleanAttr

      val pipeline = new util.ArrayList[Bson]()
      val and      = new BsonArray()
      and.add(new BsonDocument(field, new BsonDocument("$ne", new BsonNull())))
      and.add(new BsonDocument(field, new BsonDocument("$ne", new BsonArray())))
      pipeline.add(new BsonDocument("$match", new BsonDocument("$and", and)))

      val collectionName = attr.cleanNs
      val collection     = conn.mongoDb.getCollection(collectionName, classOf[BsonDocument])
      val it             = collection.aggregate(pipeline).iterator
      if (it.hasNext) {
        val caster = attr match {
          case a: AttrSetMan => a match {
            case _: AttrSetManID             => castSetID
            case _: AttrSetManString         => castSetString
            case _: AttrSetManInt            => castSetInt
            case _: AttrSetManLong           => castSetLong
            case _: AttrSetManFloat          => castSetFloat
            case _: AttrSetManDouble         => castSetDouble
            case _: AttrSetManBoolean        => castSetBoolean
            case _: AttrSetManBigInt         => castSetBigInt
            case _: AttrSetManBigDecimal     => castSetBigDecimal
            case _: AttrSetManDate           => castSetDate
            case _: AttrSetManDuration       => castSetDuration
            case _: AttrSetManInstant        => castSetInstant
            case _: AttrSetManLocalDate      => castSetLocalDate
            case _: AttrSetManLocalTime      => castSetLocalTime
            case _: AttrSetManLocalDateTime  => castSetLocalDateTime
            case _: AttrSetManOffsetTime     => castSetOffsetTime
            case _: AttrSetManOffsetDateTime => castSetOffsetDateTime
            case _: AttrSetManZonedDateTime  => castSetZonedDateTime
            case _: AttrSetManUUID           => castSetUUID
            case _: AttrSetManURI            => castSetURI
            case _: AttrSetManByte           => castSetByte
            case _: AttrSetManShort          => castSetShort
            case _: AttrSetManChar           => castSetChar
          }
          case a: AttrSetTac => a match {
            case _: AttrSetTacID             => castSetID
            case _: AttrSetTacString         => castSetString
            case _: AttrSetTacInt            => castSetInt
            case _: AttrSetTacLong           => castSetLong
            case _: AttrSetTacFloat          => castSetFloat
            case _: AttrSetTacDouble         => castSetDouble
            case _: AttrSetTacBoolean        => castSetBoolean
            case _: AttrSetTacBigInt         => castSetBigInt
            case _: AttrSetTacBigDecimal     => castSetBigDecimal
            case _: AttrSetTacDate           => castSetDate
            case _: AttrSetTacDuration       => castSetDuration
            case _: AttrSetTacInstant        => castSetInstant
            case _: AttrSetTacLocalDate      => castSetLocalDate
            case _: AttrSetTacLocalTime      => castSetLocalTime
            case _: AttrSetTacLocalDateTime  => castSetLocalDateTime
            case _: AttrSetTacOffsetTime     => castSetOffsetTime
            case _: AttrSetTacOffsetDateTime => castSetOffsetDateTime
            case _: AttrSetTacZonedDateTime  => castSetZonedDateTime
            case _: AttrSetTacUUID           => castSetUUID
            case _: AttrSetTacURI            => castSetURI
            case _: AttrSetTacByte           => castSetByte
            case _: AttrSetTacShort          => castSetShort
            case _: AttrSetTacChar           => castSetChar
          }
          case _             => ???
        }
        caster(field)(it.next).asInstanceOf[Set[Any]]
      } else Set.empty[Any]
    } catch {
      case e: MoleculeError => throw e
      case _: Throwable     => throw ExecutionError(
        s"Unexpected error trying to find current values of mandatory attribute ${attr.name}")
    }
  }
}