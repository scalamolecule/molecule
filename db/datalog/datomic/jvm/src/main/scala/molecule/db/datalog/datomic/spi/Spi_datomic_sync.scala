package molecule.db.datalog.datomic.spi

import java.util.List as jList
import java.util.stream.Stream as jStream
import datomic.Peer
import geny.Generator
import molecule.base.error.{InsertError, ModelError}
import molecule.core.dataModel.*
import molecule.db.core.action.*
import molecule.db.core.spi.{Conn, Spi_sync, TxReport}
import molecule.db.core.transaction.{ResolveDelete, ResolveInsert, ResolveSave, ResolveUpdate}
import molecule.db.core.util.Executor.*
import molecule.db.core.util.FutureUtils
import molecule.db.core.validation.TxModelValidation
import molecule.db.core.validation.insert.InsertValidation
import molecule.db.datalog.core.query.Model2DatomicQuery
import molecule.db.datalog.datomic.facade.DatomicConn_JVM
import molecule.db.datalog.datomic.query.{DatomicQueryResolveCursor, DatomicQueryResolveOffset}
import molecule.db.datalog.datomic.transaction.*
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
    if (q.printInspect) query_inspect(q)
    val m2q = new Model2DatomicQuery[Tpl](q.dataModel)
    m2q.bindValues.addAll(q.bindValues)
    DatomicQueryResolveOffset[Tpl](
      q.dataModel, q.optLimit, None, q.dbView, m2q
    ).getListFromOffset_sync(conn.asInstanceOf[DatomicConn_JVM])._1
  }

  override def query_inspect[Tpl](q: Query[Tpl])(implicit conn: Conn): String = {
    renderInspectQuery("QUERY", q.dataModel)
  }

  override def queryOffset_get[Tpl](
    q: QueryOffset[Tpl]
  )(implicit conn: Conn): (List[Tpl], Int, Boolean) = {
    if (q.printInspect) queryOffset_inspect(q)
    val m2q = new Model2DatomicQuery[Tpl](q.dataModel)
    m2q.bindValues.addAll(q.bindValues)
    DatomicQueryResolveOffset[Tpl](
      q.dataModel, q.optLimit, Some(q.offset), q.dbView, m2q
    ).getListFromOffset_sync(conn.asInstanceOf[DatomicConn_JVM])
  }

  override def queryOffset_inspect[Tpl](
    q: QueryOffset[Tpl]
  )(implicit conn: Conn): String = {
    renderInspectQuery("QUERY (offset)", q.dataModel)
  }

  override def queryCursor_get[Tpl](
    q: QueryCursor[Tpl]
  )(implicit conn: Conn): (List[Tpl], String, Boolean) = {
    if (q.printInspect) queryCursor_inspect(q)
    val m2q = new Model2DatomicQuery[Tpl](q.dataModel)
    m2q.bindValues.addAll(q.bindValues)
    DatomicQueryResolveCursor[Tpl](
      q.dataModel, q.optLimit, Some(q.cursor), q.dbView, m2q
    ).getListFromCursor_sync(conn.asInstanceOf[DatomicConn_JVM])
  }

  override def queryCursor_inspect[Tpl](
    q: QueryCursor[Tpl]
  )(implicit conn: Conn): String = {
    renderInspectQuery("QUERY (cursor)", q.dataModel)
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
  )(implicit conn0: Conn): Unit = {
    val conn = conn0.asInstanceOf[DatomicConn_JVM]
    conn.addCallback(q.dataModel, () =>
      callback {
    val m2q  = new Model2DatomicQuery[Tpl](q.dataModel)
        val a = DatomicQueryResolveOffset(q.dataModel, q.optLimit, None, None, m2q)
        a.getListFromOffset_sync(conn)._1
      }
    )
  }

  override def query_unsubscribe[Tpl](
    q: Query[Tpl]
  )(implicit conn: Conn): Unit = {
    conn.removeCallback(q.dataModel)
  }


  override def save_transact(save: Save)(implicit conn: Conn): TxReport = {
    await(Spi_datomic_async.save_transact(save)(conn, global))
  }

  override def save_inspect(save: Save)(implicit conn: Conn): String = {
    renderInspectTx("SAVE", save.dataModel, save_getStmts(save))
  }

  override def save_validate(
    save: Save
  )(implicit conn: Conn): Map[String, Seq[String]] = {
    if (save.doValidate) {
      val proxy = conn.proxy
      TxModelValidation(proxy.entityMap, proxy.attrMap, "save")
        .validate(save.dataModel.elements)
    } else {
      Map.empty[String, Seq[String]]
    }
  }

  def save_getStmts(save: Save): Data = {
    (new ResolveSave with Save_datomic).getStmts(save.dataModel.elements)
  }

  override def insert_transact(insert: Insert)(implicit conn: Conn): TxReport = {
    await(Spi_datomic_async.insert_transact(insert)(conn, global))
  }

  override def insert_inspect(insert: Insert)(implicit conn: Conn): String = {
    renderInspectTx("INSERT", insert.dataModel, insert_getStmts(insert))
  }

  override def insert_validate(
    insert: Insert
  )(implicit conn: Conn): Seq[(Int, Seq[InsertError])] = {
    if (insert.doValidate) {
      InsertValidation.validate(conn, insert.dataModel.elements, insert.tpls)
    } else {
      Seq.empty[(Int, Seq[InsertError])]
    }
  }

  def insert_getStmts(insert: Insert): Data = {
    (new ResolveInsert with Insert_datomic)
      .getStmts(insert.dataModel.elements, insert.tpls)
  }

  override def update_transact(update: Update)(implicit conn: Conn): TxReport = {
    await(Spi_datomic_async.update_transact(update)(conn, global))
  }

  override def update_inspect(update: Update)(implicit conn: Conn): String = {
    val action = if (update.isUpsert) "UPSERT" else "UPDATE"
    renderInspectTx(action, update.dataModel,
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
    }.getStmts(conn, update.dataModel)
  }

  override def delete_transact(delete: Delete)(implicit conn: Conn): TxReport = {
    await(Spi_datomic_async.delete_transact(delete)(conn, global))
  }

  override def delete_inspect(delete: Delete)(implicit conn: Conn): String = {
    renderInspectTx("DELETE", delete.dataModel,
      delete_getStmts(delete, conn.asInstanceOf[DatomicConn_JVM]))
  }

  def delete_getStmts(delete: Delete, conn: DatomicConn_JVM): Data = {
    (new ResolveDelete with Delete_datomic)
      .getData(conn, delete.dataModel)
  }


  // Fallbacks --------------------------------------------------------

  override def fallback_rawTransact(
    txData: String, debug: Boolean = false
  )(implicit conn: Conn): TxReport = {
    try {
      import molecule.db.core.util.Executor.global
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
  ): String = {
    println("\n=============================================================================")
    println(query)
    val max      = 10
    val showRows = rows.length - max match {
      case 1          => rows.take(max) :+ "1 more row..."
      case n if n > 1 => rows.take(max) :+ s"$n more rows..."
      case _          => rows
    }
    showRows.mkString("List(\n  ", ",\n  ", "\n)\n")
  }

  private def renderInspectTx(
    label: String, dataModel: DataModel, stmts: Data
  ): String = {
    val edn = stmts.asScala
      .map(_.asScala.mkString("  [", " ", "]")).toList
      .mkString("[\n", "\n", "\n]")
    renderInspection(label, dataModel, edn)
  }


  // Util --------------------------------------

  def getJavaStreamAndRowResolver[Tpl](
    query: Query[Tpl], conn0: Conn
  ): (jStream[jList[AnyRef]], jList[AnyRef] => Any) = {
    val conn = conn0.asInstanceOf[DatomicConn_JVM]
    val m2q  = new Model2DatomicQuery[Tpl](query.dataModel)
    if (m2q.isNested || m2q.isOptNested || m2q.nestedOptRef) {
      throw ModelError("Nested data not allowed for streaming.")
    }
    val stream     = DatomicQueryResolveOffset[Tpl](
      query.dataModel, query.optLimit, None, query.dbView, m2q
    ).getJavaStream(conn)
    val row2AnyTpl = m2q.castRow2AnyTpl(m2q.castss.head, 0)
    (stream, row2AnyTpl)
  }
}
