package molecule.datalog.datomic.spi

import molecule.base.error._
import molecule.boilerplate.ast.Model._
import molecule.core.action._
import molecule.core.marshalling.ConnProxy
import molecule.core.spi.{SpiAsync, TxReport, Conn}
import molecule.core.transaction.{ResolveDelete, ResolveInsert, ResolveSave, ResolveUpdate}
import molecule.core.util.FutureUtils
import molecule.core.validation.ModelValidation
import molecule.core.validation.insert.InsertValidation
import molecule.datalog.datomic.facade.DatomicConn_JVM
import molecule.datalog.datomic.marshalling.DatomicRpcJVM.Data
import molecule.datalog.datomic.transaction.{Data_Delete, Data_Insert, Data_Save, Data_Update}
import scala.concurrent.{Future, ExecutionContext => EC}

object DatomicSpiAsync extends DatomicSpiAsync

trait DatomicSpiAsync
  extends SpiAsync
    with JVMDatomicSpiBase
    with DatomicSpiAsyncBase
    with FutureUtils {

  // Query --------------------------------------------------------

  override def query_get[Tpl](q: Query[Tpl])
                             (implicit conn: Conn, ec: EC): Future[List[Tpl]] = {
    future(DatomicSpiSync.query_get(q))
  }

  override def query_subscribe[Tpl](q: Query[Tpl], callback: List[Tpl] => Unit)
                                   (implicit conn: Conn, ec: EC): Future[Unit] = {
    future(DatomicSpiSync.query_subscribe(q, callback))
  }

  override def query_inspect[Tpl](q: Query[Tpl])
                                 (implicit conn: Conn, ec: EC): Future[Unit] = {
    future(DatomicSpiSync.query_inspect(q))
  }


  override def queryOffset_get[Tpl](q: QueryOffset[Tpl])
                                   (implicit conn: Conn, ec: EC): Future[(List[Tpl], Int, Boolean)] = {
    future(DatomicSpiSync.queryOffset_get(q))
  }

  override def queryOffset_inspect[Tpl](q: QueryOffset[Tpl])
                                       (implicit conn: Conn, ec: EC): Future[Unit] = {
    future(DatomicSpiSync.queryOffset_inspect(q))
  }


  override def queryCursor_get[Tpl](q: QueryCursor[Tpl])
                                   (implicit conn: Conn, ec: EC): Future[(List[Tpl], String, Boolean)] = {
    future(DatomicSpiSync.queryCursor_get(q))
  }

  override def queryCursor_inspect[Tpl](q: QueryCursor[Tpl])
                                       (implicit conn: Conn, ec: EC): Future[Unit] = {
    future(DatomicSpiSync.queryCursor_inspect(q))
  }


  // Save --------------------------------------------------------

  override def save_transact(save: Save)(implicit conn: Conn, ec: EC): Future[TxReport] = try {
    val errors = save_validate(save)
    if (errors.isEmpty) {
      conn.asInstanceOf[DatomicConn_JVM].transact_async(save_getStmts(save))
    } else {
      Future.failed(ValidationErrors(errors))
    }
  } catch {
    case e: Throwable => Future.failed(e)
  }

  override def save_inspect(save: Save)(implicit conn: Conn, ec: EC): Future[Unit] = {
    printInspectTx("SAVE", save.elements, save_getStmts(save))
  }

  private def save_getStmts(save: Save): Data = {
    (new ResolveSave() with Data_Save).getStmts(save.elements)
  }

  override def save_validate(save: Save)(implicit conn: Conn): Map[String, Seq[String]] = {
    val proxy = conn.proxy
    ModelValidation(proxy.nsMap, proxy.attrMap, "save").validate(save.elements)
  }


  // Insert --------------------------------------------------------

  override def insert_transact(insert: Insert)(implicit conn: Conn, ec: EC): Future[TxReport] = try {
    val errors = insert_validate(insert)
    if (errors.isEmpty) {
      conn.asInstanceOf[DatomicConn_JVM].transact_async(insert_getStmts(insert, conn.proxy))
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
    (new ResolveInsert with Data_Insert)
      .getStmts(proxy.nsMap, insert.elements, insert.tpls)
  }

  override def insert_validate(insert: Insert)(implicit conn: Conn): Seq[(Int, Seq[InsertError])] = {
    InsertValidation.validate(conn, insert.elements, insert.tpls)
  }


  // Update --------------------------------------------------------

  override def update_transact(update: Update)(implicit conn0: Conn, ec: EC): Future[TxReport] = try {
    val errors = update_validate(update)
    if (errors.isEmpty) {
      val conn = conn0.asInstanceOf[DatomicConn_JVM]
      conn.transact_async(update_getStmts(update, conn))
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
    (new ResolveUpdate(conn.proxy.uniqueAttrs, update.isUpsert) with Data_Update)
      .getStmts(conn, update.elements)
  }

  override def update_validate(update: Update)(implicit conn: Conn): Map[String, Seq[String]] = {
    validateUpdate(conn, update.elements)
  }


  // Delete --------------------------------------------------------

  override def delete_transact(delete: Delete)(implicit conn0: Conn, ec: EC): Future[TxReport] = try {
    val conn = conn0.asInstanceOf[DatomicConn_JVM]
    conn.transact_async(delete_getStmts(delete, conn))
  } catch {
    case e: Throwable => Future.failed(e)
  }

  override def delete_inspect(delete: Delete)(implicit conn: Conn, ec: EC): Future[Unit] = {
    printInspectTx("DELETE", delete.elements, delete_getStmts(delete, conn.asInstanceOf[DatomicConn_JVM]))
  }

  private def delete_getStmts(delete: Delete, conn: DatomicConn_JVM): Data = {
    (new ResolveDelete with Data_Delete).getStmtsData(conn, delete.elements)
  }


  // Inspect --------------------------------------------------------

  private def printInspectTx(label: String, elements: List[Element], stmts: Data)
                            (implicit ec: EC): Future[Unit] = {
    Future(printInspect(label, elements, stmts.toArray().toList.mkString("\n")))
  }
}
