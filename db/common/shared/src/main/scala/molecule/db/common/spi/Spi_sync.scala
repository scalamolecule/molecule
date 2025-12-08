package molecule.db.common.spi

import geny.Generator
import molecule.core.error.InsertError
import molecule.db.common.crud.*

trait Spi_sync {

  private def noJS(method: String): Nothing = throw new Exception(
    s"Synchronous SPI on JS platform not implemented. " +
      s"Method '$method' unexpectedly called from JS platform"
  )

  private[common] def query_get[Tpl](
    q: Query[Tpl]
  )(using conn: Conn): List[Tpl] = noJS("query_get")

  private[common] def query_inspect[Tpl](
    q: Query[Tpl]
  )(using conn: Conn): String = noJS("query_inspect")


  private[common] def queryOffset_get[Tpl](
    q: QueryOffset[Tpl]
  )(using conn: Conn): (List[Tpl], Int, Boolean) = noJS("queryOffset_get")

  private[common] def queryOffset_inspect[Tpl](
    q: QueryOffset[Tpl]
  )(using conn: Conn): String = noJS("queryOffset_inspect")


  private[common] def queryCursor_get[Tpl](
    q: QueryCursor[Tpl]
  )(using conn: Conn): (List[Tpl], String, Boolean) = noJS("queryCursor_get")

  private[common] def queryCursor_inspect[Tpl](
    q: QueryCursor[Tpl]
  )(using conn: Conn): String = noJS("queryCursor_inspect")


  private[common] def query_stream[Tpl](
    q: Query[Tpl], chunkSize: Int = 100
  )(using conn: Conn): Generator[Tpl] = noJS("query_stream")


  private[common] def query_subscribe[Tpl](
    q: Query[Tpl], callback: List[Tpl] => Unit
  )(using conn: Conn): Unit = noJS("query_subscribe")

  private[common] def query_unsubscribe[Tpl](
    q: Query[Tpl]
  )(using conn: Conn): Unit = noJS("query_unsubscribe")


  private[common] def save_transact(save: Save)(using conn: Conn): TxReport = noJS("save_transact")
  private[common] def save_inspect(save: Save)(using conn: Conn): String = noJS("save_inspect")
  private[common] def save_validate(save: Save)(using conn: Conn): Map[String, Seq[String]] = noJS("save_validate")

  private[common] def insert_transact(insert: Insert)(using conn: Conn): TxReport = noJS("insert_transact")
  private[common] def insert_inspect(insert: Insert)(using conn: Conn): String = noJS("insert_inspect")
  private[common] def insert_validate(insert: Insert)(using conn: Conn): Seq[(Int, Seq[InsertError])] = noJS("insert_validate")

  private[common] def update_transact(update: Update)(using conn: Conn): TxReport = noJS("update_transact")
  private[common] def update_inspect(update: Update)(using conn: Conn): String = noJS("update_inspect")
  private[common] def update_validate(update: Update)(using conn: Conn): Map[String, Seq[String]] = noJS("update_validate")

  private[common] def delete_transact(delete: Delete)(using conn: Conn): TxReport = noJS("delete_transact")
  private[common] def delete_inspect(delete: Delete)(using conn: Conn): String = noJS("delete_inspect")

  private[common] def fallback_rawQuery(
    query: String,
    debug: Boolean = false,
  )(using conn: Conn): List[List[Any]] = noJS("fallback_rawQuery")

  private[common] def fallback_rawTransact(
    txData: String,
    debug: Boolean = false
  )(using conn: Conn): TxReport = noJS("fallback_rawTransact")
}