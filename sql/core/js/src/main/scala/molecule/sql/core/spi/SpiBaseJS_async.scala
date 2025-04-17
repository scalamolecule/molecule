package molecule.sql.core.spi

import boopickle.Default._
import cats.effect.IO
import molecule.base.error.{InsertError, InsertErrors, ValidationErrors}
import molecule.core.action._
import molecule.core.ast.DataModel.Element
import molecule.core.marshalling.serialize.PickleTpls
import molecule.core.spi.{Conn, Renderer, Spi_async, TxReport}
import molecule.core.util.FutureUtils
import molecule.core.validation.TxModelValidation
import molecule.core.validation.insert.InsertValidation
import molecule.sql.core.facade.JdbcConn_JS
import scala.concurrent.{Future, ExecutionContext => EC}


trait SpiBaseJS_async extends Spi_async with Renderer with FutureUtils {

  // Query --------------------------------------------------------

  override def query_get[Tpl](q: Query[Tpl])(implicit conn0: Conn, ec: EC): Future[List[Tpl]] = {
    val conn = conn0.asInstanceOf[JdbcConn_JS]
    conn.rpc.query[Tpl](conn.proxy, q.elements, q.optLimit).future
  }

  override def query_stream[Tpl](
    q: Query[Tpl],
    chunkSize: Int
  )(implicit conn0: Conn, ec: EC): fs2.Stream[IO, Tpl] = {
    val conn = conn0.asInstanceOf[JdbcConn_JS]
    conn.rpc.queryFs2Stream[Tpl](conn.proxy, q.elements, q.optLimit)
  }

  // Query backend if cached subscription attributes are mutated.
  // Probably need a websocket solution instead
  override def query_subscribe[Tpl](
    q: Query[Tpl], callback: List[Tpl] => Unit
  )(implicit conn0: Conn, ec: EC): Future[Unit] = {
    val conn                 = conn0.asInstanceOf[JdbcConn_JS]
    val elements             = q.elements
    val involvedAttrs        = getAttrNames(elements)
    val involvedDeleteEntity = getInitialEntity(elements)
    val maybeCallback        = (mutationAttrs: Set[String], isDelete: Boolean) => {
      if (
        mutationAttrs.exists(involvedAttrs.contains) ||
          isDelete && mutationAttrs.head.startsWith(involvedDeleteEntity)
      ) {
        conn.rpc.query[Tpl](conn.proxy, q.elements, q.optLimit)
          .future
          .map(callback)
      } else Future.unit
    }
    Future(conn.addCallback(elements -> maybeCallback))
  }

  override def query_unsubscribe[Tpl](q: Query[Tpl])(implicit conn0: Conn, ec: EC): Future[Unit] = {
    Future(conn0.removeCallback(q.elements))
  }

  override def query_inspect[Tpl](q: Query[Tpl])(implicit conn: Conn, ec: EC): Future[Unit] = {
    printInspectQuery("QUERY", q.elements)
  }


  override def queryOffset_get[Tpl](q: QueryOffset[Tpl])
                                   (implicit conn0: Conn, ec: EC): Future[(List[Tpl], Int, Boolean)] = {
    val conn = conn0.asInstanceOf[JdbcConn_JS]
    conn.rpc.queryOffset[Tpl](conn.proxy, q.elements, q.optLimit, q.offset).future
  }

  override def queryOffset_inspect[Tpl](q: QueryOffset[Tpl])
                                       (implicit conn: Conn, ec: EC): Future[Unit] = {
    printInspectQuery("QUERY (offset)", q.elements)
  }


  override def queryCursor_get[Tpl](q: QueryCursor[Tpl])
                                   (implicit conn0: Conn, ec: EC): Future[(List[Tpl], String, Boolean)] = {
    val conn = conn0.asInstanceOf[JdbcConn_JS]
    conn.rpc.queryCursor[Tpl](conn.proxy, q.elements, q.optLimit, q.cursor).future
  }

  override def queryCursor_inspect[Tpl](q: QueryCursor[Tpl])
                                       (implicit conn: Conn, ec: EC): Future[Unit] = {
    printInspectQuery("QUERY (cursor)", q.elements)
  }


  // Save --------------------------------------------------------

  override def save_transact(save: Save)(implicit conn0: Conn, ec: EC): Future[TxReport] = {
    val conn = conn0.asInstanceOf[JdbcConn_JS]
    for {
      _ <- if (save.doInspect) save_inspect(save) else Future.unit
      errors <- save_validate(save) // validate original elements against meta model
      txReport <- if (errors.isEmpty) {
        conn.rpc.save(conn.proxy, save.elements).future
      } else {
        throw ValidationErrors(errors)
      }
      _ <- conn.callback(save.elements)
    } yield {
      txReport
    }
  }

  override def save_inspect(save: Save)(implicit conn: Conn, ec: EC): Future[Unit] = {
    printInspectTx("SAVE", save.elements)
  }

  override def save_validate(save: Save)(implicit conn: Conn, ec: EC): Future[Map[String, Seq[String]]] = future {
    if (save.doValidate) {
      val proxy = conn.proxy
      TxModelValidation(proxy.entityMap, proxy.attrMap, "save").validate(save.elements)
    } else {
      Map.empty[String, Seq[String]]
    }
  }


  // Insert --------------------------------------------------------

  override def insert_transact(insert: Insert)(implicit conn0: Conn, ec: EC): Future[TxReport] = {
    val conn = conn0.asInstanceOf[JdbcConn_JS]
    for {
      _ <- if (insert.doInspect) insert_inspect(insert) else Future.unit
      // Validating on JS side since it doesn't require db lookups
      errors <- insert_validate(insert) // validate original elements against meta model
      txReport <- if (errors.isEmpty) {
        if (insert.tpls.isEmpty) {
          Future(TxReport(Nil))
        } else {
          val tplsSerialized = PickleTpls(insert.elements, true).getPickledTpls(insert.tpls)
          conn.rpc.insert(conn.proxy, insert.elements, tplsSerialized).future
        }
      } else {
        throw InsertErrors(errors)
      }
      _ <- conn.callback(insert.elements)
    } yield {
      txReport
    }
  }

  override def insert_inspect(insert: Insert)(implicit conn: Conn, ec: EC): Future[Unit] = {
    printInspectTx("INSERT", insert.elements)
  }

  override def insert_validate(insert: Insert)
                              (implicit conn: Conn, ec: EC): Future[Seq[(Int, Seq[InsertError])]] = future {
    if (insert.doValidate) {
      InsertValidation.validate(conn, insert.elements, insert.tpls)
    } else {
      Seq.empty[(Int, Seq[InsertError])]
    }
  }


  // Update --------------------------------------------------------

  override def update_transact(update: Update)(implicit conn0: Conn, ec: EC): Future[TxReport] = {
    val conn = conn0.asInstanceOf[JdbcConn_JS]
    for {
      _ <- if (update.doInspect) update_inspect(update) else Future.unit
      // Validating on JVM side only since it requires db lookups
      txReport <- conn.rpc.update(conn.proxy, update.elements, update.isUpsert).future
      _ <- conn.callback(update.elements)
    } yield {
      txReport
    }
  }

  override def update_inspect(update: Update)(implicit conn: Conn, ec: EC): Future[Unit] = {
    printInspectTx("UPDATE", update.elements)
  }

  override def update_validate(update: Update)
                              (implicit conn: Conn, ec: EC): Future[Map[String, Seq[String]]] = future {
    val proxy = conn.proxy
    TxModelValidation(proxy.entityMap, proxy.attrMap, "update").validate(update.elements)
  }


  // Delete --------------------------------------------------------

  override def delete_transact(delete: Delete)(implicit conn0: Conn, ec: EC): Future[TxReport] = {
    val conn = conn0.asInstanceOf[JdbcConn_JS]
    for {
      txReport <- conn.rpc.delete(conn.proxy, delete.elements).future
      _ <- conn.callback(delete.elements, true)
    } yield txReport
  }

  override def delete_inspect(delete: Delete)(implicit conn: Conn, ec: EC): Future[Unit] = {
    printInspectTx("DELETE", delete.elements)
  }


  // Util --------------------------------------

  private def printInspectTx(label: String, elements: List[Element])
                            (implicit ec: EC): Future[Unit] = {
    Future(printRaw("RPC " + label, elements))
  }

  protected def printInspectQuery(label: String, elements: List[Element])
                                 (implicit ec: EC): Future[Unit]
}
