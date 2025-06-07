package molecule.db.core.spi

import geny.Generator
import molecule.db.base.error.InsertError
import molecule.db.core.action.*

trait Spi_sync {

  private def noJS(method: String): Nothing = throw new Exception(
    s"Synchronous SPI on JS platform not implemented. " +
      s"Method '$method' unexpectedly called from JS platform"
  )

  def query_get[Tpl](
    q: Query[Tpl]
  )(implicit conn: Conn): List[Tpl] = noJS("query_get")

  def query_inspect[Tpl](
    q: Query[Tpl]
  )(implicit conn: Conn): String = noJS("query_inspect")


  def queryOffset_get[Tpl](
    q: QueryOffset[Tpl]
  )(implicit conn: Conn): (List[Tpl], Int, Boolean) = noJS("queryOffset_get")

  def queryOffset_inspect[Tpl](
    q: QueryOffset[Tpl]
  )(implicit conn: Conn): String = noJS("queryOffset_inspect")


  def queryCursor_get[Tpl](
    q: QueryCursor[Tpl]
  )(implicit conn: Conn): (List[Tpl], String, Boolean) = noJS("queryCursor_get")

  def queryCursor_inspect[Tpl](
    q: QueryCursor[Tpl]
  )(implicit conn: Conn): String = noJS("queryCursor_inspect")


  def query_stream[Tpl](
    q: Query[Tpl], chunkSize: Int = 100
  )(implicit conn: Conn): Generator[Tpl] = noJS("query_stream")


  def query_subscribe[Tpl](
    q: Query[Tpl], callback: List[Tpl] => Unit
  )(implicit conn: Conn): Unit = noJS("query_subscribe")

  def query_unsubscribe[Tpl](
    q: Query[Tpl]
  )(implicit conn: Conn): Unit = noJS("query_unsubscribe")


  def save_transact(save: Save)(implicit conn: Conn): TxReport = noJS("save_transact")
  def save_inspect(save: Save)(implicit conn: Conn): String = noJS("save_inspect")
  def save_validate(save: Save)(implicit conn: Conn): Map[String, Seq[String]] = noJS("save_validate")

  def insert_transact(insert: Insert)(implicit conn: Conn): TxReport = noJS("insert_transact")
  def insert_inspect(insert: Insert)(implicit conn: Conn): String = noJS("insert_inspect")
  def insert_validate(insert: Insert)(implicit conn: Conn): Seq[(Int, Seq[InsertError])] = noJS("insert_validate")

  def update_transact(update: Update)(implicit conn: Conn): TxReport = noJS("update_transact")
  def update_inspect(update: Update)(implicit conn: Conn): String = noJS("update_inspect")
  def update_validate(update: Update)(implicit conn: Conn): Map[String, Seq[String]] = noJS("update_validate")

  def delete_transact(delete: Delete)(implicit conn: Conn): TxReport = noJS("delete_transact")
  def delete_inspect(delete: Delete)(implicit conn: Conn): String = noJS("delete_inspect")

  def fallback_rawQuery(
    query: String,
    debug: Boolean = false,
  )(implicit conn: Conn): List[List[Any]] = noJS("fallback_rawQuery")

  def fallback_rawTransact(
    txData: String,
    debug: Boolean = false
  )(implicit conn: Conn): TxReport = noJS("fallback_rawTransact")
}