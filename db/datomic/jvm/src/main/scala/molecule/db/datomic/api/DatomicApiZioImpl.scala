package molecule.db.datomic.api

import molecule.base.util.exceptions.MoleculeError
import molecule.core.action.Insert
import molecule.core.api.{ApiZio, Connection, TxReport}
import molecule.core.transaction.{DeleteExtraction, InsertExtraction, SaveExtraction, UpdateExtraction}
import molecule.core.util.Executor._
import molecule.db.datomic.action._
import molecule.db.datomic.facade.DatomicConn_JVM
import molecule.db.datomic.marshalling.DatomicRpcJVM.Data
import molecule.db.datomic.query._
import molecule.db.datomic.transaction.{Delete_stmts, Insert_stmts, Save_stmts, Update_stmts}
import zio._
import scala.concurrent.Future

trait DatomicApiZioImpl extends ApiZio {

  implicit class datomicQueryApiZio[Tpl](q: DatomicQueryImpl[Tpl]) extends QueryApi[Tpl] {
    override def get: ZIO[Connection, MoleculeError, List[Tpl]] = {
      getResult[List[Tpl]]((conn: DatomicConn_JVM) =>
        DatomicQueryResolveOffset[Tpl](q.elements, q.limit, None)
          .getListFromOffset_async(conn, global).map(_._1)
      )
    }
    override def inspect: ZIO[Connection, MoleculeError, Unit] = ???
  }

  implicit class datomicQueryOffsetApiZio[Tpl](q: DatomicQueryImplOffset[Tpl]) extends QueryOffsetApi[Tpl] {
    override def get: ZIO[Connection, MoleculeError, (List[Tpl], Int, Boolean)] = {
      getResult[(List[Tpl], Int, Boolean)]((conn: DatomicConn_JVM) =>
        DatomicQueryResolveOffset[Tpl](q.elements, q.limit, Some(q.offset))
          .getListFromOffset_async(conn, global)
      )
    }
    override def inspect: ZIO[Connection, MoleculeError, Unit] = ???
  }

  implicit class datomicQueryCursorApiZio[Tpl](q: DatomicQueryImplCursor[Tpl]) extends QueryCursorApi[Tpl] {
    override def get: ZIO[Connection, MoleculeError, (List[Tpl], String, Boolean)] = {
      getResult[(List[Tpl], String, Boolean)]((conn: DatomicConn_JVM) =>
        DatomicQueryResolveCursor[Tpl](q.elements, q.limit, Some(q.cursor))
          .getListFromCursor_async(conn, global)
      )
    }
    override def inspect: ZIO[Connection, MoleculeError, Unit] = ???
  }


  implicit class datomicSaveApiZio[Tpl](save: DatomicSaveImpl) extends SaveApi {
    override def transact: ZIO[Connection, MoleculeError, TxReport] = for {
      stmts <- ZIO.succeed(new SaveExtraction() with Save_stmts).map(_.getStmts(save.elements))
      txReport <- transactStmts(stmts)
    } yield txReport
  }

  implicit class datomicInsertApiZio[Tpl](insert0: Insert) extends InsertApi {
    val insert = insert0.asInstanceOf[DatomicInsertImpl_JVM]
    override def transact: ZIO[Connection, MoleculeError, TxReport] = for {
      stmts <- ZIO.succeed(new InsertExtraction with Insert_stmts).map(_.getStmts(insert.elements, insert.tpls))
      txReport <- transactStmts(stmts)
    } yield txReport
  }

  implicit class datomicUpdateApiZio[Tpl](update: DatomicUpdateImpl) extends UpdateApi {
    override def transact: ZIO[Connection, MoleculeError, TxReport] = for {
      conn0 <- ZIO.service[Connection]
      conn = conn0.asInstanceOf[DatomicConn_JVM]
      stmts <- ZIO.succeed(new UpdateExtraction(conn.proxy.uniqueAttrs, update.isUpsert) with Update_stmts)
        .map(_.getStmts(conn, update.elements))
      txReport <- transactStmtsWithConn(conn, stmts)
    } yield txReport
  }

  implicit class datomicDeleteApiZio[Tpl](delete: DatomicDeleteImpl) extends DeleteApi {
    override def transact: ZIO[Connection, MoleculeError, TxReport] = for {
      conn0 <- ZIO.service[Connection]
      conn = conn0.asInstanceOf[DatomicConn_JVM]
      stmts <- ZIO.succeed(new DeleteExtraction with Delete_stmts).map(_.getStmtsData(conn, delete.elements))
      txReport <- transactStmtsWithConn(conn, stmts)
    } yield txReport
  }


  // Helpers ---------

  private def getResult[T](query: DatomicConn_JVM => Future[T]): ZIO[Connection, MoleculeError, T] = for {
    conn0 <- ZIO.service[Connection]
    conn = conn0.asInstanceOf[DatomicConn_JVM]
    result <- ZIO.fromFuture(_ => query(conn)).mapError {
      case e: MoleculeError => e
      case e: Throwable     => MoleculeError(e.toString, e)
    }
  } yield result

  private def transactStmts(stmts: Data): ZIO[Connection, MoleculeError, TxReport] = {
    for {
      conn <- ZIO.service[Connection]
      txReport <- transactStmtsWithConn(conn.asInstanceOf[DatomicConn_JVM], stmts)
    } yield txReport
  }

  private def transactStmtsWithConn(conn: DatomicConn_JVM, stmts: Data): ZIO[Connection, MoleculeError, TxReport] = {
    moleculeErrorTx(ZIO.fromFuture(_ => conn.transact_async(stmts)))
  }

  private def moleculeErrorTx(result: Task[TxReport]): ZIO[Connection, MoleculeError, TxReport] = {
    result.mapError {
      case e: MoleculeError => e
      case e: Throwable     => MoleculeError(e.toString, e)
    }
  }
}
