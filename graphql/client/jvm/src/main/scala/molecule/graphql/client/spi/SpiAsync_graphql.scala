package molecule.graphql.client.spi

import molecule.base.error._
import molecule.core.action._
import molecule.core.marshalling.ConnProxy
import molecule.core.spi.{Conn, SpiAsync, TxReport}
import molecule.core.util.FutureUtils
import molecule.graphql.client.facade.GraphqlConn_JVM
import molecule.graphql.client.transaction.GraphqlDataType_JVM
//import molecule.datalog.datomic.facade.DatomicConn_JVM
//import molecule.datalog.datomic.marshalling.Rpc_datomic.Data
import scala.concurrent.{Future, ExecutionContext => EC}

object SpiAsync_graphql extends SpiAsync_graphql

trait SpiAsync_graphql
  extends SpiAsync
//    with JVMDatomicSpiBase
//    with DatomicSpiAsyncBase
    with GraphqlDataType_JVM
    with FutureUtils {

  // Query --------------------------------------------------------

  override def query_get[Tpl](q: Query[Tpl])
                             (implicit conn: Conn, ec: EC): Future[List[Tpl]] = {
//    future(SpiSync_datomic.query_get(q))
    ???
  }

  override def query_subscribe[Tpl](q: Query[Tpl], callback: List[Tpl] => Unit)
                                   (implicit conn: Conn, ec: EC): Future[Unit] = {
//    future(SpiSync_datomic.query_subscribe(q, callback))
    ???
  }

  override def query_unsubscribe[Tpl](q: Query[Tpl])
                                     (implicit conn: Conn, ec: EC): Future[Unit] = {
//    future(SpiSync_datomic.query_unsubscribe(q))
    ???
  }

  override def query_inspect[Tpl](q: Query[Tpl])
                                 (implicit conn: Conn, ec: EC): Future[Unit] = {
//    future(SpiSync_datomic.query_inspect(q))
    ???
  }


  override def queryOffset_get[Tpl](q: QueryOffset[Tpl])
                                   (implicit conn: Conn, ec: EC): Future[(List[Tpl], Int, Boolean)] = {
//    future(SpiSync_datomic.queryOffset_get(q))
    ???
  }

  override def queryOffset_inspect[Tpl](q: QueryOffset[Tpl])
                                       (implicit conn: Conn, ec: EC): Future[Unit] = {
//    future(SpiSync_datomic.queryOffset_inspect(q))
    ???
  }


  override def queryCursor_get[Tpl](q: QueryCursor[Tpl])
                                   (implicit conn: Conn, ec: EC): Future[(List[Tpl], String, Boolean)] = {
//    future(SpiSync_datomic.queryCursor_get(q))
    ???
  }

  override def queryCursor_inspect[Tpl](q: QueryCursor[Tpl])
                                       (implicit conn: Conn, ec: EC): Future[Unit] = {
//    future(SpiSync_datomic.queryCursor_inspect(q))
    ???
  }


  // Save --------------------------------------------------------

  override def save_transact(save: Save)(implicit conn0: Conn, ec: EC): Future[TxReport] = {
//    val conn = conn0.asInstanceOf[DatomicConn_JVM]
//    for {
//      _ <- if (save.doInspect) save_inspect(save) else Future.unit
//      errors <- save_validate(save)
//      txReport <- errors match {
//        case errors if errors.isEmpty => conn.transact_async(save_getStmts(save))
//        case errors                   => throw ValidationErrors(errors)
//      }
//    } yield {
//      conn.callback(save.elements)
//      txReport
//    }
    ???
  }

  override def save_inspect(save: Save)(implicit conn: Conn, ec: EC): Future[Unit] = future {
//    SpiSync_datomic.save_inspect(save)
    ???
  }

  private def save_getStmts(save: Save): Data = {
//    SpiSync_datomic.save_getStmts(save)
    ???
  }

  override def save_validate(save: Save)(implicit conn: Conn, ec: EC): Future[Map[String, Seq[String]]] = future {
//    SpiSync_datomic.save_validate(save)
    ???
  }


  // Insert --------------------------------------------------------

  override def insert_transact(insert: Insert)(implicit conn0: Conn, ec: EC): Future[TxReport] = {
//    val conn = conn0.asInstanceOf[DatomicConn_JVM]
//    for {
//      _ <- if (insert.doInspect) insert_inspect(insert) else Future.unit
//      errors <- insert_validate(insert)
//      txReport <- errors match {
//        case errors if errors.isEmpty => conn.transact_async(insert_getStmts(insert, conn.proxy))
//        case errors                   => throw InsertErrors(errors)
//      }
//    } yield {
//      conn.callback(insert.elements)
//      txReport
//    }
    ???
  }

  override def insert_inspect(insert: Insert)(implicit conn: Conn, ec: EC): Future[Unit] = future {
//    SpiSync_datomic.insert_inspect(insert)
    ???
  }

  private def insert_getStmts(insert: Insert, proxy: ConnProxy): Data = {
//    SpiSync_datomic.insert_getStmts(insert, proxy)
    ???
  }

  override def insert_validate(insert: Insert)
                              (implicit conn: Conn, ec: EC): Future[Seq[(Int, Seq[InsertError])]] = future {
//    SpiSync_datomic.insert_validate(insert)
    ???
  }


  // Update --------------------------------------------------------

  override def update_transact(update: Update)(implicit conn0: Conn, ec: EC): Future[TxReport] = {
//    val conn = conn0.asInstanceOf[DatomicConn_JVM]
//    for {
//      _ <- if (update.doInspect) update_inspect(update) else Future.unit
//      errors <- update_validate(update)
//      txReport <- errors match {
//        case errors if errors.isEmpty => conn.transact_async(update_getStmts(update, conn))
//        case errors                   => throw ValidationErrors(errors)
//      }
//    } yield {
//      conn.callback(update.elements)
//      txReport
//    }
    ???
  }

  override def update_inspect(update: Update)(implicit conn: Conn, ec: EC): Future[Unit] = future {
//    SpiSync_datomic.update_inspect(update)
    ???
  }

  private def update_getStmts(update: Update, conn: GraphqlConn_JVM): Data = {
//    SpiSync_datomic.update_getStmts(update, conn)
    ???
  }

  override def update_validate(update: Update)(implicit conn: Conn, ec: EC): Future[Map[String, Seq[String]]] = future {
//    SpiSync_datomic.update_validate(update)
    ???
  }


  // Delete --------------------------------------------------------

  override def delete_transact(delete: Delete)(implicit conn0: Conn, ec: EC): Future[TxReport] = {
//    val conn = conn0.asInstanceOf[DatomicConn_JVM]
//    for {
//      _ <- if (delete.doInspect) delete_inspect(delete) else Future.unit
//      txReport <- conn.transact_async(delete_getStmts(delete, conn))
//    } yield {
//      conn.callback(delete.elements, true)
//      txReport
//    }
    ???
  }

  override def delete_inspect(delete: Delete)(implicit conn: Conn, ec: EC): Future[Unit] = future {
//    SpiSync_datomic.delete_inspect(delete)
    ???
  }

  private def delete_getStmts(delete: Delete, conn: GraphqlConn_JVM): Data = {
//    SpiSync_datomic.delete_getStmts(delete, conn)
    ???
  }


  // Fallbacks --------------------------------------------------------

  override def fallback_rawQuery(
    query: String,
    debug: Boolean = false,
  )(implicit conn: Conn, ec: EC): Future[List[List[Any]]] = future {
//    SpiSync_datomic.fallback_rawQuery(query, debug)
    ???
  }

  override def fallback_rawTransact(
    txData: String,
    debugFlag: Boolean = false
  )(implicit conn: Conn, ec: EC): Future[TxReport] = {
//    val debug = if (debugFlag) (s: String) => println(s) else (_: String) => ()
//    debug("\n=============================================================================")
//    debug(txData)
//    conn.asInstanceOf[DatomicConn_JVM].transactEdn(txData)
    ???
  }
}
