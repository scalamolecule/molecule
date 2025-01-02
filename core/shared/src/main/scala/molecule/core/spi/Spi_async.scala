package molecule.core.spi

import molecule.base.error.InsertError
import molecule.core.action._
import scala.concurrent.{Future, ExecutionContext => EC}

trait Spi_async {

  def query_get[Tpl](
    q: Query[Tpl]
  )(implicit conn: Conn, ec: EC): Future[List[Tpl]]

  def query_subscribe[Tpl](
    q: Query[Tpl], callback: List[Tpl] => Unit
  )(implicit conn: Conn, ec: EC): Future[Unit]

  def query_unsubscribe[Tpl](
    q: Query[Tpl]
  )(implicit conn: Conn, ec: EC): Future[Unit]

  def query_inspect[Tpl](
    q: Query[Tpl]
  )(implicit conn: Conn, ec: EC): Future[Unit]


  def queryOffset_get[Tpl](
    q: QueryOffset[Tpl]
  )(implicit conn: Conn, ec: EC): Future[(List[Tpl], Int, Boolean)]

  def queryOffset_inspect[Tpl](
    q: QueryOffset[Tpl]
  )(implicit conn: Conn, ec: EC): Future[Unit]


  def queryCursor_get[Tpl](
    q: QueryCursor[Tpl]
  )(implicit conn: Conn, ec: EC): Future[(List[Tpl], String, Boolean)]

  def queryCursor_inspect[Tpl](
    q: QueryCursor[Tpl]
  )(implicit conn: Conn, ec: EC): Future[Unit]


  def save_transact(save: Save)(implicit conn: Conn, ec: EC): Future[TxReport]
  def save_inspect(save: Save)(implicit conn: Conn, ec: EC): Future[Unit]
  def save_validate(save: Save)(implicit conn: Conn, ec: EC): Future[Map[String, Seq[String]]]

  def insert_transact(insert: Insert)(implicit conn: Conn, ec: EC): Future[TxReport]
  def insert_inspect(insert: Insert)(implicit conn: Conn, ec: EC): Future[Unit]
  def insert_validate(insert: Insert)(implicit conn: Conn, ec: EC): Future[Seq[(Int, Seq[InsertError])]]

  def update_transact(update: Update)(implicit conn: Conn, ec: EC): Future[TxReport]
  def update_inspect(update: Update)(implicit conn: Conn, ec: EC): Future[Unit]
  def update_validate(update: Update)(implicit conn: Conn, ec: EC): Future[Map[String, Seq[String]]]

  def delete_transact(delete: Delete)(implicit conn: Conn, ec: EC): Future[TxReport]
  def delete_inspect(delete: Delete)(implicit conn: Conn, ec: EC): Future[Unit]


  private def noJS(method: String): Nothing =
    throw new Exception(s"Fallback method '$method' not available from JS platform")

  def fallback_rawQuery(
    query: String,
    debug: Boolean = false,
  )(implicit conn: Conn, ec: EC): Future[List[List[Any]]] = noJS("rawQuery")

  def fallback_rawTransact(
    txData: String,
    debug: Boolean = false
  )(implicit conn: Conn, ec: EC): Future[TxReport] = noJS("rawTransact")
}
