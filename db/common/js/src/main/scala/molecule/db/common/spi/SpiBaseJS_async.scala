package molecule.db.common.spi

import java.nio.ByteBuffer
import boopickle.Default.*
import molecule.base.error.{InsertError, InsertErrors, ValidationErrors}
import molecule.core.dataModel.{DataModel, OneInt, OneString}
import molecule.db.common.action.*
import molecule.db.common.facade.JdbcConn_JS
import molecule.db.common.marshalling.Boopicklers.*
import molecule.db.common.marshalling.serialize.PickleTpls
import molecule.db.common.spi.{Conn, Renderer, Spi_async, TxReport}
import molecule.db.common.util.FutureUtils
import molecule.db.common.validation.TxModelValidation
import molecule.db.common.validation.insert.InsertValidation
import scala.concurrent.{Future, ExecutionContext as EC}


trait SpiBaseJS_async extends Spi_async with Renderer with FutureUtils {

  // Query --------------------------------------------------------

  override def query_get[Tpl](q: Query[Tpl])(using conn0: Conn, ec: EC): Future[List[Tpl]] = {
    val conn   = conn0.asInstanceOf[JdbcConn_JS]
    conn.rpc.query[Tpl](conn.proxy, q.dataModel, q.optLimit, q.bindValues).future
  }

  override def query_inspect[Tpl](q: Query[Tpl])(using conn: Conn, ec: EC): Future[String] = {
    renderInspectQuery("QUERY", q.dataModel)
  }


  override def queryOffset_get[Tpl](q: QueryOffset[Tpl])
                                   (using conn0: Conn, ec: EC): Future[(List[Tpl], Int, Boolean)] = {
    val conn = conn0.asInstanceOf[JdbcConn_JS]
    conn.rpc.queryOffset[Tpl](conn.proxy, q.dataModel, q.optLimit, q.offset, q.bindValues).future
  }

  override def queryOffset_inspect[Tpl](q: QueryOffset[Tpl])
                                       (using conn: Conn, ec: EC): Future[String] = {
    renderInspectQuery("QUERY (offset)", q.dataModel)
  }


  override def queryCursor_get[Tpl](q: QueryCursor[Tpl])
                                   (using conn0: Conn, ec: EC): Future[(List[Tpl], String, Boolean)] = {
    val conn = conn0.asInstanceOf[JdbcConn_JS]
    conn.rpc.queryCursor[Tpl](conn.proxy, q.dataModel, q.optLimit, q.cursor, q.bindValues).future
  }

  override def queryCursor_inspect[Tpl](q: QueryCursor[Tpl])
                                       (using conn: Conn, ec: EC): Future[String] = {
    renderInspectQuery("QUERY (cursor)", q.dataModel)
  }


  override def query_subscribe[Tpl](
    q: Query[Tpl], callback: List[Tpl] => Unit
  )(using conn: Conn, ec: EC): Future[Unit] = {
    conn.rpc.subscribe[Tpl](conn.proxy, q.dataModel, q.optLimit, callback)
  }

  override def query_unsubscribe[Tpl](q: Query[Tpl])(using conn: Conn, ec: EC): Future[Unit] = {
    conn.rpc.unsubscribe(conn.proxy, q.dataModel).future
  }


  // Save --------------------------------------------------------

  override def save_transact(save: Save)(using conn0: Conn, ec: EC): Future[TxReport] = {
    val conn = conn0.asInstanceOf[JdbcConn_JS]
    for {
      _ <- if (save.printInspect) save_inspect(save) else Future.unit
      errors <- save_validate(save) // validate original elements against meta model
      txReport <- if (errors.isEmpty) {
        conn.rpc.save(conn.proxy, save.dataModel).future
      } else {
        throw ValidationErrors(errors)
      }
    } yield {
      txReport
    }
  }

  override def save_inspect(save: Save)(using conn: Conn, ec: EC): Future[String] = {
    renderInspectTx("SAVE", save.dataModel)
  }

  override def save_validate(save: Save)(using conn: Conn, ec: EC): Future[Map[String, Seq[String]]] = future {
    if (save.doValidate) {
      TxModelValidation(conn.proxy.metaDb, "save").validate(save.dataModel.elements)
    } else {
      Map.empty[String, Seq[String]]
    }
  }


  // Insert --------------------------------------------------------

  override def insert_transact(insert: Insert)(using conn0: Conn, ec: EC): Future[TxReport] = {
    val conn = conn0.asInstanceOf[JdbcConn_JS]
    for {
      _ <- if (insert.printInspect) insert_inspect(insert) else Future.unit
      // Validating on JS side since it doesn't require db lookups
      errors <- insert_validate(insert) // validate original elements against meta model
      txReport <- if (errors.isEmpty) {
        if (insert.tpls.isEmpty) {
          Future(TxReport(Nil))
        } else {
          val tplsSerialized: ByteBuffer = PickleTpls(insert.dataModel, true).getPickledTpls(insert.tpls)
          conn.rpc.insert(conn.proxy, insert.dataModel, tplsSerialized).future
        }
      } else {
        throw InsertErrors(errors)
      }
    } yield {
      txReport
    }
  }

  override def insert_inspect(insert: Insert)(using conn: Conn, ec: EC): Future[String] = {
    renderInspectTx("INSERT", insert.dataModel)
  }

  override def insert_validate(insert: Insert)
                              (using conn: Conn, ec: EC): Future[Seq[(Int, Seq[InsertError])]] = future {
    if (insert.doValidate) {
      InsertValidation.validate(conn, insert.dataModel.elements, insert.tpls)
    } else {
      Seq.empty[(Int, Seq[InsertError])]
    }
  }


  // Update --------------------------------------------------------

  override def update_transact(update: Update)(using conn0: Conn, ec: EC): Future[TxReport] = {
    val conn = conn0.asInstanceOf[JdbcConn_JS]
    for {
      _ <- if (update.printInspect) update_inspect(update) else Future.unit
      // Validating on JVM side only since it requires db lookups
      txReport <- conn.rpc.update(conn.proxy, update.dataModel, update.isUpsert).future
    } yield {
      txReport
    }
  }

  override def update_inspect(update: Update)(using conn: Conn, ec: EC): Future[String] = {
    renderInspectTx("UPDATE", update.dataModel)
  }

  override def update_validate(update: Update)
                              (using conn: Conn, ec: EC): Future[Map[String, Seq[String]]] = future {
    TxModelValidation(conn.proxy.metaDb, "update").validate(update.dataModel.elements)
  }


  // Delete --------------------------------------------------------

  override def delete_transact(delete: Delete)(using conn0: Conn, ec: EC): Future[TxReport] = {
    val conn = conn0.asInstanceOf[JdbcConn_JS]
    for {
      txReport <- conn.rpc.delete(conn.proxy, delete.dataModel).future
    } yield txReport
  }

  override def delete_inspect(delete: Delete)(using conn: Conn, ec: EC): Future[String] = {
    renderInspectTx("DELETE", delete.dataModel)
  }


  // Util --------------------------------------

  private def renderInspectTx(label: String, dataModel: DataModel)
                             (using ec: EC): Future[String] = {
    Future(renderInspection(label, dataModel))
  }

  protected def renderInspectQuery(label: String, dataModel: DataModel)
                                  (using ec: EC): Future[String]
}
