package molecule.sql.core.spi

import boopickle.Default._
import cats.effect.IO
import molecule.base.error.{InsertError, InsertErrors, MoleculeError, ValidationErrors}
import molecule.boilerplate.ast.Model._
import molecule.core.action._
import molecule.core.marshalling.serialize.PickleTpls
import molecule.core.spi.{Conn, Renderer, Spi_io, TxReport}
import molecule.core.util.Executor.{global => ec}
import molecule.core.util.IOUtils
import molecule.core.validation.TxModelValidation
import molecule.core.validation.insert.InsertValidation
import molecule.sql.core.facade.JdbcConn_JS
import scala.concurrent.Future


trait SpiBase_io
  extends Spi_io
    with Renderer
    with IOUtils {

  // Query --------------------------------------------------------

  override def query_get[Tpl](q: Query[Tpl])(implicit conn0: Conn): IO[List[Tpl]] = {
    val conn  = conn0.asInstanceOf[JdbcConn_JS]
    val proxy = conn.proxy.copy(dbView = q.dbView)
    conn.rpc.query[Tpl](proxy, q.elements, q.optLimit).io
  }

  override def query_subscribe[Tpl](q: Query[Tpl], callback: List[Tpl] => Unit)
                                   (implicit conn0: Conn): IO[Unit] = {
    val conn             = conn0.asInstanceOf[JdbcConn_JS]
    val elements         = q.elements
    val involvedAttrs    = getAttrNames(elements)
    val involvedDeleteNs = getInitialNs(elements)
    val maybeCallback    = (mutationAttrs: Set[String], isDelete: Boolean) => {
      if (
        mutationAttrs.exists(involvedAttrs.contains) ||
          isDelete && mutationAttrs.head.startsWith(involvedDeleteNs)
      ) {
        conn.rpc.query[Tpl](conn.proxy, q.elements, q.optLimit)
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
    IO(conn.addCallback(elements -> maybeCallback))
  }

  override def query_unsubscribe[Tpl](q: Query[Tpl])(implicit conn0: Conn): IO[Unit] = {
    IO(conn0.removeCallback(q.elements))
  }

  override def query_inspect[Tpl](q: Query[Tpl])(implicit conn: Conn): IO[Unit] = {
    printInspectQuery("QUERY", q.elements)
  }


  override def queryOffset_get[Tpl](q: QueryOffset[Tpl])
                                   (implicit conn0: Conn): IO[(List[Tpl], Int, Boolean)] = {
    val conn  = conn0.asInstanceOf[JdbcConn_JS]
    val proxy = conn.proxy.copy(dbView = q.dbView)
    conn.rpc.queryOffset[Tpl](proxy, q.elements, q.optLimit, q.offset).io
  }

  override def queryOffset_inspect[Tpl](q: QueryOffset[Tpl])
                                       (implicit conn: Conn): IO[Unit] = {
    printInspectQuery("QUERY (offset)", q.elements)
  }


  override def queryCursor_get[Tpl](q: QueryCursor[Tpl])
                                   (implicit conn0: Conn): IO[(List[Tpl], String, Boolean)] = {
    val conn  = conn0.asInstanceOf[JdbcConn_JS]
    val proxy = conn.proxy.copy(dbView = q.dbView)
    conn.rpc.queryCursor[Tpl](proxy, q.elements, q.optLimit, q.cursor).io
  }

  override def queryCursor_inspect[Tpl](q: QueryCursor[Tpl])
                                       (implicit conn: Conn): IO[Unit] = {
    printInspectQuery("QUERY (cursor)", q.elements)
  }


  // Save --------------------------------------------------------

  override def save_transact(save: Save)(implicit conn0: Conn): IO[TxReport] = {
    val conn = conn0.asInstanceOf[JdbcConn_JS]
    for {
      _ <- if (save.doInspect) save_inspect(save) else IO.unit
      // Validating on JS side since it doesn't require db lookups
      errors <- save_validate(save) // validate original elements against meta model
      txReport <- if (errors.isEmpty) {
        conn.rpc.save(conn.proxy, save.elements).io
      } else {
        throw ValidationErrors(errors)
      }
      _ <- conn.callback(save.elements).io
    } yield {
      txReport
    }
  }

  override def save_inspect(save: Save)(implicit conn: Conn): IO[Unit] = {
    printInspectTx("SAVE", save.elements)
  }

  override def save_validate(save: Save)(implicit conn: Conn): IO[Map[String, Seq[String]]] = IO {
    if (save.doValidate) {
      val proxy = conn.proxy
      TxModelValidation(proxy.nsMap, proxy.attrMap, "save").validate(save.elements)
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
          val tplsSerialized = PickleTpls(insert.elements, true).pickleEither(Right(insert.tpls))
          conn.rpc.insert(conn.proxy, insert.elements, tplsSerialized).io
        }
      } else {
        throw InsertErrors(errors)
      }
      _ <- conn.callback(insert.elements).io
    } yield {
      txReport
    }
  }

  override def insert_inspect(insert: Insert)(implicit conn: Conn): IO[Unit] = {
    printInspectTx("INSERT", insert.elements)
  }

  override def insert_validate(insert: Insert)
                              (implicit conn: Conn): IO[Seq[(Int, Seq[InsertError])]] = IO {
    if (insert.doValidate) {
      InsertValidation.validate(conn, insert.elements, insert.tpls)
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
      txReport <- conn.rpc.update(conn.proxy, update.elements, update.isUpsert).io
      _ <- conn.callback(update.elements).io
    } yield {
      txReport
    }
  }

  override def update_inspect(update: Update)(implicit conn: Conn): IO[Unit] = {
    printInspectTx("UPDATE", update.elements)
  }

  override def update_validate(update: Update)
                              (implicit conn: Conn): IO[Map[String, Seq[String]]] = IO {
    val proxy = conn.proxy
    TxModelValidation(proxy.nsMap, proxy.attrMap, "update").validate(update.elements)
  }


  // Delete --------------------------------------------------------

  override def delete_transact(delete: Delete)(implicit conn0: Conn): IO[TxReport] = {
    val conn = conn0.asInstanceOf[JdbcConn_JS]
    for {
      txReport <- conn.rpc.delete(conn.proxy, delete.elements).io
      _ <- conn.callback(delete.elements, true).io
    } yield txReport
  }

  override def delete_inspect(delete: Delete)(implicit conn: Conn): IO[Unit] = {
    printInspectTx("DELETE", delete.elements)
  }


  // Util --------------------------------------

  private def printInspectTx(label: String, elements: List[Element]): IO[Unit] = {
    IO(printRaw("RPC " + label, elements))
  }

  protected def printInspectQuery(label: String, elements: List[Element]): IO[Unit]
}
