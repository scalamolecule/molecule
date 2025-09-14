package molecule.db.common.spi

import geny.Generator
import molecule.core.error.InsertError
import molecule.db.common.crud.*

trait Spi_sync {

  private def noJS(method: String): Nothing = throw new Exception(
    s"Synchronous SPI on JS platform not implemented. " +
      s"Method '$method' unexpectedly called from JS platform"
  )

  def query_get[Tpl](
    q: Query[Tpl]
  )(using conn: Conn): List[Tpl] = noJS("query_get")

  def query_inspect[Tpl](
    q: Query[Tpl]
  )(using conn: Conn): String = noJS("query_inspect")


  def queryOffset_get[Tpl](
    q: QueryOffset[Tpl]
  )(using conn: Conn): (List[Tpl], Int, Boolean) = noJS("queryOffset_get")

  def queryOffset_inspect[Tpl](
    q: QueryOffset[Tpl]
  )(using conn: Conn): String = noJS("queryOffset_inspect")


  def queryCursor_get[Tpl](
    q: QueryCursor[Tpl]
  )(using conn: Conn): (List[Tpl], String, Boolean) = noJS("queryCursor_get")

  def queryCursor_inspect[Tpl](
    q: QueryCursor[Tpl]
  )(using conn: Conn): String = noJS("queryCursor_inspect")


  def query_stream[Tpl](
    q: Query[Tpl], chunkSize: Int = 100
  )(using conn: Conn): Generator[Tpl] = noJS("query_stream")


  def query_subscribe[Tpl](
    q: Query[Tpl], callback: List[Tpl] => Unit
  )(using conn: Conn): Unit = noJS("query_subscribe")

  def query_unsubscribe[Tpl](
    q: Query[Tpl]
  )(using conn: Conn): Unit = noJS("query_unsubscribe")


  def save_transact(save: Save)(using conn: Conn): TxReport = noJS("save_transact")
  def save_inspect(save: Save)(using conn: Conn): String = noJS("save_inspect")
  def save_validate(save: Save)(using conn: Conn): Map[String, Seq[String]] = noJS("save_validate")

  def insert_transact(insert: Insert)(using conn: Conn): TxReport = noJS("insert_transact")
  def insert_inspect(insert: Insert)(using conn: Conn): String = noJS("insert_inspect")
  def insert_validate(insert: Insert)(using conn: Conn): Seq[(Int, Seq[InsertError])] = noJS("insert_validate")

  def update_transact(update: Update)(using conn: Conn): TxReport = noJS("update_transact")
  def update_inspect(update: Update)(using conn: Conn): String = noJS("update_inspect")
  def update_validate(update: Update)(using conn: Conn): Map[String, Seq[String]] = noJS("update_validate")

  def delete_transact(delete: Delete)(using conn: Conn): TxReport = noJS("delete_transact")
  def delete_inspect(delete: Delete)(using conn: Conn): String = noJS("delete_inspect")

  def fallback_rawQuery(
    query: String,
    debug: Boolean = false,
  )(using conn: Conn): List[List[Any]] = noJS("fallback_rawQuery")

  def fallback_rawTransact(
    txData: String,
    debug: Boolean = false
  )(using conn: Conn): TxReport = noJS("fallback_rawTransact")
}