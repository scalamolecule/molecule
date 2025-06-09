package molecule.db.core.api

import geny.Generator
import molecule.base.error.InsertError
import molecule.core.ast.Keywords
import molecule.db.core.action.*
import molecule.db.core.spi.*
import scala.util.control.NonFatal

trait Api_sync extends Keywords { spi: Spi_sync =>

  implicit class QueryApiSync[Tpl](q: Query[Tpl]) {
    def get(implicit conn: Conn): List[Tpl] = query_get(q)
    def inspect(implicit conn: Conn): String = query_inspect(q)

    def stream(implicit conn: Conn): Generator[Tpl] = query_stream(q)
    def stream(chunkSize: Int)(implicit conn: Conn): Generator[Tpl] = query_stream(q, chunkSize)

    def subscribe(callback: List[Tpl] => Unit)(implicit conn: Conn): Unit = query_subscribe(q, callback)
    def unsubscribe()(implicit conn: Conn): Unit = query_unsubscribe(q)
  }

  implicit class QueryOffsetApiSync[Tpl](q: QueryOffset[Tpl]) {
    def get(implicit conn: Conn): (List[Tpl], Int, Boolean) = queryOffset_get(q)
    def inspect(implicit conn: Conn): String = queryOffset_inspect(q)
  }

  implicit class QueryCursorApiSync[Tpl](q: QueryCursor[Tpl]) {
    def get(implicit conn: Conn): (List[Tpl], String, Boolean) = queryCursor_get(q)
    def inspect(implicit conn: Conn): String = queryCursor_inspect(q)
  }

  implicit class SaveApiSync[Tpl](save: Save) {
    def transact(implicit conn: Conn): TxReport = save_transact(save)
    def inspect(implicit conn: Conn): String = save_inspect(save)
    def validate(implicit conn: Conn): Map[String, Seq[String]] = save_validate(save)
  }

  implicit class InsertApiSync[Tpl](insert: Insert) {
    def transact(implicit conn: Conn): TxReport = insert_transact(insert)
    def inspect(implicit conn: Conn): String = insert_inspect(insert)
    def validate(implicit conn: Conn): Seq[(Int, Seq[InsertError])] = insert_validate(insert)
  }

  implicit class UpdateApiSync[Tpl](update: Update) {
    def transact(implicit conn0: Conn): TxReport = update_transact(update)
    def inspect(implicit conn0: Conn): String = update_inspect(update)
    def validate(implicit conn: Conn): Map[String, Seq[String]] = update_validate(update)
  }

  implicit class DeleteApiSync[Tpl](delete: Delete) {
    def transact(implicit conn0: Conn): TxReport = delete_transact(delete)
    def inspect(implicit conn0: Conn): String = delete_inspect(delete)
  }


  def rawQuery(
    query: String,
    debug: Boolean = false,
  )(implicit conn: Conn): List[List[Any]] = fallback_rawQuery(query, debug)

  def rawTransact(
    txData: String,
    debug: Boolean = false
  )(implicit conn: Conn): TxReport = fallback_rawTransact(txData, debug)
}


trait Api_sync_transact { api: Api_sync & Spi_sync =>

  def transact(a1: Action, a2: Action, aa: Action*)
              (implicit conn: Conn): Seq[TxReport] = transact(a1 +: a2 +: aa)

  def transact(actions: Seq[Action])(implicit conn: Conn): Seq[TxReport] = {
    conn.waitCommitting()
    try {
      val txReports = actions.map {
        case save: Save     => save_transact(save)
        case insert: Insert => insert_transact(insert)
        case update: Update => update_transact(update)
        case delete: Delete => delete_transact(delete)
      }
      // Commit if all executions succeeded
      conn.commit()
      txReports
    } catch {
      case NonFatal(e) =>
        // Rollback all executed actions so far
        conn.rollback()
        throw e
    }
  }


  def unitOfWork[T](runUOW: => T)(implicit conn: Conn): T = {
    conn.waitCommitting()
    try {
      val result = runUOW
      // Commit all actions
      conn.commit()
      result
    } catch {
      case NonFatal(e) =>
        // Rollback all executed actions so far
        conn.rollback()
        throw e
    }
  }

  def savepoint[T](runSavepoint: Savepoint => T)(implicit conn: Conn): T = {
    conn.savepoint_sync(runSavepoint)
  }
}