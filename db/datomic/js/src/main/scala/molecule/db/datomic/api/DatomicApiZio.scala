package molecule.db.datomic.api

import molecule.base.util.exceptions.MoleculeError
import molecule.core.action.Insert
import molecule.core.api.{ApiZio, Connection, TxReport}
import molecule.core.marshalling.serialize.PickleTpls
import molecule.core.util.Executor._
import molecule.core.util.FutureUtils
import molecule.db.datomic.action._
import molecule.db.datomic.facade.DatomicConn_JS
import zio._
import scala.concurrent.Future

trait DatomicApiZio extends ApiZio with FutureUtils {

  implicit class datomicQueryApiZio[Tpl](q: DatomicQuery[Tpl]) extends QueryApi[Tpl] {
    override def get: ZIO[Connection, MoleculeError, List[Tpl]] = {
      getResult((conn: DatomicConn_JS) =>
        conn.rpc.query[Tpl](conn.proxy, q.elements, q.limit).future
      )
    }
    override def inspect: ZIO[Connection, MoleculeError, Unit] = ???
  }

  implicit class datomicQueryOffsetApiZio[Tpl](q: DatomicQueryOffset[Tpl]) extends QueryOffsetApi[Tpl] {
    override def get: ZIO[Connection, MoleculeError, (List[Tpl], Int, Boolean)] = {
      getResult((conn: DatomicConn_JS) =>
        conn.rpc.queryOffset[Tpl](conn.proxy, q.elements, q.limit, q.offset).future
      )
    }
    override def inspect: ZIO[Connection, MoleculeError, Unit] = ???
  }

  implicit class datomicQueryCursorApiZio[Tpl](q: DatomicQueryCursor[Tpl]) extends QueryCursorApi[Tpl] {
    override def get: ZIO[Connection, MoleculeError, (List[Tpl], String, Boolean)] = {
      getResult((conn: DatomicConn_JS) =>
        conn.rpc.queryCursor[Tpl](conn.proxy, q.elements, q.limit, q.cursor).future
      )
    }
    override def inspect: ZIO[Connection, MoleculeError, Unit] = ???
  }


  implicit class datomicSaveApiZio[Tpl](save: DatomicSave) extends SaveApi {
    override def transact: ZIO[Connection, MoleculeError, TxReport] = {
      for {
        conn <- ZIO.service[Connection]
        txReport <- transactStmts(conn.rpc.save(conn.proxy, save.elements).future)
      } yield txReport
    }
  }

  implicit class datomicInsertApiZio[Tpl](insert0: Insert) extends InsertApi {
    val insert = insert0.asInstanceOf[DatomicInsert_JS]
    override def transact: ZIO[Connection, MoleculeError, TxReport] = {
      for {
        conn <- ZIO.service[Connection]
        (tplElements, txElements) = splitElements(insert.elements)
        tplsSerialized = PickleTpls(tplElements, true).pickle(Right(insert.tpls))
        txReport <- transactStmts(conn.rpc.insert(conn.proxy, tplElements, tplsSerialized, txElements).future)
      } yield txReport
    }
  }

  implicit class datomicUpdateApiZio[Tpl](update: DatomicUpdate) extends UpdateApi {
    override def transact: ZIO[Connection, MoleculeError, TxReport] = {
      for {
        conn <- ZIO.service[Connection]
        txReport <- transactStmts(conn.rpc.update(conn.proxy, update.elements, update.isUpsert).future)
      } yield txReport
    }
  }

  implicit class datomicDeleteApiZio[Tpl](delete: DatomicDelete) extends DeleteApi {
    override def transact: ZIO[Connection, MoleculeError, TxReport] = {
      for {
        conn <- ZIO.service[Connection]
        txReport <- transactStmts(conn.rpc.delete(conn.proxy, delete.elements).future)
      } yield txReport
    }
  }


  // Helpers ---------

  private def getResult[T](query: DatomicConn_JS => Future[T]): ZIO[Connection, MoleculeError, T] = for {
    conn0 <- ZIO.service[Connection]
    conn = conn0.asInstanceOf[DatomicConn_JS]
    result <- moleculeError(ZIO.fromFuture(_ => query(conn)))
  } yield result

  private def transactStmts(fut: Future[TxReport]): ZIO[Connection, MoleculeError, TxReport] = {
    moleculeError(ZIO.fromFuture(_ => fut ))
  }

  private def moleculeError[T](result: Task[T]): ZIO[Connection, MoleculeError, T] = {
    result.mapError {
      case e: MoleculeError => e
      case e: Throwable     => MoleculeError(e.toString, e)
    }
  }
}
