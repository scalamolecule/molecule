package molecule.db.datalog.datomic.spi

import cats.effect.IO
import molecule.base.error.{InsertError, InsertErrors, ValidationErrors}
import molecule.core.dataModel.DataModel
import molecule.db.core.action.*
import molecule.db.core.marshalling.serialize.PickleTpls
import molecule.db.core.spi.{Conn, Spi_io, TxReport}
import molecule.db.core.util.Executor.*
import molecule.db.core.validation.TxModelValidation
import molecule.db.core.validation.insert.InsertValidation
import molecule.db.datalog.datomic.facade.DatomicConn_JS
import scala.concurrent.ExecutionContext as EC


trait Spi_datomic_io
  extends Spi_io
    with SpiBase_datomic_io {

  // Query --------------------------------------------------------

  override def query_get[Tpl](q: Query[Tpl])
                             (implicit conn0: Conn): IO[List[Tpl]] = {
    val conn  = conn0.asInstanceOf[DatomicConn_JS]
    val proxy = conn.proxy.copy(dbView = q.dbView)
    conn.rpc.query[Tpl](proxy, q.dataModel, q.optLimit, q.bindValues).io
  }

  override def query_inspect[Tpl](q: Query[Tpl])
                                 (implicit conn: Conn): IO[String] = {
    renderInspectQuery("QUERY", q.dataModel)
  }

  override def queryOffset_get[Tpl](q: QueryOffset[Tpl])
                                   (implicit conn0: Conn): IO[(List[Tpl], Int, Boolean)] = {
    val conn  = conn0.asInstanceOf[DatomicConn_JS]
    val proxy = conn.proxy.copy(dbView = q.dbView)
    conn.rpc.queryOffset[Tpl](proxy, q.dataModel, q.optLimit, q.offset).io
  }

  override def queryOffset_inspect[Tpl](q: QueryOffset[Tpl])
                                       (implicit conn: Conn): IO[String] = {
    renderInspectQuery("QUERY (offset)", q.dataModel)
  }

  override def queryCursor_get[Tpl](q: QueryCursor[Tpl])
                                   (implicit conn0: Conn): IO[(List[Tpl], String, Boolean)] = {
    val conn  = conn0.asInstanceOf[DatomicConn_JS]
    val proxy = conn.proxy.copy(dbView = q.dbView)
    conn.rpc.queryCursor[Tpl](proxy, q.dataModel, q.optLimit, q.cursor).io
  }

  override def queryCursor_inspect[Tpl](q: QueryCursor[Tpl])
                                       (implicit conn: Conn): IO[String] = {
    renderInspectQuery("QUERY (cursor)", q.dataModel)
  }


  override def query_subscribe[Tpl](q: Query[Tpl], callback: List[Tpl] => Unit)
                                   (implicit conn0: Conn): IO[Unit] = {
    val conn = conn0.asInstanceOf[DatomicConn_JS]
    IO(conn.rpc.subscribe[Tpl](conn.proxy, q.dataModel, q.optLimit, callback))
  }

  override def query_unsubscribe[Tpl](q: Query[Tpl])
                                     (implicit conn: Conn): IO[Unit] = {
    IO(conn.removeCallback(q.dataModel))
  }


  // Save --------------------------------------------------------

  override def save_transact(save: Save)(implicit conn0: Conn): IO[TxReport] = {
    val conn = conn0.asInstanceOf[DatomicConn_JS]
    for {
      _ <- if (save.printInspect) save_inspect(save) else IO.unit
      errors <- save_validate(save) // validate original elements against meta model
      txReport <- errors match {
        case errors if errors.isEmpty => conn.rpc.save(conn.proxy, save.dataModel).io
        case errors                   => throw ValidationErrors(errors)
      }
      _ <- conn.callback(save.dataModel).io
    } yield {
      txReport
    }
  }

  override def save_inspect(save: Save)(implicit conn: Conn): IO[String] = {
    renderInspectTx("SAVE", save.dataModel)
  }

  override def save_validate(save: Save)(implicit conn: Conn): IO[Map[String, Seq[String]]] = io {
    TxModelValidation(conn.proxy.metaDb, "save").validate(save.dataModel.elements)
  }


  // Insert --------------------------------------------------------

  override def insert_transact(insert: Insert)(implicit conn0: Conn): IO[TxReport] = {
    val conn = conn0.asInstanceOf[DatomicConn_JS]
    for {
      _ <- if (insert.printInspect) insert_inspect(insert) else IO.unit
      errors <- insert_validate(insert) // validate original elements against meta model
      txReport <- errors match {
        case errors if errors.isEmpty =>
          val tplsSerialized = PickleTpls(insert.dataModel, true).getPickledTpls(insert.tpls)
          conn.rpc.insert(conn.proxy, insert.dataModel, tplsSerialized).io
        case errors                   => throw InsertErrors(errors)
      }
      _ <- conn.callback(insert.dataModel).io
    } yield {
      txReport
    }
  }

  override def insert_inspect(insert: Insert)(implicit conn: Conn): IO[String] = {
    renderInspectTx("INSERT", insert.dataModel)
  }

  override def insert_validate(insert: Insert)(implicit conn: Conn): IO[Seq[(Int, Seq[InsertError])]] = io {
    InsertValidation.validate(conn, insert.dataModel.elements, insert.tpls)
  }


  // Update --------------------------------------------------------

  override def update_transact(update: Update)(implicit conn0: Conn): IO[TxReport] = {
    val conn = conn0.asInstanceOf[DatomicConn_JS]
    for {
      _ <- if (update.printInspect) update_inspect(update) else IO.unit
      errors <- update_validate(update) // validate original elements against meta model
      txReport <- errors match {
        case errors if errors.isEmpty =>
          conn.rpc.update(conn.proxy, update.dataModel, update.isUpsert).io
        case errors                   => throw ValidationErrors(errors)
      }
      _ <- conn.callback(update.dataModel).io
    } yield {
      txReport
    }
  }

  override def update_inspect(update: Update)(implicit conn: Conn): IO[String] = {
    renderInspectTx("UPDATE", update.dataModel)
  }

  override def update_validate(update: Update)(implicit conn: Conn): IO[Map[String, Seq[String]]] = io {
    TxModelValidation(conn.proxy.metaDb, "update").validate(update.dataModel.elements)
  }


  // Delete --------------------------------------------------------

  override def delete_transact(delete: Delete)(implicit conn0: Conn): IO[TxReport] = {
    val conn = conn0.asInstanceOf[DatomicConn_JS]
    for {
      txReport <- conn.rpc.delete(conn.proxy, delete.dataModel).io
      _ <- conn.callback(delete.dataModel, true).io
    } yield txReport
  }

  override def delete_inspect(delete: Delete)(implicit conn: Conn): IO[String] = {
    renderInspectTx("DELETE", delete.dataModel)
  }


  // Util

  private def renderInspectTx(label: String, dataModel: DataModel)
                            (implicit ec: EC): IO[String] = {
    IO(renderInspection("RPC " + label, dataModel))
  }
}
