package molecule.datalog.datomic.spi

import java.util.List as jList
import java.util.stream.Stream as jStream
import datomic.Peer
import geny.Generator
import molecule.base.error.{InsertError, ModelError}
import molecule.core.action.*
import molecule.core.ast.DataModel.*
import molecule.core.spi.{Conn, Spi_sync, TxReport}
import molecule.core.transaction.{ResolveDelete, ResolveInsert, ResolveSave, ResolveUpdate}
import molecule.core.util.Executor.*
import molecule.core.util.FutureUtils
import molecule.core.validation.TxModelValidation
import molecule.core.validation.insert.InsertValidation
import molecule.datalog.core.query.Model2DatomicQuery
import molecule.datalog.datomic.facade.DatomicConn_JVM
import molecule.datalog.datomic.query.{DatomicQueryResolveCursor, DatomicQueryResolveOffset}
import molecule.datalog.datomic.transaction.*
import scala.concurrent.Await
import scala.concurrent.duration.DurationInt

object Spi_datomic_sync extends Spi_datomic_sync

trait Spi_datomic_sync
  extends Spi_sync
    with SpiBase_datomic_sync
    with DatomicDataType_JVM
    with JVMDatomicSpiBase
    with FutureUtils {

  override def query_get[Tpl](q: Query[Tpl])(implicit conn: Conn): List[Tpl] = {
    if (q.doInspect) query_inspect(q)
    val m2q = new Model2DatomicQuery[Tpl](q.elements)
    DatomicQueryResolveOffset[Tpl](
      q.elements, q.optLimit, None, q.dbView, m2q
    ).getListFromOffset_sync(conn.asInstanceOf[DatomicConn_JVM])._1
  }

  override def query_inspect[Tpl](q: Query[Tpl])(implicit conn: Conn): Unit = {
    printInspectQuery("QUERY", q.elements)
  }

  override def queryOffset_get[Tpl](
    q: QueryOffset[Tpl]
  )(implicit conn: Conn): (List[Tpl], Int, Boolean) = {
    if (q.doInspect) queryOffset_inspect(q)
    val m2q = new Model2DatomicQuery[Tpl](q.elements)
    DatomicQueryResolveOffset[Tpl](
      q.elements, q.optLimit, Some(q.offset), q.dbView, m2q
    ).getListFromOffset_sync(conn.asInstanceOf[DatomicConn_JVM])
  }

  override def queryOffset_inspect[Tpl](
    q: QueryOffset[Tpl]
  )(implicit conn: Conn): Unit = {
    printInspectQuery("QUERY (offset)", q.elements)
  }

  override def queryCursor_get[Tpl](
    q: QueryCursor[Tpl]
  )(implicit conn: Conn): (List[Tpl], String, Boolean) = {
    if (q.doInspect) queryCursor_inspect(q)
    val m2q = new Model2DatomicQuery[Tpl](q.elements)
    DatomicQueryResolveCursor[Tpl](
      q.elements, q.optLimit, Some(q.cursor), q.dbView, m2q
    ).getListFromCursor_sync(conn.asInstanceOf[DatomicConn_JVM])
  }

  override def queryCursor_inspect[Tpl](
    q: QueryCursor[Tpl]
  )(implicit conn: Conn): Unit = {
    printInspectQuery("QUERY (cursor)", q.elements)
  }


  // Simple geny Generator stream implementation.
  // Plugs in nicely with the Lihaoyi ecosystem.
  // See https://github.com/com-lihaoyi/geny
  override def query_stream[Tpl](
    q: Query[Tpl], chunkSize: Int
  )(implicit conn0: Conn): Generator[Tpl] = new Generator[Tpl] {
    // callback function
    def generate(handleTuple: Tpl => Generator.Action): Generator.Action = {
      if (q.doInspect)
        query_inspect(q)
      val (stream, row2tpl)        = getJavaStreamAndRowResolver(q, conn0)
      val it                       = stream.iterator()
      var action: Generator.Action = Generator.Continue
      while (it.hasNext && action == Generator.Continue) {
        action = handleTuple(row2tpl(it.next()).asInstanceOf[Tpl])
      }
      action
    }
  }


  override def query_subscribe[Tpl](
    q: Query[Tpl], callback: List[Tpl] => Unit
  )(implicit conn: Conn): Unit = {
    val datomicConn = conn.asInstanceOf[DatomicConn_JVM]
    val m2q         = new Model2DatomicQuery[Tpl](q.elements)
    DatomicQueryResolveOffset[Tpl](
      q.elements, q.optLimit, None, q.dbView, m2q
    ).subscribe(datomicConn, callback)
  }

  override def query_unsubscribe[Tpl](
    q: Query[Tpl]
  )(implicit conn: Conn): Unit = {
    val datomicConn = conn.asInstanceOf[DatomicConn_JVM]
    val m2q         = new Model2DatomicQuery[Tpl](q.elements)
    DatomicQueryResolveOffset[Tpl](q.elements, q.optLimit, None, q.dbView, m2q)
      .unsubscribe(datomicConn)
  }


  override def save_transact(save: Save)(implicit conn: Conn): TxReport = {
    await(Spi_datomic_async.save_transact(save)(conn, global))
  }

  override def save_inspect(save: Save)(implicit conn: Conn): Unit = {
    printInspectTx("SAVE", save.elements, save_getStmts(save))
  }

  override def save_validate(
    save: Save
  )(implicit conn: Conn): Map[String, Seq[String]] = {
    if (save.doValidate) {
      val proxy = conn.proxy
      TxModelValidation(proxy.entityMap, proxy.attrMap, "save")
        .validate(save.elements)
    } else {
      Map.empty[String, Seq[String]]
    }
  }

  def save_getStmts(save: Save): Data = {
    (new ResolveSave with Save_datomic).getStmts(save.elements)
  }

  override def insert_transact(insert: Insert)(implicit conn: Conn): TxReport = {
    await(Spi_datomic_async.insert_transact(insert)(conn, global))
  }

  override def insert_inspect(insert: Insert)(implicit conn: Conn): Unit = {
    printInspectTx(
      "INSERT",
      insert.elements, insert_getStmts(insert)
    )
  }

  override def insert_validate(
    insert: Insert
  )(implicit conn: Conn): Seq[(Int, Seq[InsertError])] = {
    if (insert.doValidate) {
      InsertValidation.validate(conn, insert.elements, insert.tpls)
    } else {
      Seq.empty[(Int, Seq[InsertError])]
    }
  }

  def insert_getStmts(insert: Insert): Data = {
    (new ResolveInsert with Insert_datomic)
      .getStmts(insert.elements, insert.tpls)
  }

  override def update_transact(update: Update)(implicit conn: Conn): TxReport = {
    await(Spi_datomic_async.update_transact(update)(conn, global))
  }

  override def update_inspect(update: Update)(implicit conn: Conn): Unit = {
    val action = if (update.isUpsert) "UPSERT" else "UPDATE"
    printInspectTx(action, update.elements,
      update_getStmts(update, conn.asInstanceOf[DatomicConn_JVM]))
  }

  override def update_validate(
    update: Update
  )(implicit conn: Conn): Map[String, Seq[String]] = {
    validateUpdate(conn, update)
  }

  def update_getStmts(update: Update, conn: DatomicConn_JVM): Data = {
    new ResolveUpdate with Update_datomic {
      override val isUpsert: Boolean = update.isUpsert
    }.getStmts(conn, update.elements)
  }

  override def delete_transact(delete: Delete)(implicit conn: Conn): TxReport = {
    await(Spi_datomic_async.delete_transact(delete)(conn, global))
  }

  override def delete_inspect(delete: Delete)(implicit conn: Conn): Unit = {
    printInspectTx("DELETE", delete.elements,
      delete_getStmts(delete, conn.asInstanceOf[DatomicConn_JVM]))
  }

  def delete_getStmts(delete: Delete, conn: DatomicConn_JVM): Data = {
    (new ResolveDelete with Delete_datomic)
      .getData(conn, delete.elements)
  }


  // Fallbacks --------------------------------------------------------

  override def fallback_rawTransact(
    txData: String, debug: Boolean = false
  )(implicit conn: Conn): TxReport = {
    try {
      import molecule.core.util.Executor.global
      Await.result(
        Spi_datomic_async.fallback_rawTransact(txData, debug)(conn, global),
        10.seconds
      )
    } catch {
      case t: Throwable => throw ModelError(t.toString)
    }
  }

  override def fallback_rawQuery(
    query: String, debug: Boolean = false,
  )(implicit conn: Conn): List[List[Any]] = {
    val rows = Peer.q(query, conn.db.asInstanceOf[AnyRef])
      .asScala.toList.map(_.asScala.toList.map(toScala(_)))
    if (debug) {
      renderRawQueryData(query, rows)
    }
    rows
  }

  def renderRawQueryData(
    query: String,
    rows: List[List[Any]],
  ): Unit = {
    println("\n=============================================================================")
    println(query)
    val max      = 10
    val showRows = rows.length - max match {
      case 1          => rows.take(max) :+ "1 more row..."
      case n if n > 1 => rows.take(max) :+ s"$n more rows..."
      case _          => rows
    }
    println(showRows.mkString("List(\n  ", ",\n  ", "\n)\n"))
  }

  private def printInspectTx(
    label: String, elements: List[Element], stmts: Data
  ): Unit = {
    val edn = stmts.asScala
      .map(_.asScala.mkString("  [", " ", "]")).toList
      .mkString("[\n", "\n", "\n]")
    printRaw(label, elements, edn)
  }


  // Util --------------------------------------

  def getJavaStreamAndRowResolver[Tpl](
    q: Query[Tpl], conn0: Conn
  ): (jStream[jList[AnyRef]], jList[AnyRef] => Any) = {
    val conn       = conn0.asInstanceOf[DatomicConn_JVM]
    val queryClean = q.copy(elements = noKeywords(q.elements, Some(conn.proxy)))
    val m2q        = new Model2DatomicQuery[Tpl](q.elements)
    if (m2q.isNested || m2q.isOptNested || m2q.nestedOptRef) {
      throw ModelError("Nested data not allowed for streaming.")
    }
    val stream     = DatomicQueryResolveOffset[Tpl](
      q.elements, q.optLimit, None, q.dbView, m2q
    ).getJavaStream(conn)
    val row2AnyTpl = m2q.castRow2AnyTpl(m2q.castss.head, 0)
    (stream, row2AnyTpl)
  }
}
