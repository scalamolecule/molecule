package molecule.core.spi

import cats.effect.IO
import molecule.base.error.InsertError
import molecule.core.action._

/**
 * Choosing pragmatic IO[T] as Doobie does:
 *
 * @see https://typelevel.org/doobie/docs/09-Error-Handling.html
 *
 *      If IO[Either[MoleculeError, T]] was preferred:
 * @see https://guillaumebogard.dev/posts/functional-error-handling/
 */
trait SpiIO {

  def query_get[Tpl](
    q: Query[Tpl]
  )(implicit conn: Conn): IO[List[Tpl]]

  def query_subscribe[Tpl](
    q: Query[Tpl], callback: List[Tpl] => Unit
  )(implicit conn: Conn): IO[Unit]

  def query_unsubscribe[Tpl](
    q: Query[Tpl]
  )(implicit conn: Conn): IO[Unit]

  def query_inspect[Tpl](
    q: Query[Tpl]
  )(implicit conn: Conn): IO[Unit]


  def queryOffset_get[Tpl](
    q: QueryOffset[Tpl]
  )(implicit conn: Conn): IO[(List[Tpl], Int, Boolean)]

  def queryOffset_inspect[Tpl](
    q: QueryOffset[Tpl]
  )(implicit conn: Conn): IO[Unit]


  def queryCursor_get[Tpl](
    q: QueryCursor[Tpl]
  )(implicit conn: Conn): IO[(List[Tpl], String, Boolean)]

  def queryCursor_inspect[Tpl](
    q: QueryCursor[Tpl]
  )(implicit conn: Conn): IO[Unit]


  def save_transact(save: Save)(implicit conn: Conn): IO[TxReport]
  def save_inspect(save: Save)(implicit conn: Conn): IO[Unit]
  def save_validate(save: Save)(implicit conn: Conn): IO[Map[String, Seq[String]]]

  def insert_transact(insert: Insert)(implicit conn: Conn): IO[TxReport]
  def insert_inspect(insert: Insert)(implicit conn: Conn): IO[Unit]
  def insert_validate(insert: Insert)(implicit conn: Conn): IO[Seq[(Int, Seq[InsertError])]]

  def update_transact(update: Update)(implicit conn: Conn): IO[TxReport]
  def update_inspect(update: Update)(implicit conn: Conn): IO[Unit]
  def update_validate(update: Update)(implicit conn: Conn): IO[Map[String, Seq[String]]]

  def delete_transact(delete: Delete)(implicit conn: Conn): IO[TxReport]
  def delete_inspect(delete: Delete)(implicit conn: Conn): IO[Unit]


  private def noJS(method: String): Nothing =
    throw new Exception(s"Fallback method '$method' not available from JS platform")

  def fallback_rawTransact(
    txData: String,
    debug: Boolean = false
  )(implicit conn: Conn): IO[TxReport] = noJS("rawTransact")

  def fallback_rawQuery(
    query: String,
    debug: Boolean = false,
  )(implicit conn: Conn): IO[List[List[Any]]] = noJS("rawQuery")
}
