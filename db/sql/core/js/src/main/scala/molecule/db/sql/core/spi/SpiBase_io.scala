package molecule.db.sql.core.spi

import boopickle.Default.*
import cats.effect.IO
import molecule.db.base.error.{InsertError, InsertErrors, MoleculeError, ValidationErrors}
import molecule.db.core.action.*
import molecule.db.core.ast.Element
import molecule.db.core.marshalling.serialize.PickleTpls
import molecule.db.core.spi.{Conn, Renderer, Spi_io, TxReport}
import molecule.db.core.util.Executor.global as ec
import molecule.db.core.util.IOUtils
import molecule.db.core.validation.TxModelValidation
import molecule.db.core.validation.insert.InsertValidation
import molecule.db.sql.core.facade.JdbcConn_JS
import scala.concurrent.Future

trait SpiBase_io
  extends Spi_io
    with Renderer
    with IOUtils {

  // Query --------------------------------------------------------

  override def query_get[Tpl](q: Query[Tpl])(implicit conn0: Conn): IO[List[Tpl]] = {
    val conn = conn0.asInstanceOf[JdbcConn_JS]
    conn.rpc.query[Tpl](conn.proxy, q.dataModel, q.optLimit).io
  }

  override def query_inspect[Tpl](q: Query[Tpl])(implicit conn: Conn): IO[Unit] = {
    printInspectQuery("QUERY", q.dataModel.elements)
  }


  override def query_subscribe[Tpl](q: Query[Tpl], callback: List[Tpl] => Unit)
                                   (implicit conn0: Conn): IO[Unit] = {
    val conn                 = conn0.asInstanceOf[JdbcConn_JS]
    val elements             = q.dataModel.elements
    val involvedAttrs        = getAttrNames(elements)
    val involvedDeleteEntity = getInitialEntity(elements)
    val maybeCallback        = (mutationAttrs: Set[String], isDelete: Boolean) => {
      if (
        mutationAttrs.exists(involvedAttrs.contains) ||
          isDelete && mutationAttrs.head.startsWith(involvedDeleteEntity)
      ) {
        conn.rpc.query[Tpl](conn.proxy, q.dataModel, q.optLimit)
          .map {
            case Right(result)       => callback(result)
            case Left(moleculeError) => throw moleculeError
          }
          .recover {
            case e: MoleculeError =>
              logger.debug(e)
              throw e
            case e: Throwable     =>
              logger.error(e.toString + "\n" + e.getStackTrace.toList.mkString("\n"))
              // Re-throw to preserve original stacktrace
              throw e
          }
      } else Future.unit
    }
    IO(conn.addCallback(q.dataModel -> maybeCallback))
  }

  override def query_unsubscribe[Tpl](q: Query[Tpl])(implicit conn0: Conn): IO[Unit] = {
    IO(conn0.removeCallback(q.dataModel))
  }


  override def queryOffset_get[Tpl](q: QueryOffset[Tpl])
                                   (implicit conn0: Conn): IO[(List[Tpl], Int, Boolean)] = {
    val conn = conn0.asInstanceOf[JdbcConn_JS]
    conn.rpc.queryOffset[Tpl](conn.proxy, q.dataModel, q.optLimit, q.offset).io
  }

  override def queryOffset_inspect[Tpl](q: QueryOffset[Tpl])
                                       (implicit conn: Conn): IO[Unit] = {
    printInspectQuery("QUERY (offset)", q.dataModel.elements)
  }


  override def queryCursor_get[Tpl](q: QueryCursor[Tpl])
                                   (implicit conn0: Conn): IO[(List[Tpl], String, Boolean)] = {
    val conn = conn0.asInstanceOf[JdbcConn_JS]
    conn.rpc.queryCursor[Tpl](conn.proxy, q.dataModel, q.optLimit, q.cursor).io
  }

  override def queryCursor_inspect[Tpl](q: QueryCursor[Tpl])
                                       (implicit conn: Conn): IO[Unit] = {
    printInspectQuery("QUERY (cursor)", q.dataModel.elements)
  }


  // Save --------------------------------------------------------

  override def save_transact(save: Save)(implicit conn0: Conn): IO[TxReport] = {
    val conn = conn0.asInstanceOf[JdbcConn_JS]
    for {
      _ <- if (save.doInspect) save_inspect(save) else IO.unit
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

  override def save_inspect(save: Save)(implicit conn: Conn): IO[Unit] = {
    printInspectTx("SAVE", save.dataModel.elements)
  }

  override def save_validate(save: Save)(implicit conn: Conn): IO[Map[String, Seq[String]]] = IO.blocking {
    if (save.doValidate) {
      val proxy = conn.proxy
      TxModelValidation(proxy.entityMap, proxy.attrMap, "save").validate(save.dataModel.elements)
    } else {
      Map.empty[String, Seq[String]]
    }
  }


  // Insert --------------------------------------------------------

  override def insert_transact(insert: Insert)(implicit conn0: Conn): IO[TxReport] = {
    val conn = conn0.asInstanceOf[JdbcConn_JS]
    for {
      _ <- if (insert.doInspect) insert_inspect(insert) else IO.unit
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

  override def insert_inspect(insert: Insert)(implicit conn: Conn): IO[Unit] = {
    printInspectTx("INSERT", insert.dataModel.elements)
  }

  override def insert_validate(insert: Insert)
                              (implicit conn: Conn): IO[Seq[(Int, Seq[InsertError])]] = IO.blocking {
    if (insert.doValidate) {
      InsertValidation.validate(conn, insert.dataModel.elements, insert.tpls)
    } else {
      Seq.empty[(Int, Seq[InsertError])]
    }
  }


  // Update --------------------------------------------------------

  override def update_transact(update: Update)(implicit conn0: Conn): IO[TxReport] = {
    val conn = conn0.asInstanceOf[JdbcConn_JS]
    for {
      _ <- if (update.doInspect) update_inspect(update) else IO.unit
      // Validating on JVM side only since it requires db lookups
      txReport <- conn.rpc.update(conn.proxy, update.dataModel, update.isUpsert).io
      _ <- conn.callback(update.dataModel).io
    } yield {
      txReport
    }
  }

  override def update_inspect(update: Update)(implicit conn: Conn): IO[Unit] = {
    printInspectTx("UPDATE", update.dataModel.elements)
  }

  override def update_validate(update: Update)
                              (implicit conn: Conn): IO[Map[String, Seq[String]]] = IO.blocking {
    val proxy = conn.proxy
    TxModelValidation(proxy.entityMap, proxy.attrMap, "update").validate(update.dataModel.elements)
  }


  // Delete --------------------------------------------------------

  override def delete_transact(delete: Delete)(implicit conn0: Conn): IO[TxReport] = {
    val conn = conn0.asInstanceOf[JdbcConn_JS]
    for {
      txReport <- conn.rpc.delete(conn.proxy, delete.dataModel).io
      _ <- conn.callback(delete.dataModel, true).io
    } yield txReport
  }

  override def delete_inspect(delete: Delete)(implicit conn: Conn): IO[Unit] = {
    printInspectTx("DELETE", delete.dataModel.elements)
  }


  // Util --------------------------------------

  private def printInspectTx(label: String, elements: List[Element]): IO[Unit] = {
    IO(printRaw("RPC " + label, elements))
  }

  protected def printInspectQuery(label: String, elements: List[Element]): IO[Unit]
}
