package molecule.db.common.spi

import boopickle.Default.*
import cats.effect.IO
import molecule.base.error.{InsertError, InsertErrors, ValidationErrors}
import molecule.core.dataModel.DataModel
import molecule.db.common.action.*
import molecule.db.common.facade.JdbcConn_JS
import molecule.db.common.marshalling.serialize.PickleTpls
import molecule.db.common.spi.{Conn, Renderer, Spi_io, TxReport}
import molecule.db.common.util.Executor.global as ec
//import molecule.db.common.util.Executor.*
import molecule.db.common.util.IOUtils
import molecule.db.common.validation.TxModelValidation
import molecule.db.common.validation.insert.InsertValidation

trait SpiBase_io
  extends Spi_io
    with Renderer
    with IOUtils {

  // Query --------------------------------------------------------

  override def query_get[Tpl](q: Query[Tpl])(using conn0: Conn): IO[List[Tpl]] = {
    val conn = conn0.asInstanceOf[JdbcConn_JS]
    conn.rpc.query[Tpl](conn.proxy, q.dataModel, q.optLimit, q.bindValues).io
  }

  override def query_inspect[Tpl](q: Query[Tpl])(using conn: Conn): IO[String] = {
    renderInspectQuery("QUERY", q.dataModel)
  }


  override def queryOffset_get[Tpl](q: QueryOffset[Tpl])
                                   (using conn0: Conn): IO[(List[Tpl], Int, Boolean)] = {
    val conn = conn0.asInstanceOf[JdbcConn_JS]
    conn.rpc.queryOffset[Tpl](conn.proxy, q.dataModel, q.optLimit, q.offset, q.bindValues).io
  }

  override def queryOffset_inspect[Tpl](q: QueryOffset[Tpl])
                                       (using conn: Conn): IO[String] = {
    renderInspectQuery("QUERY (offset)", q.dataModel)
  }


  override def queryCursor_get[Tpl](q: QueryCursor[Tpl])
                                   (using conn0: Conn): IO[(List[Tpl], String, Boolean)] = {
    val conn = conn0.asInstanceOf[JdbcConn_JS]
    conn.rpc.queryCursor[Tpl](conn.proxy, q.dataModel, q.optLimit, q.cursor, q.bindValues).io
  }

  override def queryCursor_inspect[Tpl](q: QueryCursor[Tpl])
                                       (using conn: Conn): IO[String] = {
    renderInspectQuery("QUERY (cursor)", q.dataModel)
  }


  override def query_subscribe[Tpl](q: Query[Tpl], callback: List[Tpl] => Unit)
                                   (using conn0: Conn): IO[Unit] = {
    val conn = conn0.asInstanceOf[JdbcConn_JS]
    IO(conn.rpc.subscribe[Tpl](conn.proxy, q.dataModel, q.optLimit, callback))
  }

  override def query_unsubscribe[Tpl](q: Query[Tpl])(using conn0: Conn): IO[Unit] = {
    IO(conn0.removeCallback(q.dataModel))
  }


  // Save --------------------------------------------------------

  override def save_transact(save: Save)(using conn0: Conn): IO[TxReport] = {
    val conn = conn0.asInstanceOf[JdbcConn_JS]
    for {
      _ <- if (save.printInspect) save_inspect(save) else IO.unit
      // Validating on JS side since it doesn't require db lookups
      errors <- save_validate(save) // validate original elements against meta model
      txReport <- if (errors.isEmpty) {
        conn.rpc.save(conn.proxy, save.dataModel).io
      } else {
        throw ValidationErrors(errors)
      }
      _ <- conn.callback(save.dataModel).io
    } yield {
      txReport
    }
  }

  override def save_inspect(save: Save)(using conn: Conn): IO[String] = {
    renderInspectTx("SAVE", save.dataModel)
  }

  override def save_validate(save: Save)(using conn: Conn): IO[Map[String, Seq[String]]] = IO.blocking {
    if (save.doValidate) {
      TxModelValidation(conn.proxy.metaDb, "save").validate(save.dataModel.elements)
    } else {
      Map.empty[String, Seq[String]]
    }
  }


  // Insert --------------------------------------------------------

  override def insert_transact(insert: Insert)(using conn0: Conn): IO[TxReport] = {
    val conn = conn0.asInstanceOf[JdbcConn_JS]
    for {
      _ <- if (insert.printInspect) insert_inspect(insert) else IO.unit
      // Validating on JS side since it doesn't require db lookups
      errors <- insert_validate(insert) // validate original elements against meta model
      txReport <- if (errors.isEmpty) {
        if (insert.tpls.isEmpty) {
          IO(TxReport(Nil))
        } else {
          val tplsSerialized = PickleTpls(insert.dataModel, true).getPickledTpls(insert.tpls)
          conn.rpc.insert(conn.proxy, insert.dataModel, tplsSerialized).io
        }
      } else {
        throw InsertErrors(errors)
      }
      _ <- conn.callback(insert.dataModel).io
    } yield {
      txReport
    }
  }

  override def insert_inspect(insert: Insert)(using conn: Conn): IO[String] = {
    renderInspectTx("INSERT", insert.dataModel)
  }

  override def insert_validate(insert: Insert)
                              (using conn: Conn): IO[Seq[(Int, Seq[InsertError])]] = IO.blocking {
    if (insert.doValidate) {
      InsertValidation.validate(conn, insert.dataModel.elements, insert.tpls)
    } else {
      Seq.empty[(Int, Seq[InsertError])]
    }
  }


  // Update --------------------------------------------------------

  override def update_transact(update: Update)(using conn0: Conn): IO[TxReport] = {
    val conn = conn0.asInstanceOf[JdbcConn_JS]
    for {
      _ <- if (update.printInspect) update_inspect(update) else IO.unit
      // Validating on JVM side only since it requires db lookups
      txReport <- conn.rpc.update(conn.proxy, update.dataModel, update.isUpsert).io
      _ <- conn.callback(update.dataModel).io
    } yield {
      txReport
    }
  }

  override def update_inspect(update: Update)(using conn: Conn): IO[String] = {
    renderInspectTx("UPDATE", update.dataModel)
  }

  override def update_validate(update: Update)
                              (using conn: Conn): IO[Map[String, Seq[String]]] = IO.blocking {
    TxModelValidation(conn.proxy.metaDb, "update").validate(update.dataModel.elements)
  }


  // Delete --------------------------------------------------------

  override def delete_transact(delete: Delete)(using conn0: Conn): IO[TxReport] = {
    val conn = conn0.asInstanceOf[JdbcConn_JS]
    for {
      txReport <- conn.rpc.delete(conn.proxy, delete.dataModel).io
      _ <- conn.callback(delete.dataModel, true).io
    } yield txReport
  }

  override def delete_inspect(delete: Delete)(using conn: Conn): IO[String] = {
    renderInspectTx("DELETE", delete.dataModel)
  }


  // Util --------------------------------------

  private def renderInspectTx(label: String, dataModel: DataModel): IO[String] = {
    IO(renderInspection("RPC " + label, dataModel))
  }

  protected def renderInspectQuery(label: String, dataModel: DataModel): IO[String]
}
