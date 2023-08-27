package molecule.datalog.datomic.spi

import molecule.base.error._
import molecule.boilerplate.ast.Model._
import molecule.core.action._
import molecule.core.api.ApiZio
import molecule.core.marshalling.serialize.PickleTpls
import molecule.core.spi.{Conn, SpiZio, TxReport}
import molecule.core.util.Executor._
import molecule.core.util.FutureUtils
import molecule.core.validation.ModelValidation
import molecule.core.validation.insert.InsertValidation
import molecule.datalog.datomic.facade.DatomicConn_JS
import zio._
import scala.concurrent.Future

trait DatomicSpiZio
  extends SpiZio
    with DatomicSpiZioBase
    with ApiZio
    with FutureUtils {

  override def query_get[Tpl](
    q: Query[Tpl]
  ): ZIO[Conn, MoleculeError, List[Tpl]] = {
    getResult((conn: DatomicConn_JS) =>
      conn.rpc.query[Tpl](conn.proxy, q.elements, q.limit).future
    )
  }

  override def query_subscribe[Tpl](
    q: Query[Tpl], callback: List[Tpl] => Unit
  ): ZIO[Conn, Nothing, Unit] = {
    for {
      conn0 <- ZIO.service[Conn]
      conn = conn0.asInstanceOf[DatomicConn_JS]
    } yield {
      try {
        conn.rpc.subscribe[Tpl](conn.proxy, q.elements, q.limit, callback)
      } catch {
        case e: MoleculeError => ZIO.fail(e)
        case e: Throwable     => ZIO.fail(ExecutionError(e.toString))
      }
    }
  }

  override def query_inspect[Tpl](
    q: Query[Tpl]
  ): ZIO[Conn, MoleculeError, Unit] = {
    printInspectQuery("QUERY", q.elements)
  }


  override def queryOffset_get[Tpl](
    q: QueryOffset[Tpl]
  ): ZIO[Conn, MoleculeError, (List[Tpl], Int, Boolean)] = {
    getResult((conn: DatomicConn_JS) =>
      conn.rpc.queryOffset[Tpl](conn.proxy, q.elements, q.limit, q.offset).future
    )
  }

  override def queryOffset_inspect[Tpl](
    q: QueryOffset[Tpl]
  ): ZIO[Conn, MoleculeError, Unit] = {
    printInspectQuery("QUERY (offset)", q.elements)
  }


  override def queryCursor_get[Tpl](
    q: QueryCursor[Tpl]
  ): ZIO[Conn, MoleculeError, (List[Tpl], String, Boolean)] = {
    getResult((conn: DatomicConn_JS) =>
      conn.rpc.queryCursor[Tpl](conn.proxy, q.elements, q.limit, q.cursor).future
    )
  }

  override def queryCursor_inspect[Tpl](
    q: QueryCursor[Tpl]
  ): ZIO[Conn, MoleculeError, Unit] = {
    printInspectQuery("QUERY (cursor)", q.elements)
  }


  override def save_transact(save: Save): ZIO[Conn, MoleculeError, TxReport] = {
    for {
      conn <- ZIO.service[Conn]
      errors <- save_validate(save)
      _ <- ZIO.when(errors.nonEmpty)(ZIO.fail(ValidationErrors(errors)))
      txReport <- transactStmts(conn.rpc.save(conn.proxy, save.elements).future)
    } yield txReport
  }
  override def save_inspect(save: Save): ZIO[Conn, MoleculeError, Unit] = {
    printInspectTx("SAVE", save.elements)
  }
  override def save_validate(save: Save): ZIO[Conn, MoleculeError, Map[String, Seq[String]]] = {
    for {
      conn <- ZIO.service[Conn]
      proxy = conn.proxy
      errors <- ZIO.succeed[Map[String, Seq[String]]](
        ModelValidation(proxy.nsMap, proxy.attrMap, "save").validate(save.elements)
      )
    } yield errors
  }

  override def insert_transact(insert: Insert): ZIO[Conn, MoleculeError, TxReport] = {
    for {
      conn <- ZIO.service[Conn]
      errors <- insert_validate(insert)
      _ <- ZIO.when(errors.nonEmpty)(ZIO.fail(InsertErrors(errors)))
      tplsSerialized = PickleTpls(insert.elements, true).pickle(Right(insert.tpls))
      txReport <- transactStmts(conn.rpc.insert(conn.proxy, insert.elements, tplsSerialized).future)
    } yield txReport
  }
  override def insert_inspect(insert: Insert): ZIO[Conn, MoleculeError, Unit] = {
    printInspectTx("INSERT", insert.elements)
  }
  override def insert_validate(insert: Insert): ZIO[Conn, MoleculeError, Seq[(Int, Seq[InsertError])]] = {
    for {
      conn <- ZIO.service[Conn]
      errors <- ZIO.succeed[Seq[(Int, Seq[InsertError])]](
        InsertValidation.validate(conn, insert.elements, insert.tpls)
      )
    } yield errors
  }

  override def update_transact(update: Update): ZIO[Conn, MoleculeError, TxReport] = {
    for {
      conn <- ZIO.service[Conn]
      errors <- update_validate(update)
      _ <- ZIO.when(errors.nonEmpty)(ZIO.fail(ValidationErrors(errors)))
      txReport <- transactStmts(conn.rpc.update(conn.proxy, update.elements, update.isUpsert).future)
    } yield txReport
  }
  override def update_inspect(update: Update): ZIO[Conn, MoleculeError, Unit] = {
    printInspectTx("UPDATE", update.elements)
  }
  override def update_validate(update: Update): ZIO[Conn, MoleculeError, Map[String, Seq[String]]] = {
    for {
      conn <- ZIO.service[Conn]
      proxy = conn.proxy
      errors <- ZIO.succeed[Map[String, Seq[String]]](
        ModelValidation(proxy.nsMap, proxy.attrMap, "update").validate(update.elements)
      )
    } yield errors
  }

  override def delete_transact(delete: Delete): ZIO[Conn, MoleculeError, TxReport] = {
    for {
      conn <- ZIO.service[Conn]
      txReport <- transactStmts(conn.rpc.delete(conn.proxy, delete.elements).future)
    } yield txReport
  }
  override def delete_inspect(delete: Delete): ZIO[Conn, MoleculeError, Unit] = {
    printInspectTx("DELETE", delete.elements)
  }



  // Helpers ---------

  private def getResult[T](query: DatomicConn_JS => Future[T]): ZIO[Conn, MoleculeError, T] = {
    for {
      conn0 <- ZIO.service[Conn]
      conn = conn0.asInstanceOf[DatomicConn_JS]
      result <- moleculeError(ZIO.fromFuture(_ => query(conn)))
    } yield result
  }

  private def transactStmts(fut: Future[TxReport]): ZIO[Conn, MoleculeError, TxReport] = {
    moleculeError(ZIO.fromFuture(_ => fut))
  }

  private def printInspectTx(label: String, elements: List[Element]): ZIO[Conn, MoleculeError, Unit] = {
    ZIO.succeed(
      printInspect("RPC " + label, elements)
    )
  }
}