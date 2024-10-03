package molecule.core.api

import molecule.base.error.InsertError
import molecule.core.action._
import molecule.core.spi.{Conn, Spi_sync, TxReport}

trait Api_sync { spi: Spi_sync =>

  implicit class QueryApiSync[Tpl](q: Query[Tpl]) {
    def get(implicit conn: Conn): List[Tpl] = query_get(q)
    def subscribe(callback: List[Tpl] => Unit)(implicit conn: Conn): Unit = query_subscribe(q, callback)
    def unsubscribe()(implicit conn: Conn): Unit = query_unsubscribe(q)
    def inspect(implicit conn: Conn): Unit = query_inspect(q)
  }

  implicit class QueryOffsetApiSync[Tpl](q: QueryOffset[Tpl]) {
    def get(implicit conn: Conn): (List[Tpl], Int, Boolean) = queryOffset_get(q)
    def inspect(implicit conn: Conn): Unit = queryOffset_inspect(q)
  }

  implicit class QueryCursorApiSync[Tpl](q: QueryCursor[Tpl]) {
    def get(implicit conn: Conn): (List[Tpl], String, Boolean) = queryCursor_get(q)
    def inspect(implicit conn: Conn): Unit = queryCursor_inspect(q)
  }

  implicit class SaveApiSync[Tpl](save: Save) {
    def transact(implicit conn: Conn): TxReport = save_transact(save)
    def inspect(implicit conn: Conn): Unit = save_inspect(save)
    def validate(implicit conn: Conn): Map[String, Seq[String]] = save_validate(save)
  }

  implicit class InsertApiSync[Tpl](insert: Insert) {
    def transact(implicit conn: Conn): TxReport = insert_transact(insert)
    def inspect(implicit conn: Conn): Unit = insert_inspect(insert)
    def validate(implicit conn: Conn): Seq[(Int, Seq[InsertError])] = insert_validate(insert)
  }

  implicit class UpdateApiSync[Tpl](update: Update) {
    def transact(implicit conn0: Conn): TxReport = update_transact(update)
    def inspect(implicit conn0: Conn): Unit = update_inspect(update)
    def validate(implicit conn: Conn): Map[String, Seq[String]] = update_validate(update)
  }

  implicit class DeleteApiSync[Tpl](delete: Delete) {
    def transact(implicit conn0: Conn): TxReport = delete_transact(delete)
    def inspect(implicit conn0: Conn): Unit = delete_inspect(delete)
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