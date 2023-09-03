package molecule.datalog.datomic.spi

import datomic.Peer
import molecule.base.error.{InsertError, ModelError, MoleculeError}
import molecule.boilerplate.ast.Model._
import molecule.core.action._
import molecule.core.spi.{Conn, PrintInspect, SpiSync, TxReport}
import molecule.core.util.{FutureUtils, JavaConversions}
import molecule.datalog.core.query.Model2DatomicQuery
import molecule.datalog.datomic.facade.DatomicConn_JVM
import molecule.datalog.datomic.query.{DatomicQueryResolveCursor, DatomicQueryResolveOffset}
import molecule.datalog.datomic.subscription.SubscriptionStarter
import zio.ZIO
import scala.concurrent.Await
import scala.concurrent.ExecutionContext.global
import scala.concurrent.duration.DurationInt

object DatomicSpiSync extends DatomicSpiSync

trait DatomicSpiSync
  extends SpiSync
    with DatomicSpiZioBase
    with SubscriptionStarter
    with PrintInspect
    with FutureUtils
    with JavaConversions {

  override def query_get[Tpl](q: Query[Tpl])(implicit conn: Conn): List[Tpl] = {
    if (q.doInspect) query_inspect(q)
    DatomicQueryResolveOffset[Tpl](q.elements, q.optLimit, None, q.dbView)
      .getListFromOffset_sync(conn.asInstanceOf[DatomicConn_JVM])._1
  }

  override def query_subscribe[Tpl](q: Query[Tpl], callback: List[Tpl] => Unit)(implicit conn: Conn): Unit = {
    val datomicConn = conn.asInstanceOf[DatomicConn_JVM]
    DatomicQueryResolveOffset[Tpl](q.elements, q.optLimit, None, q.dbView)
      .subscribe(datomicConn, getWatcher(datomicConn), callback)
  }

  override def query_inspect[Tpl](q: Query[Tpl])(implicit conn: Conn): Unit = {
    printInspectQuery("QUERY", q.elements)
  }

  override def queryOffset_get[Tpl](q: QueryOffset[Tpl])(implicit conn: Conn): (List[Tpl], Int, Boolean) = {
    if (q.doInspect) queryOffset_inspect(q)
    DatomicQueryResolveOffset[Tpl](q.elements, q.optLimit, Some(q.offset), q.dbView)
      .getListFromOffset_sync(conn.asInstanceOf[DatomicConn_JVM])
  }

  override def queryOffset_inspect[Tpl](q: QueryOffset[Tpl])(implicit conn: Conn): Unit = {
    printInspectQuery("QUERY (offset)", q.elements)
  }

  override def queryCursor_get[Tpl](q: QueryCursor[Tpl])(implicit conn: Conn): (List[Tpl], String, Boolean) = {
    if (q.doInspect) queryCursor_inspect(q)
    DatomicQueryResolveCursor[Tpl](q.elements, q.optLimit, Some(q.cursor), q.dbView)
      .getListFromCursor_sync(conn.asInstanceOf[DatomicConn_JVM])
  }
  override def queryCursor_inspect[Tpl](q: QueryCursor[Tpl])(implicit conn: Conn): Unit = {
    printInspectQuery("QUERY (cursor)", q.elements)
  }


  override def save_transact(save: Save)(implicit conn: Conn): TxReport = {
    await(DatomicSpiAsync.save_transact(save)(conn, global))
  }
  override def save_inspect(save: Save)(implicit conn: Conn): Unit = {
    await(DatomicSpiAsync.save_inspect(save)(conn, global))
  }
  override def save_validate(save: Save)(implicit conn: Conn): Map[String, Seq[String]] = {
    DatomicSpiAsync.save_validate(save)(conn)
  }

  override def insert_transact(insert: Insert)(implicit conn: Conn): TxReport = {
    await(DatomicSpiAsync.insert_transact(insert)(conn, global))
  }
  override def insert_inspect(insert: Insert)(implicit conn: Conn): Unit = {
    await(DatomicSpiAsync.insert_inspect(insert)(conn, global))
  }
  override def insert_validate(insert: Insert)(implicit conn: Conn): Seq[(Int, Seq[InsertError])] = {
    DatomicSpiAsync.insert_validate(insert)(conn)
  }

  override def update_transact(update: Update)(implicit conn: Conn): TxReport = {
    await(DatomicSpiAsync.update_transact(update)(conn, global))
  }
  override def update_inspect(update: Update)(implicit conn: Conn): Unit = {
    await(DatomicSpiAsync.update_inspect(update)(conn, global))
  }
  override def update_validate(update: Update)(implicit conn: Conn): Map[String, Seq[String]] = {
    DatomicSpiAsync.update_validate(update)
  }

  override def delete_transact(delete: Delete)(implicit conn: Conn): TxReport = {
    await(DatomicSpiAsync.delete_transact(delete)(conn, global))
  }
  override def delete_inspect(delete: Delete)(implicit conn: Conn): Unit = {
    await(DatomicSpiAsync.delete_inspect(delete)(conn, global))
  }


  // Fallbacks

  override def fallback_rawQuery(
    query: String,
    withNulls: Boolean = false,
    doPrint: Boolean = true,
  )(implicit conn: Conn): List[List[Any]] = {
    if (withNulls)
      throw new Exception("Null values not part of the semantic model of Datomic.")

    // todo: cast result
    Peer.q(query, conn.db).asScala.toList.map(_.asScala.toList)
  }

  override def fallback_rawTransact(
    txData: String,
    doPrint: Boolean = true
  )(implicit conn: Conn): TxReport = {
    try {
      import molecule.core.util.Executor.global
      Await.result(DatomicSpiAsync.fallback_rawTransact(txData, doPrint)(conn, global), 10.seconds)
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
