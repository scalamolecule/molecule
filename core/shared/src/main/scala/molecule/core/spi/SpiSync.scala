package molecule.core.spi

import molecule.base.error.InsertError
import molecule.core.action._

trait SpiSync  {

  def query_get[Tpl](
    q: Query[Tpl]
  )(implicit conn: Conn): List[Tpl] = ???

  def query_subscribe[Tpl](
    q: Query[Tpl], callback: List[Tpl] => Unit
  )(implicit conn: Conn): Unit = ???

  def query_unsubscribe[Tpl](
    q: Query[Tpl]
  )(implicit conn: Conn): Unit = ???

  def query_inspect[Tpl](
    q: Query[Tpl]
  )(implicit conn: Conn): Unit = ???


  def queryOffset_get[Tpl](
    q: QueryOffset[Tpl]
  )(implicit conn: Conn): (List[Tpl], Int, Boolean) = ???

  def queryOffset_inspect[Tpl](
    q: QueryOffset[Tpl]
  )(implicit conn: Conn): Unit = ???


  def queryCursor_get[Tpl](
    q: QueryCursor[Tpl]
  )(implicit conn: Conn): (List[Tpl], String, Boolean) = ???

  def queryCursor_inspect[Tpl](
    q: QueryCursor[Tpl]
  )(implicit conn: Conn): Unit = ???


  def save_transact(save: Save)(implicit conn: Conn): TxReport = ???
  def save_inspect(save: Save)(implicit conn: Conn): Unit = ???
  def save_validate(save: Save)(implicit conn: Conn): Map[String, Seq[String]] = ???

  def insert_transact(insert: Insert)(implicit conn: Conn): TxReport = ???
  def insert_inspect(insert: Insert)(implicit conn: Conn): Unit = ???
  def insert_validate(insert: Insert)(implicit conn: Conn): Seq[(Int, Seq[InsertError])] = ???

  def update_transact(update: Update)(implicit conn: Conn): TxReport = ???
  def update_inspect(update: Update)(implicit conn: Conn): Unit = ???
  def update_validate(update: Update)(implicit conn: Conn): Map[String, Seq[String]] = ???

  def delete_transact(delete: Delete)(implicit conn: Conn): TxReport = ???
  def delete_inspect(delete: Delete)(implicit conn: Conn): Unit = ???

  def fallback_rawQuery(
    query: String,
    withNulls: Boolean = false,
    doPrint: Boolean = true,
  )(implicit conn: Conn): List[List[Any]] = ???

  def fallback_rawTransact(
    txData: String,
    doPrint: Boolean = true
  )(implicit conn: Conn): TxReport = ???
}