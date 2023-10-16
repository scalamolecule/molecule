package molecule.core.api

import molecule.base.error.InsertError
import molecule.core.action._
import molecule.core.spi.{Conn, SpiSync, TxReport}

trait ApiSync { spi: SpiSync =>

  implicit class QueryApiAsync[Tpl](q: Query[Tpl]) {
    def get(implicit conn: Conn): List[Tpl] = query_get(q)
    def subscribe(callback: List[Tpl] => Unit)(implicit conn: Conn): Unit = query_subscribe(q, callback)
    def unsubscribe()(implicit conn: Conn): Unit = query_unsubscribe(q)
    def inspect(implicit conn: Conn): Unit = query_inspect(q)
  }

  implicit class QueryOffsetApiAsync[Tpl](q: QueryOffset[Tpl]) {
    def get(implicit conn: Conn): (List[Tpl], Int, Boolean) = queryOffset_get(q)
    def inspect(implicit conn: Conn): Unit = queryOffset_inspect(q)
  }

  implicit class QueryCursorApiAsync[Tpl](q: QueryCursor[Tpl]) {
    def get(implicit conn: Conn): (List[Tpl], String, Boolean) = queryCursor_get(q)
    def inspect(implicit conn: Conn): Unit = queryCursor_inspect(q)
  }

  implicit class SaveApiAsync[Tpl](save: Save) {
    def transact(implicit conn: Conn): TxReport = save_transact(save)
    def inspect(implicit conn: Conn): Unit = save_inspect(save)
    def validate(implicit conn: Conn): Map[String, Seq[String]] = save_validate(save)
  }

  implicit class InsertApiAsync[Tpl](insert: Insert) {
    def transact(implicit conn: Conn): TxReport = insert_transact(insert)
    def inspect(implicit conn: Conn): Unit = insert_inspect(insert)
    def validate(implicit conn: Conn): Seq[(Int, Seq[InsertError])] = insert_validate(insert)
  }

  implicit class UpdateApiAsync[Tpl](update: Update) {
    def transact(implicit conn0: Conn): TxReport = update_transact(update)
    def inspect(implicit conn0: Conn): Unit = update_inspect(update)
    def validate(implicit conn: Conn): Map[String, Seq[String]] = update_validate(update)
  }

  implicit class DeleteApiAsync[Tpl](delete: Delete) {
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