package molecule.db.datalog.datomic.spi

import cats.effect.IO
import molecule.base.error.{InsertError, InsertErrors, ValidationErrors}
import molecule.db.core.action.*
import molecule.db.core.spi.{Conn, Spi_async, TxReport}
import molecule.db.core.util.FutureUtils
import molecule.db.datalog.core.spi.StreamingDatomic
import molecule.db.datalog.datomic.facade.DatomicConn_JVM
import molecule.db.datalog.datomic.transaction.DatomicDataType_JVM
import scala.concurrent.{Future, ExecutionContext as EC}

object Spi_datomic_async extends Spi_datomic_async

trait Spi_datomic_async
  extends Spi_async
    with JVMDatomicSpiBase
    with StreamingDatomic
    with SpiBase_datomic_async
    with DatomicDataType_JVM
    with FutureUtils {

  // Query --------------------------------------------------------

  override def query_get[Tpl](
    q: Query[Tpl]
  )(implicit conn: Conn, ec: EC): Future[List[Tpl]] = {
    future(Spi_datomic_sync.query_get(q))
  }

  override def query_inspect[Tpl](
    q: Query[Tpl]
  )(implicit conn: Conn, ec: EC): Future[String] = {
    future(Spi_datomic_sync.query_inspect(q))
  }


  override def queryOffset_get[Tpl](
    q: QueryOffset[Tpl]
  )(implicit conn: Conn, ec: EC): Future[(List[Tpl], Int, Boolean)] = {
    future(Spi_datomic_sync.queryOffset_get(q))
  }

  override def queryOffset_inspect[Tpl](
    q: QueryOffset[Tpl]
  )(implicit conn: Conn, ec: EC): Future[String] = {
    future(Spi_datomic_sync.queryOffset_inspect(q))
  }


  override def queryCursor_get[Tpl](
    q: QueryCursor[Tpl]
  )(implicit conn: Conn, ec: EC): Future[(List[Tpl], String, Boolean)] = {
    future(Spi_datomic_sync.queryCursor_get(q))
  }

  override def queryCursor_inspect[Tpl](
    q: QueryCursor[Tpl]
  )(implicit conn: Conn, ec: EC): Future[String] = {
    future(Spi_datomic_sync.queryCursor_inspect(q))
  }


  override def query_stream[Tpl](
    q: Query[Tpl], chunkSize: Int
  )(implicit conn0: Conn, ec: EC): fs2.Stream[IO, Tpl] = {
    fs2streamDatomic(
      q, chunkSize,
      (q: Query[Tpl], conn: Conn) => Spi_datomic_sync.query_inspect[Tpl](q)(conn),
      Spi_datomic_sync.getJavaStreamAndRowResolver[Tpl]
    )
  }


  override def query_subscribe[Tpl](
    q: Query[Tpl], callback: List[Tpl] => Unit
  )(implicit conn: Conn, ec: EC): Future[Unit] = {
    future(Spi_datomic_sync.query_subscribe(q, callback))
  }

  override def query_unsubscribe[Tpl](
    q: Query[Tpl]
  )(implicit conn: Conn, ec: EC): Future[Unit] = {
    future(Spi_datomic_sync.query_unsubscribe(q))
  }


  // Save --------------------------------------------------------

  override def save_transact(
    save: Save
  )(implicit conn0: Conn, ec: EC): Future[TxReport] = {
    val conn = conn0.asInstanceOf[DatomicConn_JVM]
    for {
      _ <- if (save.printInspect) save_inspect(save) else Future.unit
      errors <- save_validate(save)
      txReport <- errors match {
        case errors if errors.isEmpty =>
          conn.transact_async(save_getStmts(save))
        case errors                   => throw ValidationErrors(errors)
      }
      _ <- conn.callback(save.dataModel)
    } yield {
      txReport
    }
  }

  override def save_inspect(
    save: Save
  )(implicit conn: Conn, ec: EC): Future[String] = future {
    Spi_datomic_sync.save_inspect(save)
  }

  private def save_getStmts(save: Save): Data = {
    Spi_datomic_sync.save_getStmts(save)
  }

  override def save_validate(
    save: Save
  )(implicit conn: Conn, ec: EC): Future[Map[String, Seq[String]]] = future {
    Spi_datomic_sync.save_validate(save)
  }


  // Insert --------------------------------------------------------

  override def insert_transact(
    insert: Insert //, rpc: Boolean = false
  )(implicit conn0: Conn, ec: EC): Future[TxReport] = {
    val conn = conn0.asInstanceOf[DatomicConn_JVM]
    for {
      _ <- if (insert.printInspect) insert_inspect(insert) else Future.unit
      errors <- insert_validate(insert)
      txReport <- errors match {
        case errors if errors.isEmpty =>
          conn.transact_async(insert_getStmts(insert))
        case errors                   => throw InsertErrors(errors)
      }
      _ <- conn.callback(insert.dataModel)
    } yield {
      txReport
    }
  }

  override def insert_inspect(
    insert: Insert
  )(implicit conn: Conn, ec: EC): Future[String] = future {
    Spi_datomic_sync.insert_inspect(insert)
  }

  private def insert_getStmts(insert: Insert): Data = {
    Spi_datomic_sync.insert_getStmts(insert)
  }

  override def insert_validate(
    insert: Insert
  )(implicit conn: Conn, ec: EC): Future[Seq[(Int, Seq[InsertError])]] = future {
    Spi_datomic_sync.insert_validate(insert)
  }


  // Update --------------------------------------------------------

  override def update_transact(
    update: Update
  )(implicit conn0: Conn, ec: EC): Future[TxReport] = {
    val conn = conn0.asInstanceOf[DatomicConn_JVM]
    for {
      _ <- if (update.printInspect) update_inspect(update) else Future.unit
      errors <- update_validate(update)
      txReport <- errors match {
        case errors if errors.isEmpty =>
          conn.transact_async(update_getStmts(update, conn))
        case errors                   => throw ValidationErrors(errors)
      }
      _ <- conn.callback(update.dataModel)
    } yield {
      txReport
    }
  }

  override def update_inspect(
    update: Update
  )(implicit conn: Conn, ec: EC): Future[String] = future {
    Spi_datomic_sync.update_inspect(update)
  }

  def update_getStmts(update: Update, conn: DatomicConn_JVM): Data = {
    Spi_datomic_sync.update_getStmts(update, conn)
  }

  override def update_validate(
    update: Update
  )(implicit conn: Conn, ec: EC): Future[Map[String, Seq[String]]] = future {
    Spi_datomic_sync.update_validate(update)
  }


  // Delete --------------------------------------------------------

  override def delete_transact(
    delete: Delete
  )(implicit conn0: Conn, ec: EC): Future[TxReport] = {
    val conn = conn0.asInstanceOf[DatomicConn_JVM]
    for {
      _ <- if (delete.printInspect) delete_inspect(delete) else Future.unit
      txReport <- conn.transact_async(delete_getStmts(delete, conn))
      _ <- conn.callback(delete.dataModel, true)
    } yield {
      txReport
    }
  }

  override def delete_inspect(
    delete: Delete
  )(implicit conn: Conn, ec: EC): Future[String] = future {
    Spi_datomic_sync.delete_inspect(delete)
  }

  private def delete_getStmts(delete: Delete, conn: DatomicConn_JVM): Data = {
    Spi_datomic_sync.delete_getStmts(delete, conn)
  }


  // Fallbacks --------------------------------------------------------

  override def fallback_rawQuery(
    query: String, debug: Boolean = false,
  )(implicit conn: Conn, ec: EC): Future[List[List[Any]]] = future {
    Spi_datomic_sync.fallback_rawQuery(query, debug)
  }

  override def fallback_rawTransact(
    txData: String, debugFlag: Boolean = false
  )(implicit conn: Conn, ec: EC): Future[TxReport] = {
    val debug = if (debugFlag) (s: String) => println(s) else (_: String) => ()
    debug("\n=============================================================================")
    debug(txData)
    conn.asInstanceOf[DatomicConn_JVM].transactEdn(txData)
  }
}
