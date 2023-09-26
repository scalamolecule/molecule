package molecule.datalog.datomic.spi

import molecule.base.error._
import molecule.boilerplate.ast.Model._
import molecule.core.action._
import molecule.core.marshalling.ConnProxy
import molecule.core.spi.{Conn, SpiAsync, TxReport}
import molecule.core.transaction.{ResolveDelete, ResolveInsert, ResolveSave, ResolveUpdate}
import molecule.core.util.FutureUtils
import molecule.core.validation.ModelValidation
import molecule.core.validation.insert.InsertValidation
import molecule.datalog.datomic.facade.DatomicConn_JVM
import molecule.datalog.datomic.marshalling.Rpc_datomic.Data
import molecule.datalog.datomic.transaction.{Delete_datomic, Insert_datomic, Save_datomic, Update_datomic}
import scala.concurrent.{Future, ExecutionContext => EC}

object SpiAsync_datomic extends SpiAsync_datomic

trait SpiAsync_datomic
  extends SpiAsync
    with JVMDatomicSpiBase
    with DatomicSpiAsyncBase
    with FutureUtils {

  // Query --------------------------------------------------------


  /*
  found   : [Tpl(in method QueryApiAsync)](q: molecule.core.action.Query[Tpl(in method QueryApiAsync)]): molecule.datalog.datomic.test.api.ZioApi.QueryApiAsync(in trait ApiZio)[Tpl(in method QueryApiAsync)]
  required: [Tpl(in method QueryApiAsync)](q: molecule.core.action.Query[Tpl(in method QueryApiAsync)]): molecule.datalog.datomic.test.api.ZioApi.QueryApiAsync(in trait ApiZioImplicits)[Tpl(in method QueryApiAsync)];
   */

  override def query_get[Tpl](q: Query[Tpl])
                             (implicit conn: Conn, ec: EC): Future[List[Tpl]] = {
    future(SpiSync_datomic.query_get(q))
  }

  override def query_subscribe[Tpl](q: Query[Tpl], callback: List[Tpl] => Unit)
                                   (implicit conn: Conn, ec: EC): Future[Unit] = {
    future(SpiSync_datomic.query_subscribe(q, callback))
  }

  override def query_unsubscribe[Tpl](q: Query[Tpl])
                                     (implicit conn: Conn, ec: EC): Future[Unit] = {
    future(SpiSync_datomic.query_unsubscribe(q))
  }

  override def query_inspect[Tpl](q: Query[Tpl])
                                 (implicit conn: Conn, ec: EC): Future[Unit] = {
    future(SpiSync_datomic.query_inspect(q))
  }


  override def queryOffset_get[Tpl](q: QueryOffset[Tpl])
                                   (implicit conn: Conn, ec: EC): Future[(List[Tpl], Int, Boolean)] = {
    future(SpiSync_datomic.queryOffset_get(q))
  }

  override def queryOffset_inspect[Tpl](q: QueryOffset[Tpl])
                                       (implicit conn: Conn, ec: EC): Future[Unit] = {
    future(SpiSync_datomic.queryOffset_inspect(q))
  }


  override def queryCursor_get[Tpl](q: QueryCursor[Tpl])
                                   (implicit conn: Conn, ec: EC): Future[(List[Tpl], String, Boolean)] = {
    future(SpiSync_datomic.queryCursor_get(q))
  }

  override def queryCursor_inspect[Tpl](q: QueryCursor[Tpl])
                                       (implicit conn: Conn, ec: EC): Future[Unit] = {
    future(SpiSync_datomic.queryCursor_inspect(q))
  }


  // Save --------------------------------------------------------

  override def save_transact(save: Save)(implicit conn: Conn, ec: EC): Future[TxReport] = try {
    if (save.doInspect) save_inspect(save)
    val errors = save_validate(save)
    if (errors.isEmpty) {
      conn.asInstanceOf[DatomicConn_JVM].transact_async(save_getStmts(save, conn.proxy))
        .map { txReport =>
          conn.callback(save.elements)
          txReport
        }
    } else {
      Future.failed(ValidationErrors(errors))
    }
  } catch {
    case e: Throwable => Future.failed(e)
  }

  override def save_inspect(save: Save)(implicit conn: Conn, ec: EC): Future[Unit] = {
    printInspectTx("SAVE", save.elements, save_getStmts(save, conn.proxy))
  }

  private def save_getStmts(save: Save, proxy: ConnProxy): Data = {
    (new ResolveSave(proxy) with Save_datomic).getStmts(save.elements)
  }

  override def save_validate(save: Save)(implicit conn: Conn): Map[String, Seq[String]] = {
    val proxy = conn.proxy
    ModelValidation(proxy.nsMap, proxy.attrMap, "save").validate(save.elements)
  }


  // Insert --------------------------------------------------------

  override def insert_transact(insert: Insert)(implicit conn: Conn, ec: EC): Future[TxReport] = try {
    if (insert.doInspect) insert_inspect(insert)
    val errors = insert_validate(insert)
    if (errors.isEmpty) {
      conn.asInstanceOf[DatomicConn_JVM].transact_async(insert_getStmts(insert, conn.proxy))
        .map { txReport =>
          conn.callback(insert.elements)
          txReport
        }
    } else {
      Future.failed(InsertErrors(errors))
    }
  } catch {
    case e: Throwable => Future.failed(e)
  }

  override def insert_inspect(insert: Insert)(implicit conn: Conn, ec: EC): Future[Unit] = {
    printInspectTx("INSERT", insert.elements, insert_getStmts(insert, conn.proxy))
  }

  private def insert_getStmts(insert: Insert, proxy: ConnProxy): Data = {
    (new ResolveInsert(proxy) with Insert_datomic)
      .getStmts(proxy.nsMap, insert.elements, insert.tpls)
  }

  override def insert_validate(insert: Insert)(implicit conn: Conn): Seq[(Int, Seq[InsertError])] = {
    InsertValidation.validate(conn, insert.elements, insert.tpls)
  }


  // Update --------------------------------------------------------

  override def update_transact(update: Update)(implicit conn0: Conn, ec: EC): Future[TxReport] = try {
    if (update.doInspect) update_inspect(update)
    val errors = update_validate(update)
    if (errors.isEmpty) {
      val conn = conn0.asInstanceOf[DatomicConn_JVM]
      conn.transact_async(update_getStmts(update, conn))
        .map { txReport =>
          conn.callback(update.elements)
          txReport
        }
    } else {
      Future.failed(ValidationErrors(errors))
    }
  } catch {
    case e: Throwable => Future.failed(e)
  }

  override def update_inspect(update: Update)(implicit conn: Conn, ec: EC): Future[Unit] = {
    val action = if (update.isUpsert) "UPSERT" else "UPDATE"
    printInspectTx(action, update.elements, update_getStmts(update, conn.asInstanceOf[DatomicConn_JVM]))
  }

  private def update_getStmts(update: Update, conn: DatomicConn_JVM): Data = {
    (new ResolveUpdate(conn.proxy, update.isUpsert) with Update_datomic)
      .getStmts(conn, update.elements)
  }

  override def update_validate(update: Update)(implicit conn: Conn): Map[String, Seq[String]] = {
    validateUpdate(conn, update)
  }


  // Delete --------------------------------------------------------

  override def delete_transact(delete: Delete)(implicit conn0: Conn, ec: EC): Future[TxReport] = try {
    if (delete.doInspect) delete_inspect(delete)
    val conn = conn0.asInstanceOf[DatomicConn_JVM]
    conn.transact_async(delete_getStmts(delete, conn))
      .map { txReport =>
        conn.callback(delete.elements, true)
        txReport
      }
  } catch {
    case e: Throwable => Future.failed(e)
  }

  override def delete_inspect(delete: Delete)(implicit conn: Conn, ec: EC): Future[Unit] = {
    printInspectTx("DELETE", delete.elements, delete_getStmts(delete, conn.asInstanceOf[DatomicConn_JVM]))
  }

  private def delete_getStmts(delete: Delete, conn: DatomicConn_JVM): Data = {
    (new ResolveDelete with Delete_datomic).getData(conn, delete.elements)
  }


  // Inspect --------------------------------------------------------

  private def printInspectTx(label: String, elements: List[Element], stmts: Data)
                            (implicit ec: EC): Future[Unit] = {
    Future(printInspect(label, elements, stmts.toArray().toList.mkString("\n")))
  }

  override def fallback_rawQuery(
    query: String,
    withNulls: Boolean = false,
    doPrint: Boolean = true,
  )(implicit conn: Conn, ec: EC): Future[List[List[Any]]] = Future {
    SpiSync_datomic.fallback_rawQuery(query, withNulls, doPrint)
  }

  override def fallback_rawTransact(
    txData: String,
    doPrint: Boolean = true
  )(implicit conn: Conn, ec: EC): Future[TxReport] = {
    val debug = if (doPrint) (s: String) => println(s) else (_: String) => ()
    debug("\n=============================================================================")
    debug(txData)

    conn.asInstanceOf[DatomicConn_JVM].transactEdn(txData)
  }
}
