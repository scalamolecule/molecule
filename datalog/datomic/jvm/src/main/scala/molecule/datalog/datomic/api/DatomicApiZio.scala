package molecule.datalog.datomic.api

import molecule.base.error._
import molecule.boilerplate.ast.Model._
import molecule.core.action._
import molecule.core.api.{ApiZio, Connection, TxReport}
import molecule.core.marshalling.ConnProxy
import molecule.core.transaction.{DeleteExtraction, InsertExtraction, SaveExtraction, UpdateExtraction}
import molecule.core.util.Executor._
import molecule.core.validation.ModelValidation
import molecule.core.validation.insert.InsertValidation
import molecule.datalog.datomic.facade.DatomicConn_JVM
import molecule.datalog.datomic.subscription.SubscriptionStarter
import molecule.datalog.datomic.transaction.{Data_Delete, Data_Insert, Data_Save, Data_Update}
import molecule.datalog.datomic.marshalling.DatomicRpcJVM.Data
import molecule.datalog.datomic.query.{DatomicQueryResolveCursor, DatomicQueryResolveOffset}
import zio._
import scala.concurrent.Future


trait DatomicApiZio extends JVMDatomicApiBase with SubscriptionStarter with DatomicZioApiBase with ApiZio {

  implicit class datomicQueryApiZio[Tpl](q: Query[Tpl]) extends QueryApi[Tpl] {
    override def get: ZIO[Connection, MoleculeError, List[Tpl]] = {
      getResult[List[Tpl]]((conn: DatomicConn_JVM) =>
        DatomicQueryResolveOffset[Tpl](q.elements, q.limit, None, q.dbView)
          .getListFromOffset_async(conn, global).map(_._1)
      )
    }

    override def subscribe(callback: List[Tpl] => Unit): ZIO[Connection, Nothing, Unit] = {
      for {
        conn0 <- ZIO.service[Connection]
        datomicConn = conn0.asInstanceOf[DatomicConn_JVM]
        res <- ZIO.succeed(DatomicQueryResolveOffset[Tpl](q.elements, q.limit, None, q.dbView)
          .subscribe(datomicConn, getWatcher(datomicConn), callback))
      } yield res
    }

    override def inspect: ZIO[Connection, MoleculeError, Unit] = {
      printInspectQuery("QUERY", q.elements)
    }
  }


  implicit class datomicQueryOffsetApiZio[Tpl](q: QueryOffset[Tpl]) extends QueryOffsetApi[Tpl] {
    override def get: ZIO[Connection, MoleculeError, (List[Tpl], Int, Boolean)] = {
      getResult[(List[Tpl], Int, Boolean)]((conn: DatomicConn_JVM) =>
        DatomicQueryResolveOffset[Tpl](q.elements, q.limit, Some(q.offset), q.dbView)
          .getListFromOffset_async(conn, global)
      )
    }

    override def inspect: ZIO[Connection, MoleculeError, Unit] = {
      printInspectQuery("QUERY (offset)", q.elements)
    }
  }


  implicit class datomicQueryCursorApiZio[Tpl](q: QueryCursor[Tpl]) extends QueryCursorApi[Tpl] {
    override def get: ZIO[Connection, MoleculeError, (List[Tpl], String, Boolean)] = {
      getResult[(List[Tpl], String, Boolean)]((conn: DatomicConn_JVM) =>
        DatomicQueryResolveCursor[Tpl](q.elements, q.limit, Some(q.cursor), q.dbView)
          .getListFromCursor_async(conn, global)
      )
    }

    override def inspect: ZIO[Connection, MoleculeError, Unit] = {
      printInspectQuery("QUERY (cursor)", q.elements)
    }
  }


  implicit class datomicSaveApiZio[Tpl](save: Save) extends SaveTransaction {
    override def transact: ZIO[Connection, MoleculeError, TxReport] = {
      for {
        conn0 <- ZIO.service[Connection]
        errors <- validate
        _ <- ZIO.when(errors.nonEmpty)(ZIO.fail(ValidationErrors(errors)))
        conn = conn0.asInstanceOf[DatomicConn_JVM]
        stmts <- ZIO.succeed(getStmts)
        txReport <- transactStmts(stmts)
      } yield txReport
    }

    override def inspect: ZIO[Connection, MoleculeError, Unit] = {
      for {
        conn0 <- ZIO.service[Connection]
        conn = conn0.asInstanceOf[DatomicConn_JVM]
      } yield printInspectTx("SAVE", save.elements, getStmts)
    }

    private def getStmts: Data = {
      (new SaveExtraction() with Data_Save).getStmts(save.elements)
    }

    override def validate: ZIO[Connection, MoleculeError, Map[String, Seq[String]]] = {
      for {
        conn0 <- ZIO.service[Connection]
        conn = conn0.asInstanceOf[DatomicConn_JVM]
        proxy = conn.proxy
        errors <- ZIO.succeed[Map[String, Seq[String]]](
          ModelValidation(proxy.schema.nsMap, proxy.schema.attrMap, "save").validate(save.elements)
        )
      } yield errors
    }
  }


  implicit class datomicInsertApiZio[Tpl](insert0: Insert) extends InsertTransaction {
    val insert = insert0.asInstanceOf[InsertTpls]
    override def transact: ZIO[Connection, MoleculeError, TxReport] = {
      for {
        conn0 <- ZIO.service[Connection]
        errors <- validate
        _ <- ZIO.when(errors.nonEmpty)(ZIO.fail(InsertErrors(errors)))
        conn = conn0.asInstanceOf[DatomicConn_JVM]
        stmts <- ZIO.succeed(getStmts(conn.proxy))
        txReport <- transactStmts(stmts)
      } yield txReport
    }

    override def inspect: ZIO[Connection, MoleculeError, Unit] = {
      for {
        conn0 <- ZIO.service[Connection]
        conn = conn0.asInstanceOf[DatomicConn_JVM]
      } yield
        printInspectTx("INSERT", insert.elements, getStmts(conn.proxy))
    }

    private def getStmts(proxy: ConnProxy): Data = {
      (new InsertExtraction with Data_Insert)
        .getStmts(proxy.schema.nsMap, insert.elements, insert.tpls)
    }

    override def validate: ZIO[Connection, MoleculeError, Seq[(Int, Seq[InsertError])]] = {
      for {
        conn0 <- ZIO.service[Connection]
        conn = conn0.asInstanceOf[DatomicConn_JVM]
        errors <- ZIO.succeed[Seq[(Int, Seq[InsertError])]](
          InsertValidation.validate(conn, insert.elements, insert.tpls)
        )
      } yield errors
    }
  }


  implicit class datomicUpdateApiZio[Tpl](update: Update) extends UpdateTransaction {
    override def transact: ZIO[Connection, MoleculeError, TxReport] = {
      for {
        conn0 <- ZIO.service[Connection]
        errors <- validate
        _ <- ZIO.when(errors.nonEmpty)(ZIO.fail(ValidationErrors(errors)))
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

    private def getStmts(conn: DatomicConn_JVM): Data = {
      (new UpdateExtraction(conn.proxy.schema.uniqueAttrs, update.isUpsert) with Data_Update)
        .getStmts(conn, update.elements)
    }

    override def validate: ZIO[Connection, MoleculeError, Map[String, Seq[String]]] = {
      for {
        conn0 <- ZIO.service[Connection]
        errors <- ZIO.succeed[Map[String, Seq[String]]](validateUpdate(conn0, update.elements))
      } yield errors
    }
  }


  implicit class datomicDeleteApiZio[Tpl](delete: Delete) extends DeleteTransaction {
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
    private def getStmts(conn: DatomicConn_JVM): Data = {
      (new DeleteExtraction with Data_Delete).getStmtsData(conn, delete.elements)
    }
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
