package molecule.datomic.api

import molecule.base.util.exceptions.MoleculeError
import molecule.boilerplate.ast.Model._
import molecule.core.action.Insert
import molecule.core.api.{ApiZio, Connection, TxReport}
import molecule.core.transaction.{DeleteExtraction, InsertExtraction, SaveExtraction, UpdateExtraction}
import molecule.core.util.Executor._
import molecule.datomic.action._
import molecule.datomic.facade.DatomicConn_JVM
import molecule.datomic.marshalling.DatomicRpcJVM.Data
import molecule.datomic.query.{DatomicQueryResolveCursor, DatomicQueryResolveOffset}
import molecule.datomic.transaction.{Delete_stmts, Insert_stmts, Save_stmts, Update_stmts}
import zio._
import scala.concurrent.Future


trait DatomicApiZio extends DatomicZioApiBase with ApiZio {

  implicit class datomicQueryApiZio[Tpl](q: DatomicQuery[Tpl]) extends QueryApi[Tpl] {
    override def get: ZIO[Connection, MoleculeError, List[Tpl]] = {
      getResult[List[Tpl]]((conn: DatomicConn_JVM) =>
        DatomicQueryResolveOffset[Tpl](q.elements, q.limit, None)
          .getListFromOffset_async(conn, global).map(_._1)
      )
    }
    override def inspect: ZIO[Connection, MoleculeError, Unit] =
      printInspectQuery("QUERY", q.elements)
  }

  implicit class datomicQueryOffsetApiZio[Tpl](q: DatomicQueryOffset[Tpl]) extends QueryOffsetApi[Tpl] {
    override def get: ZIO[Connection, MoleculeError, (List[Tpl], Int, Boolean)] = {
      getResult[(List[Tpl], Int, Boolean)]((conn: DatomicConn_JVM) =>
        DatomicQueryResolveOffset[Tpl](q.elements, q.limit, Some(q.offset))
          .getListFromOffset_async(conn, global)
      )
    }
    override def inspect: ZIO[Connection, MoleculeError, Unit] =
      printInspectQuery("QUERY (offset)", q.elements)
  }

  implicit class datomicQueryCursorApiZio[Tpl](q: DatomicQueryCursor[Tpl]) extends QueryCursorApi[Tpl] {
    override def get: ZIO[Connection, MoleculeError, (List[Tpl], String, Boolean)] = {
      getResult[(List[Tpl], String, Boolean)]((conn: DatomicConn_JVM) =>
        DatomicQueryResolveCursor[Tpl](q.elements, q.limit, Some(q.cursor))
          .getListFromCursor_async(conn, global)
      )
    }
    override def inspect: ZIO[Connection, MoleculeError, Unit] =
      printInspectQuery("QUERY (cursor)", q.elements)
  }


  implicit class datomicSaveApiZio[Tpl](save: DatomicSave) extends Transaction {
    override def transact: ZIO[Connection, MoleculeError, TxReport] = {
      for {
        stmts <- ZIO.succeed(getStmts)
        txReport <- transactStmts(stmts)
      } yield txReport
    }

    override def inspect: ZIO[Connection, MoleculeError, Unit] =
      printInspectTx("SAVE", save.elements, getStmts)

    private def getStmts: Data =
      (new SaveExtraction() with Save_stmts).getStmts(save.elements)
  }


  implicit class datomicInsertApiZio[Tpl](insert0: Insert) extends Transaction {
    val insert = insert0.asInstanceOf[DatomicInsert_JVM]
    override def transact: ZIO[Connection, MoleculeError, TxReport] = {
      for {
        stmts <- ZIO.succeed(getStmts)
        txReport <- transactStmts(stmts)
      } yield txReport
    }

    override def inspect: ZIO[Connection, MoleculeError, Unit] =
      printInspectTx("INSERT", insert.elements, getStmts)

    private def getStmts: Data =
      (new InsertExtraction with Insert_stmts).getStmts(insert.elements, insert.tpls)
  }


  implicit class datomicUpdateApiZio[Tpl](update: DatomicUpdate) extends Transaction {
    override def transact: ZIO[Connection, MoleculeError, TxReport] = {
      for {
        conn0 <- ZIO.service[Connection]
        conn = conn0.asInstanceOf[DatomicConn_JVM]
        stmts <- ZIO.succeed(getStmts(conn))
        txReport <- transactStmtsWithConn(conn, stmts)
      } yield txReport
    }

    override def inspect: ZIO[Connection, MoleculeError, Unit] = {
      for {
        conn0 <- ZIO.service[Connection]
        conn = conn0.asInstanceOf[DatomicConn_JVM]
        res <- printInspectTx("UPDATE", update.elements, getStmts(conn))
      } yield res
    }

    private def getStmts(conn: DatomicConn_JVM): Data =
      (new UpdateExtraction(conn.proxy.uniqueAttrs, update.isUpsert) with Update_stmts)
        .getStmts(conn, update.elements)
  }


  implicit class datomicDeleteApiZio[Tpl](delete: DatomicDelete) extends Transaction {
    override def transact: ZIO[Connection, MoleculeError, TxReport] = {
      for {
        conn0 <- ZIO.service[Connection]
        conn = conn0.asInstanceOf[DatomicConn_JVM]
        stmts <- ZIO.succeed(getStmts(conn))
        txReport <- transactStmtsWithConn(conn, stmts)
      } yield txReport
    }
    override def inspect: ZIO[Connection, MoleculeError, Unit] = {
      for {
        conn0 <- ZIO.service[Connection]
        conn = conn0.asInstanceOf[DatomicConn_JVM]
        res <- printInspectTx("DELETE", delete.elements, getStmts(conn))
      } yield res
    }

    private def getStmts(conn: DatomicConn_JVM): Data =
      (new DeleteExtraction with Delete_stmts).getStmtsData(conn, delete.elements)
  }


  // Helpers ---------

  private def getResult[T](query: DatomicConn_JVM => Future[T]): ZIO[Connection, MoleculeError, T] = {
    for {
      conn0 <- ZIO.service[Connection]
      conn = conn0.asInstanceOf[DatomicConn_JVM]
      result <- moleculeError(ZIO.fromFuture(_ => query(conn)))
    } yield result
  }

  private def transactStmts(stmts: Data): ZIO[Connection, MoleculeError, TxReport] = {
    for {
      conn <- ZIO.service[Connection]
      txReport <- transactStmtsWithConn(conn.asInstanceOf[DatomicConn_JVM], stmts)
    } yield txReport
  }

  private def transactStmtsWithConn(conn: DatomicConn_JVM, stmts: Data): ZIO[Connection, MoleculeError, TxReport] = {
    moleculeError(ZIO.fromFuture(_ => conn.transact_async(stmts)))
  }

  private def printInspectTx(
    label: String,
    elements: List[Element],
    stmts: Data
  ): ZIO[Connection, MoleculeError, Unit] = {
    ZIO.succeed(
      printInspect(label, elements, stmts.toArray().toList.mkString("\n"))
    )
  }
}
