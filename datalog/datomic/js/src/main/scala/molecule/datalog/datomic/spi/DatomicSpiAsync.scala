package molecule.datalog.datomic.spi

import molecule.base.error._
import molecule.boilerplate.ast.Model._
import molecule.core.action._
import molecule.core.marshalling.serialize.PickleTpls
import molecule.core.spi.{Conn, SpiAsync, TxReport}
import molecule.core.util.FutureUtils
import molecule.core.validation.ModelValidation
import molecule.core.validation.insert.InsertValidation
import molecule.datalog.datomic.facade.DatomicConn_JS
import scala.concurrent.{Future, ExecutionContext => EC}


trait DatomicSpiAsync
  extends SpiAsync
    with DatomicSpi
    with DatomicSpiAsyncBase
    with FutureUtils {

  // Query --------------------------------------------------------

  override def query_get[Tpl](q: Query[Tpl])
                             (implicit conn0: Conn, ec: EC): Future[List[Tpl]] = {
    val conn  = conn0.asInstanceOf[DatomicConn_JS]
    val proxy = conn.proxy.copy(dbView = q.dbView)
    conn.rpc.query[Tpl](proxy, q.elements, q.optLimit).future

  }

  override def query_subscribe[Tpl](q: Query[Tpl], callback: List[Tpl] => Unit)
                                   (implicit conn0: Conn, ec: EC): Future[Unit] = {
    addCallback(q, callback)(conn0, ec)
  }

  override def query_unsubscribe[Tpl](q: Query[Tpl])
                                     (implicit conn: Conn, ec: EC): Future[Unit] = {
    Future(conn.removeCallback(q.elements))
  }

  override def query_inspect[Tpl](q: Query[Tpl])
                                 (implicit conn: Conn, ec: EC): Future[Unit] = {
    printInspectQuery("QUERY", q.elements)
  }

  override def queryOffset_get[Tpl](q: QueryOffset[Tpl])
                                   (implicit conn0: Conn, ec: EC): Future[(List[Tpl], Int, Boolean)] = {
    val conn  = conn0.asInstanceOf[DatomicConn_JS]
    val proxy = conn.proxy.copy(dbView = q.dbView)
    conn.rpc.queryOffset[Tpl](proxy, q.elements, q.optLimit, q.offset).future
  }

  override def queryOffset_inspect[Tpl](q: QueryOffset[Tpl])
                                       (implicit conn: Conn, ec: EC): Future[Unit] = {
    printInspectQuery("QUERY (offset)", q.elements)
  }

  override def queryCursor_get[Tpl](q: QueryCursor[Tpl])
                                   (implicit conn0: Conn, ec: EC): Future[(List[Tpl], String, Boolean)] = {
    val conn  = conn0.asInstanceOf[DatomicConn_JS]
    val proxy = conn.proxy.copy(dbView = q.dbView)
    conn.rpc.queryCursor[Tpl](proxy, q.elements, q.optLimit, q.cursor).future
  }

  override def queryCursor_inspect[Tpl](q: QueryCursor[Tpl])
                                       (implicit conn: Conn, ec: EC): Future[Unit] = {
    printInspectQuery("QUERY (cursor)", q.elements)
  }


  // Save --------------------------------------------------------

  override def save_transact(save: Save)(implicit conn0: Conn, ec: EC): Future[TxReport] = try {
    val errors = save_validate(save)
    if (errors.isEmpty) {
      val conn = conn0.asInstanceOf[DatomicConn_JS]
      conn.rpc.save(conn.proxy, save.elements).future.map { txReport =>
        conn.callback(save.elements)
        txReport
      }
    } else {
      Future.failed(ValidationErrors(errors))
    }
  } catch {
    case e: Throwable => Future.failed(e)
  }

  override def save_inspect(save: Save)(implicit conn: Conn, ec: EC): Future[Unit] = {
    printInspectTx("SAVE", save.elements)
  }

  override def save_validate(save: Save)(implicit conn: Conn): Map[String, Seq[String]] = {
    val proxy = conn.proxy
    ModelValidation(proxy.nsMap, proxy.attrMap, "save").validate(save.elements)
  }


  // Insert --------------------------------------------------------

  override def insert_transact(insert: Insert)(implicit conn0: Conn, ec: EC): Future[TxReport] = try {
    val conn   = conn0.asInstanceOf[DatomicConn_JS]
    val errors = insert_validate(insert)(conn)
    if (errors.isEmpty) {
      val tplsSerialized = PickleTpls(insert.elements, true).pickle(Right(insert.tpls))
      conn.rpc.insert(conn.proxy, insert.elements, tplsSerialized).future.map { txReport =>
        conn.callback(insert.elements)
        txReport
      }
    } else {
      Future.failed(InsertErrors(errors))
    }
  } catch {
    case e: Throwable => Future.failed(e)
  }

  override def insert_inspect(insert: Insert)(implicit conn: Conn, ec: EC): Future[Unit] = {
    printInspectTx("INSERT", insert.elements)
  }

  override def insert_validate(insert: Insert)(implicit conn: Conn): Seq[(Int, Seq[InsertError])] = {
    InsertValidation.validate(conn, insert.elements, insert.tpls)
  }


  // Update --------------------------------------------------------

  override def update_transact(update: Update)(implicit conn0: Conn, ec: EC): Future[TxReport] = try {
    val errors = update_validate(update)
    if (errors.isEmpty) {
      val conn = conn0.asInstanceOf[DatomicConn_JS]
      conn.rpc.update(conn.proxy, update.elements, update.isUpsert).future.map { txReport =>
        conn.callback(update.elements)
        txReport
      }
    } else {
      Future.failed(ValidationErrors(errors))
    }
  } catch {
    case e: Throwable => Future.failed(e)
  }

  override def update_inspect(update: Update)(implicit conn: Conn, ec: EC): Future[Unit] = {
    printInspectTx("UPDATE", update.elements)
  }

  override def update_validate(update: Update)(implicit conn: Conn): Map[String, Seq[String]] = {
    val proxy  = conn.proxy
    if (update.isUpsert && isRefUpdate(update.elements))
      throw ModelError("Can't upsert referenced attributes. Please update instead.")
    ModelValidation(proxy.nsMap, proxy.attrMap, "update").validate(update.elements)
  }


  // Delete --------------------------------------------------------

  override def delete_transact(delete: Delete)(implicit conn0: Conn, ec: EC): Future[TxReport] = {
    val conn = conn0.asInstanceOf[DatomicConn_JS]
    conn.rpc.delete(conn.proxy, delete.elements).future.map { txReport =>
      conn.callback(delete.elements, true)
      txReport
    }
  }

  override def delete_inspect(delete: Delete)(implicit conn: Conn, ec: EC): Future[Unit] = {
    printInspectTx("DELETE", delete.elements)
  }


  // Util

  private def printInspectTx(label: String, elements: List[Element])
                            (implicit ec: EC): Future[Unit] = {
    Future(printInspect("RPC " + label, elements))
  }
}
