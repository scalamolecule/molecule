package molecule.sql.core.api

import molecule.base.error._
import molecule.boilerplate.ast.Model._
import molecule.core.action._
import molecule.core.api.{ApiZio, Connection, TxReport}
import molecule.core.marshalling.serialize.PickleTpls
import molecule.core.util.Executor._
import molecule.core.util.FutureUtils
import molecule.core.validation.ModelValidation
import molecule.core.validation.insert.InsertValidation
import molecule.sql.core.facade.SqlConn_JS
import zio._
import scala.concurrent.Future

trait SqlApiZio extends SqlZioApiBase with ApiZio with FutureUtils {

  implicit class datomicQueryApiZio[Tpl](q: Query[Tpl]) extends QueryApi[Tpl] {
    override def get: ZIO[Connection, MoleculeError, List[Tpl]] = {
      getResult((conn: SqlConn_JS) =>
        conn.rpc.query[Tpl](conn.proxy, q.elements, q.limit).future
      )
    }

    override def subscribe(callback: List[Tpl] => Unit): ZIO[Connection, MoleculeError, Unit] = {
      for {
        conn0 <- ZIO.service[Connection]
        conn = conn0.asInstanceOf[SqlConn_JS]
      } yield {
        try {
          conn.rpc.subscribe[Tpl](conn.proxy, q.elements, q.limit, callback)
        } catch {
          case e: MoleculeError => ZIO.fail(e)
          case e: Throwable     => ZIO.fail(ExecutionError(e.toString))
        }
      }
    }

    override def inspect: ZIO[Connection, MoleculeError, Unit] = {
      printInspectQuery("QUERY", q.elements)
    }
  }


  implicit class datomicQueryOffsetApiZio[Tpl](q: QueryOffset[Tpl]) extends QueryOffsetApi[Tpl] {
    override def get: ZIO[Connection, MoleculeError, (List[Tpl], Int, Boolean)] = {
      getResult((conn: SqlConn_JS) =>
        conn.rpc.queryOffset[Tpl](conn.proxy, q.elements, q.limit, q.offset).future
      )
    }

    override def inspect: ZIO[Connection, MoleculeError, Unit] = {
      printInspectQuery("QUERY (offset)", q.elements)
    }
  }


  implicit class datomicQueryCursorApiZio[Tpl](q: QueryCursor[Tpl]) extends QueryCursorApi[Tpl] {
    override def get: ZIO[Connection, MoleculeError, (List[Tpl], String, Boolean)] = {
      getResult((conn: SqlConn_JS) =>
        conn.rpc.queryCursor[Tpl](conn.proxy, q.elements, q.limit, q.cursor).future
      )
    }

    override def inspect: ZIO[Connection, MoleculeError, Unit] = {
      printInspectQuery("QUERY (cursor)", q.elements)
    }
  }


  implicit class datomicSaveApiZio[Tpl](save: Save) extends SaveTransaction {
    override def transact: ZIO[Connection, MoleculeError, TxReport] = {
      for {
        conn <- ZIO.service[Connection]
        errors <- validate
        _ <- ZIO.when(errors.nonEmpty)(ZIO.fail(ValidationErrors(errors)))
        txReport <- transactStmts(conn.rpc.save(conn.proxy, save.elements).future)
      } yield txReport
    }

    override def inspect: ZIO[Connection, MoleculeError, Unit] = {
      printInspectTx("SAVE", save.elements)
    }

    override def validate: ZIO[Connection, MoleculeError, Map[String, Seq[String]]] = {
      for {
        conn <- ZIO.service[Connection]
        proxy = conn.proxy
        errors <- ZIO.succeed[Map[String, Seq[String]]](
          ModelValidation(proxy.nsMap, proxy.attrMap, "save").validate(save.elements)
        )
      } yield errors
    }
  }


  implicit class datomicInsertApiZio[Tpl](insert0: Insert) extends InsertTransaction {
    val insert = insert0.asInstanceOf[InsertTpls]
    override def transact: ZIO[Connection, MoleculeError, TxReport] = {
      for {
        conn <- ZIO.service[Connection]
        errors <- validate
        _ <- ZIO.when(errors.nonEmpty)(ZIO.fail(InsertErrors(errors)))
        (tplElements, txElements) = separateTxElements(insert.elements)
        tplsSerialized = PickleTpls(tplElements, true).pickle(Right(insert.tpls))
        txReport <- transactStmts(conn.rpc.insert(conn.proxy, tplElements, tplsSerialized, txElements).future)
      } yield txReport
    }

    override def inspect: ZIO[Connection, MoleculeError, Unit] = {
      printInspectTx("INSERT", insert.elements)
    }

    override def validate: ZIO[Connection, MoleculeError, Seq[(Int, Seq[InsertError])]] = {
      for {
        conn <- ZIO.service[Connection]
        errors <- ZIO.succeed[Seq[(Int, Seq[InsertError])]](
          InsertValidation.validate(conn, insert.elements, insert.tpls)
        )
      } yield errors
    }
  }


  implicit class datomicUpdateApiZio[Tpl](update: Update) extends UpdateTransaction {
    override def transact: ZIO[Connection, MoleculeError, TxReport] = {
      for {
        conn <- ZIO.service[Connection]
        errors <- validate
        _ <- ZIO.when(errors.nonEmpty)(ZIO.fail(ValidationErrors(errors)))
        txReport <- transactStmts(conn.rpc.update(conn.proxy, update.elements, update.isUpsert).future)
      } yield txReport
    }

    override def inspect: ZIO[Connection, MoleculeError, Unit] = {
      printInspectTx("UPDATE", update.elements)
    }

    override def validate: ZIO[Connection, MoleculeError, Map[String, Seq[String]]] = {
      for {
        conn <- ZIO.service[Connection]
        proxy = conn.proxy
        errors <- ZIO.succeed[Map[String, Seq[String]]](
          ModelValidation(proxy.nsMap, proxy.attrMap, "update").validate(update.elements)
        )
      } yield errors
    }
  }


  implicit class datomicDeleteApiZio[Tpl](delete: Delete) extends DeleteTransaction {
    override def transact: ZIO[Connection, MoleculeError, TxReport] = {
      for {
        conn <- ZIO.service[Connection]
        txReport <- transactStmts(conn.rpc.delete(conn.proxy, delete.elements).future)
      } yield txReport
    }

    override def inspect: ZIO[Connection, MoleculeError, Unit] = {
      printInspectTx("DELETE", delete.elements)
    }
  }


  // Helpers ---------

  private def getResult[T](query: SqlConn_JS => Future[T]): ZIO[Connection, MoleculeError, T] = {
    for {
      conn0 <- ZIO.service[Connection]
      conn = conn0.asInstanceOf[SqlConn_JS]
      result <- moleculeError(ZIO.fromFuture(_ => query(conn)))
    } yield result
  }

  private def transactStmts(fut: Future[TxReport]): ZIO[Connection, MoleculeError, TxReport] = {
    moleculeError(ZIO.fromFuture(_ => fut))
  }

  private def printInspectTx(label: String, elements: List[Element]): ZIO[Connection, MoleculeError, Unit] = {
    ZIO.succeed(
      printInspect("RPC " + label, elements)
    )
  }
}
