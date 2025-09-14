package molecule.db.common.api

import scala.util.control.NonFatal
import geny.Generator
import molecule.core.dataModel.Keywords
import molecule.core.error.InsertError
import molecule.db.common.crud.*
import molecule.db.common.spi.*

trait Api_sync extends Keywords { spi: Spi_sync =>

  extension [Tpl](q: Query[Tpl]) {
    def get(using conn: Conn): List[Tpl] = query_get(q)
    def inspect(using conn: Conn): String = query_inspect(q)

    def stream(using conn: Conn): Generator[Tpl] = query_stream(q)
    def stream(chunkSize: Int)(using conn: Conn): Generator[Tpl] = query_stream(q, chunkSize)

    def subscribe(callback: List[Tpl] => Unit)(using conn: Conn): Unit = query_subscribe(q, callback)
    def unsubscribe()(using conn: Conn): Unit = query_unsubscribe(q)
  }

  extension [Tpl](q: QueryOffset[Tpl]) {
    def get(using conn: Conn): (List[Tpl], Int, Boolean) = queryOffset_get(q)
    def inspect(using conn: Conn): String = queryOffset_inspect(q)
  }

  extension [Tpl](q: QueryCursor[Tpl]) {
    def get(using conn: Conn): (List[Tpl], String, Boolean) = queryCursor_get(q)
    def inspect(using conn: Conn): String = queryCursor_inspect(q)
  }

  extension [Tpl](save: Save) {
    def transact(using conn: Conn): TxReport = save_transact(save)
    def inspect(using conn: Conn): String = save_inspect(save)
    def validate(using conn: Conn): Map[String, Seq[String]] = save_validate(save)
  }

  extension [Tpl](insert: Insert) {
    def transact(using conn: Conn): TxReport = insert_transact(insert)
    def inspect(using conn: Conn): String = insert_inspect(insert)
    def validate(using conn: Conn): Seq[(Int, Seq[InsertError])] = insert_validate(insert)
  }

  extension [Tpl](update: Update) {
    def transact(using conn0: Conn): TxReport = update_transact(update)
    def inspect(using conn0: Conn): String = update_inspect(update)
    def validate(using conn: Conn): Map[String, Seq[String]] = update_validate(update)
  }

  extension [Tpl](delete: Delete) {
    def transact(using conn0: Conn): TxReport = delete_transact(delete)
    def inspect(using conn0: Conn): String = delete_inspect(delete)
  }


  def rawQuery(
    query: String,
    debug: Boolean = false,
  )(using conn: Conn): List[List[Any]] = fallback_rawQuery(query, debug)

  def rawTransact(
    txData: String,
    debug: Boolean = false
  )(using conn: Conn): TxReport = fallback_rawTransact(txData, debug)
}


trait Api_sync_transact { api: Api_sync & Spi_sync =>

  def transact(a: Mutation, b: Mutation, cc: Mutation*)
              (using conn: Conn): Seq[TxReport] = transact(a +: b +: cc)

  def transact(mutations: Seq[Mutation])(using conn: Conn): Seq[TxReport] = {
    conn.waitCommitting()
    try {
      val txReports = mutations.map {
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


  def unitOfWork[T](runUOW: => T)(using conn: Conn): T = {
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

  def savepoint[T](runSavepoint: Savepoint => T)(using conn: Conn): T = {
    conn.savepoint_sync(runSavepoint)
  }
}