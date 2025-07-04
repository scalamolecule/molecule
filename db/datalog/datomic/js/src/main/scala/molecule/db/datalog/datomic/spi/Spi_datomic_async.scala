package molecule.db.datalog.datomic.spi

import molecule.base.error.{InsertError, InsertErrors, ValidationErrors}
import molecule.core.dataModel.DataModel
import molecule.db.core.action.*
import molecule.db.core.marshalling.serialize.PickleTpls
import molecule.db.core.spi.{Conn, Spi_async, TxReport}
import molecule.db.core.util.FutureUtils
import molecule.db.core.validation.TxModelValidation
import molecule.db.core.validation.insert.InsertValidation
import molecule.db.datalog.datomic.facade.DatomicConn_JS
import scala.concurrent.{Future, ExecutionContext as EC}


object Spi_datomic_async extends Spi_datomic_async

trait Spi_datomic_async
  extends Spi_async
    with SpiBase_datomic_async
    with FutureUtils {

  // Query --------------------------------------------------------

  override def query_get[Tpl](q: Query[Tpl])
                             (implicit conn0: Conn, ec: EC): Future[List[Tpl]] = {
    val conn  = conn0.asInstanceOf[DatomicConn_JS]
    val proxy = conn.proxy.copy(dbView = q.dbView)
    conn.rpc.query[Tpl](proxy, q.dataModel, q.optLimit).future
  }

  override def query_inspect[Tpl](q: Query[Tpl])
                                 (implicit conn: Conn, ec: EC): Future[String] = {
    renderInspectQuery("QUERY", q.dataModel)
  }


  override def queryOffset_get[Tpl](q: QueryOffset[Tpl])
                                   (implicit conn0: Conn, ec: EC): Future[(List[Tpl], Int, Boolean)] = {
    val conn  = conn0.asInstanceOf[DatomicConn_JS]
    val proxy = conn.proxy.copy(dbView = q.dbView)
    conn.rpc.queryOffset[Tpl](proxy, q.dataModel, q.optLimit, q.offset).future
  }

  override def queryOffset_inspect[Tpl](q: QueryOffset[Tpl])
                                       (implicit conn: Conn, ec: EC): Future[String] = {
    renderInspectQuery("QUERY (offset)", q.dataModel)
  }


  override def queryCursor_get[Tpl](q: QueryCursor[Tpl])
                                   (implicit conn0: Conn, ec: EC): Future[(List[Tpl], String, Boolean)] = {
    val conn  = conn0.asInstanceOf[DatomicConn_JS]
    val proxy = conn.proxy.copy(dbView = q.dbView)
    conn.rpc.queryCursor[Tpl](proxy, q.dataModel, q.optLimit, q.cursor).future
  }

  override def queryCursor_inspect[Tpl](q: QueryCursor[Tpl])
                                       (implicit conn: Conn, ec: EC): Future[String] = {
    renderInspectQuery("QUERY (cursor)", q.dataModel)
  }


  override def query_subscribe[Tpl](q: Query[Tpl], callback: List[Tpl] => Unit)
                                   (implicit conn: Conn, ec: EC): Future[Unit] = {
    conn.rpc.subscribe[Tpl](conn.proxy, q.dataModel, q.optLimit, callback)
  }

  override def query_unsubscribe[Tpl](q: Query[Tpl])
                                     (implicit conn: Conn, ec: EC): Future[Unit] = {
    conn.rpc.unsubscribe(conn.proxy, q.dataModel).future
  }


  // Save --------------------------------------------------------

  override def save_transact(save: Save)(implicit conn0: Conn, ec: EC): Future[TxReport] = {
    val conn = conn0.asInstanceOf[DatomicConn_JS]
    for {
      _ <- if (save.printInspect) save_inspect(save) else Future.unit
      errors <- save_validate(save) // validate original elements against meta model
      txReport <- errors match {
        case errors if errors.isEmpty => conn.rpc.save(conn.proxy, save.dataModel).future
        case errors                   => throw ValidationErrors(errors)
      }
      _ <- conn.callback(save.dataModel)
    } yield {
      txReport
    }
  }

  override def save_inspect(save: Save)(implicit conn: Conn, ec: EC): Future[String] = {
    renderInspectTx("SAVE", save.dataModel)
  }

  override def save_validate(save: Save)
                            (implicit conn: Conn, ec: EC): Future[Map[String, Seq[String]]] = future {
    TxModelValidation(conn.proxy.metaDb, "save").validate(save.dataModel.elements)
  }


  // Insert --------------------------------------------------------

  override def insert_transact(insert: Insert)(implicit conn0: Conn, ec: EC): Future[TxReport] = {
    val conn = conn0.asInstanceOf[DatomicConn_JS]
    for {
      _ <- if (insert.printInspect) insert_inspect(insert) else Future.unit
      errors <- insert_validate(insert) // validate original elements against meta model
      txReport <- errors match {
        case errors if errors.isEmpty =>
          val tplsSerialized = PickleTpls(insert.dataModel, true).getPickledTpls(insert.tpls)
          conn.rpc.insert(conn.proxy, insert.dataModel, tplsSerialized).future
        case errors                   => throw InsertErrors(errors)
      }
      _ <- conn.callback(insert.dataModel)
    } yield {
      txReport
    }
  }

  override def insert_inspect(insert: Insert)(implicit conn: Conn, ec: EC): Future[String] = {
    renderInspectTx("INSERT", insert.dataModel)
  }

  override def insert_validate(insert: Insert)
                              (implicit conn: Conn, ec: EC): Future[Seq[(Int, Seq[InsertError])]] = future {
    InsertValidation.validate(conn, insert.dataModel.elements, insert.tpls)
  }


  // Update --------------------------------------------------------

  override def update_transact(update: Update)(implicit conn0: Conn, ec: EC): Future[TxReport] = {
    val conn = conn0.asInstanceOf[DatomicConn_JS]
    for {
      _ <- if (update.printInspect) update_inspect(update) else Future.unit
      // Error handling on jvm side since it needs db lookups
      txReport <- conn.rpc.update(conn.proxy, update.dataModel, update.isUpsert).future
      _ <- conn.callback(update.dataModel)
    } yield {
      txReport
    }
  }

  override def update_inspect(update: Update)(implicit conn: Conn, ec: EC): Future[String] = {
    renderInspectTx("UPDATE", update.dataModel)
  }

  override def update_validate(update: Update)
                              (implicit conn: Conn, ec: EC): Future[Map[String, Seq[String]]] = future {
    TxModelValidation(conn.proxy.metaDb, "update").validate(update.dataModel.elements)
  }


  // Delete --------------------------------------------------------

  override def delete_transact(delete: Delete)(implicit conn0: Conn, ec: EC): Future[TxReport] = {
    val conn = conn0.asInstanceOf[DatomicConn_JS]
    for {
      txReport <- conn.rpc.delete(conn.proxy, delete.dataModel).future
      _ <- conn.callback(delete.dataModel, true)
    } yield txReport
  }

  override def delete_inspect(delete: Delete)(implicit conn: Conn, ec: EC): Future[String] = {
    renderInspectTx("DELETE", delete.dataModel)
  }


  // Util

  private def renderInspectTx(label: String, dataModel: DataModel)
                            (implicit ec: EC): Future[String] = {
    Future(renderInspection("RPC " + label, dataModel))
  }
}
