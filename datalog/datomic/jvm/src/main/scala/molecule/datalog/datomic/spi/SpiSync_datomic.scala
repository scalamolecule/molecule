package molecule.datalog.datomic.spi

import datomic.Peer
import molecule.base.error.{InsertError, ModelError, MoleculeError}
import molecule.core.action._
import molecule.core.spi.{Conn, PrintInspect, SpiSync, TxReport}
import molecule.core.util.{FutureUtils, JavaConversions}
import molecule.datalog.core.query.Model2DatomicQuery
import molecule.datalog.datomic.facade.DatomicConn_JVM
import molecule.datalog.datomic.query.{DatomicQueryResolveCursor, DatomicQueryResolveOffset}
import zio.ZIO
import scala.concurrent.Await
import scala.concurrent.ExecutionContext.global
import scala.concurrent.duration.DurationInt

object SpiSync_datomic extends SpiSync_datomic

trait SpiSync_datomic
  extends SpiSync
    with DatomicSpiZioBase
    with PrintInspect
    with FutureUtils
    with JavaConversions {

  override def query_get[Tpl](q: Query[Tpl])(implicit conn: Conn): List[Tpl] = {
    if (q.doInspect) query_inspect(q)
    val m2q = new Model2DatomicQuery[Tpl](q.elements)
    DatomicQueryResolveOffset[Tpl](q.elements, q.optLimit, None, q.dbView, m2q)
      .getListFromOffset_sync(conn.asInstanceOf[DatomicConn_JVM])._1
  }

  override def query_subscribe[Tpl](q: Query[Tpl], callback: List[Tpl] => Unit)(implicit conn: Conn): Unit = {
    val datomicConn = conn.asInstanceOf[DatomicConn_JVM]
    val m2q         = new Model2DatomicQuery[Tpl](q.elements)
    DatomicQueryResolveOffset[Tpl](q.elements, q.optLimit, None, q.dbView, m2q)
      .subscribe(datomicConn, callback)
  }

  override def query_unsubscribe[Tpl](q: Query[Tpl])(implicit conn: Conn): Unit = {
    val datomicConn = conn.asInstanceOf[DatomicConn_JVM]
    val m2q         = new Model2DatomicQuery[Tpl](q.elements)
    DatomicQueryResolveOffset[Tpl](q.elements, q.optLimit, None, q.dbView, m2q)
      .unsubscribe(datomicConn)
  }

  override def query_inspect[Tpl](q: Query[Tpl])(implicit conn: Conn): Unit = {
    printInspectQuery("QUERY", q.elements)
  }

  override def queryOffset_get[Tpl](q: QueryOffset[Tpl])(implicit conn: Conn): (List[Tpl], Int, Boolean) = {
    if (q.doInspect) queryOffset_inspect(q)
    val m2q = new Model2DatomicQuery[Tpl](q.elements)
    DatomicQueryResolveOffset[Tpl](q.elements, q.optLimit, Some(q.offset), q.dbView, m2q)
      .getListFromOffset_sync(conn.asInstanceOf[DatomicConn_JVM])
  }

  override def queryOffset_inspect[Tpl](q: QueryOffset[Tpl])(implicit conn: Conn): Unit = {
    printInspectQuery("QUERY (offset)", q.elements)
  }

  override def queryCursor_get[Tpl](q: QueryCursor[Tpl])(implicit conn: Conn): (List[Tpl], String, Boolean) = {
    if (q.doInspect) queryCursor_inspect(q)
    val m2q = new Model2DatomicQuery[Tpl](q.elements)
    DatomicQueryResolveCursor[Tpl](q.elements, q.optLimit, Some(q.cursor), q.dbView, m2q)
      .getListFromCursor_sync(conn.asInstanceOf[DatomicConn_JVM])
  }
  override def queryCursor_inspect[Tpl](q: QueryCursor[Tpl])(implicit conn: Conn): Unit = {
    printInspectQuery("QUERY (cursor)", q.elements)
  }


  override def save_transact(save: Save)(implicit conn: Conn): TxReport = {
    await(SpiAsync_datomic.save_transact(save)(conn, global))
  }
  override def save_inspect(save: Save)(implicit conn: Conn): Unit = {
    await(SpiAsync_datomic.save_inspect(save)(conn, global))
  }
  override def save_validate(save: Save)(implicit conn: Conn): Map[String, Seq[String]] = {
    SpiAsync_datomic.save_validate(save)(conn)
  }

  override def insert_transact(insert: Insert)(implicit conn: Conn): TxReport = {
    await(SpiAsync_datomic.insert_transact(insert)(conn, global))
  }
  override def insert_inspect(insert: Insert)(implicit conn: Conn): Unit = {
    await(SpiAsync_datomic.insert_inspect(insert)(conn, global))
  }
  override def insert_validate(insert: Insert)(implicit conn: Conn): Seq[(Int, Seq[InsertError])] = {
    SpiAsync_datomic.insert_validate(insert)(conn)
  }

  override def update_transact(update: Update)(implicit conn: Conn): TxReport = {
    await(SpiAsync_datomic.update_transact(update)(conn, global))
  }
  override def update_inspect(update: Update)(implicit conn: Conn): Unit = {
    await(SpiAsync_datomic.update_inspect(update)(conn, global))
  }
  override def update_validate(update: Update)(implicit conn: Conn): Map[String, Seq[String]] = {
    SpiAsync_datomic.update_validate(update)
  }

  override def delete_transact(delete: Delete)(implicit conn: Conn): TxReport = {
    await(SpiAsync_datomic.delete_transact(delete)(conn, global))
  }
  override def delete_inspect(delete: Delete)(implicit conn: Conn): Unit = {
    await(SpiAsync_datomic.delete_inspect(delete)(conn, global))
  }


  // Fallbacks

  override def fallback_rawQuery(
    query: String,
    withNulls: Boolean = false,
    doPrint: Boolean = true,
  )(implicit conn: Conn): List[List[Any]] = {
    if (withNulls)
      throw new Exception("Null values not part of the semantic model of Datomic.")
    Peer.q(query, conn.db.asInstanceOf[AnyRef]).asScala.toList.map(_.asScala.toList)
  }

  override def fallback_rawTransact(
    txData: String,
    doPrint: Boolean = true
  )(implicit conn: Conn): TxReport = {
    try {
      import molecule.core.util.Executor.global
      Await.result(SpiAsync_datomic.fallback_rawTransact(txData, doPrint)(conn, global), 10.seconds)
    } catch {
      case t: Throwable => throw ModelError(t.toString)
    }
  }


  // Util

  protected def sync2zio[T](query: DatomicConn_JVM => T): ZIO[Conn, MoleculeError, T] = {
    for {
      conn0 <- ZIO.service[Conn]
      conn = conn0.asInstanceOf[DatomicConn_JVM]
      result <- moleculeError(ZIO.attemptBlocking(query(conn)))
    } yield result
  }
}
