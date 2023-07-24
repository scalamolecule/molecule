package molecule.datalog.datomic.spi

import molecule.base.error._
import molecule.boilerplate.ast.Model._
import molecule.core.action._
import molecule.core.marshalling.ConnProxy
import molecule.core.spi.{Conn, SpiZio, TxReport}
import molecule.core.transaction.{DeleteExtraction, InsertExtraction, SaveExtraction, UpdateExtraction}
import molecule.core.util.Executor._
import molecule.core.validation.ModelValidation
import molecule.core.validation.insert.InsertValidation
import molecule.datalog.datomic.facade.DatomicConn_JVM
import molecule.datalog.datomic.marshalling.DatomicRpcJVM.Data
import molecule.datalog.datomic.query.{DatomicQueryResolveCursor, DatomicQueryResolveOffset}
import molecule.datalog.datomic.subscription.SubscriptionStarter
import molecule.datalog.datomic.transaction.{Data_Delete, Data_Insert, Data_Save, Data_Update}
import zio.ZIO
import scala.concurrent.Future

trait DatomicSpiZio
  extends SpiZio
    with JVMDatomicSpiBase
    with SubscriptionStarter
    with DatomicSpiZioBase {

  // Query --------------------------------------------------------

  override def query_get[Tpl](
    q: Query[Tpl]
  ): ZIO[Conn, MoleculeError, List[Tpl]] = {
    getResult[List[Tpl]]((conn: DatomicConn_JVM) =>
      DatomicQueryResolveOffset[Tpl](q.elements, q.limit, None, q.dbView)
        .getListFromOffset_async(conn, global).map(_._1)
    )
  }

  override def query_subscribe[Tpl](
    q: Query[Tpl], callback: List[Tpl] => Unit
  ): ZIO[Conn, MoleculeError, Unit] = {
    for {
      conn0 <- ZIO.service[Conn]
      datomicConn = conn0.asInstanceOf[DatomicConn_JVM]
      res <- ZIO.succeed(DatomicQueryResolveOffset[Tpl](q.elements, q.limit, None, q.dbView)
        .subscribe(datomicConn, getWatcher(datomicConn), callback))
    } yield res
  }

  override def query_inspect[Tpl](
    q: Query[Tpl]
  ): ZIO[Conn, MoleculeError, Unit] = {
    printInspectQuery("QUERY", q.elements)
  }


  override def queryOffset_get[Tpl](
    q: QueryOffset[Tpl]
  ): ZIO[Conn, MoleculeError, (List[Tpl], Int, Boolean)] = {
    getResult[(List[Tpl], Int, Boolean)]((conn: DatomicConn_JVM) =>
      DatomicQueryResolveOffset[Tpl](q.elements, q.limit, Some(q.offset), q.dbView)
        .getListFromOffset_async(conn, global)
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
    getResult[(List[Tpl], String, Boolean)]((conn: DatomicConn_JVM) =>
      DatomicQueryResolveCursor[Tpl](q.elements, q.limit, Some(q.cursor), q.dbView)
        .getListFromCursor_async(conn, global)
    )
  }

  override def queryCursor_inspect[Tpl](
    q: QueryCursor[Tpl]
  ): ZIO[Conn, MoleculeError, Unit] = {
    printInspectQuery("QUERY (cursor)", q.elements)
  }


  // Save --------------------------------------------------------

  override def save_transact(save: Save): ZIO[Conn, MoleculeError, TxReport] = {
    for {
      errors <- save_validate(save)
      _ <- ZIO.when(errors.nonEmpty)(ZIO.fail(ValidationErrors(errors)))
      stmts <- ZIO.succeed(save_getStmts(save))
      txReport <- transactStmts(stmts)
    } yield txReport
  }

  override def save_inspect(save: Save): ZIO[Conn, MoleculeError, Unit] = {
    printInspectTx("SAVE", save.elements, save_getStmts(save))
  }

  private def save_getStmts(save: Save): Data = {
    (new SaveExtraction() with Data_Save).getStmts(save.elements)
  }

  override def save_validate(save: Save): ZIO[Conn, MoleculeError, Map[String, Seq[String]]] = {
    for {
      conn0 <- ZIO.service[Conn]
      conn = conn0.asInstanceOf[DatomicConn_JVM]
      proxy = conn.proxy
      errors <- ZIO.succeed[Map[String, Seq[String]]](
        ModelValidation(proxy.nsMap, proxy.attrMap, "save").validate(save.elements)
      )
    } yield errors
  }


  // Insert --------------------------------------------------------

  override def insert_transact(insert: Insert): ZIO[Conn, MoleculeError, TxReport] = {
    for {
      conn0 <- ZIO.service[Conn]
      errors <- insert_validate(insert)
      _ <- ZIO.when(errors.nonEmpty)(ZIO.fail(InsertErrors(errors)))
      conn = conn0.asInstanceOf[DatomicConn_JVM]
      stmts <- ZIO.succeed(insert_getStmts(insert, conn.proxy))
      txReport <- transactStmts(stmts)
    } yield txReport
  }

  override def insert_inspect(insert: Insert): ZIO[Conn, MoleculeError, Unit] = {
    for {
      conn0 <- ZIO.service[Conn]
      conn = conn0.asInstanceOf[DatomicConn_JVM]
    } yield printInspectTx("INSERT", insert.elements, insert_getStmts(insert, conn.proxy))
  }

  private def insert_getStmts(insert: Insert, proxy: ConnProxy): Data = {
    (new InsertExtraction with Data_Insert)
      .getStmts(proxy.nsMap, insert.elements, insert.tpls)
  }

  override def insert_validate(insert: Insert): ZIO[Conn, MoleculeError, Seq[(Int, Seq[InsertError])]] = {
    for {
      conn0 <- ZIO.service[Conn]
      conn = conn0.asInstanceOf[DatomicConn_JVM]
      errors <- ZIO.succeed[Seq[(Int, Seq[InsertError])]](
        InsertValidation.validate(conn, insert.elements, insert.tpls)
      )
    } yield errors
  }


  // Update --------------------------------------------------------

  override def update_transact(update: Update): ZIO[Conn, MoleculeError, TxReport] = {
    for {
      conn0 <- ZIO.service[Conn]
      errors <- update_validate(update)
      _ <- ZIO.when(errors.nonEmpty)(ZIO.fail(ValidationErrors(errors)))
      conn = conn0.asInstanceOf[DatomicConn_JVM]
      stmts <- ZIO.succeed(update_getStmts(update, conn))
      txReport <- transactStmtsWithConn(conn, stmts)
    } yield txReport
  }

  override def update_inspect(update: Update): ZIO[Conn, MoleculeError, Unit] = {
    for {
      conn0 <- ZIO.service[Conn]
      conn = conn0.asInstanceOf[DatomicConn_JVM]
      res <- printInspectTx("UPDATE", update.elements, update_getStmts(update, conn))
    } yield res
  }

  private def update_getStmts(update: Update, conn: DatomicConn_JVM): Data = {
    (new UpdateExtraction(conn.proxy.uniqueAttrs, update.isUpsert) with Data_Update)
      .getStmts(conn, update.elements)
  }

  override def update_validate(update: Update): ZIO[Conn, MoleculeError, Map[String, Seq[String]]] = {
    for {
      conn0 <- ZIO.service[Conn]
      errors <- ZIO.succeed[Map[String, Seq[String]]](validateUpdate(conn0, update.elements))
    } yield errors
  }


  // Delete --------------------------------------------------------

  override def delete_transact(delete: Delete): ZIO[Conn, MoleculeError, TxReport] = {
    for {
      conn0 <- ZIO.service[Conn]
      conn = conn0.asInstanceOf[DatomicConn_JVM]
      stmts <- ZIO.succeed(delete_getStmts(delete, conn))
      txReport <- transactStmtsWithConn(conn, stmts)
    } yield txReport
  }

  override def delete_inspect(delete: Delete): ZIO[Conn, MoleculeError, Unit] = {
    for {
      conn0 <- ZIO.service[Conn]
      conn = conn0.asInstanceOf[DatomicConn_JVM]
      res <- printInspectTx("DELETE", delete.elements, delete_getStmts(delete, conn))
    } yield res
  }

  private def delete_getStmts(delete: Delete, conn: DatomicConn_JVM): Data = {
    (new DeleteExtraction with Data_Delete).getStmtsData(conn, delete.elements)
  }


  // Helpers ---------

  private def getResult[T](query: DatomicConn_JVM => Future[T]): ZIO[Conn, MoleculeError, T] = {
    for {
      conn0 <- ZIO.service[Conn]
      conn = conn0.asInstanceOf[DatomicConn_JVM]
      result <- moleculeError(ZIO.fromFuture(_ => query(conn)))
    } yield result
  }

  private def transactStmts(stmts: Data): ZIO[Conn, MoleculeError, TxReport] = {
    for {
      conn <- ZIO.service[Conn]
      txReport <- transactStmtsWithConn(conn.asInstanceOf[DatomicConn_JVM], stmts)
    } yield txReport
  }

  private def transactStmtsWithConn(conn: DatomicConn_JVM, stmts: Data): ZIO[Conn, MoleculeError, TxReport] = {
    moleculeError(ZIO.fromFuture(_ => conn.transact_async(stmts)))
  }

  private def printInspectTx(
    label: String,
    elements: List[Element],
    stmts: Data
  ): ZIO[Conn, MoleculeError, Unit] = {
    ZIO.succeed(
      printInspect(label, elements, stmts.toArray().toList.mkString("\n"))
    )
  }
}