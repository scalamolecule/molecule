package molecule.db.common.spi

import cats.effect.IO
import molecule.base.error.{ExecutionError, InsertError}
import molecule.db.common.action.*

/**
 * Choosing pragmatic IO[T] as Doobie does:
 *
 * @see https://typelevel.org/doobie/docs/09-Error-Handling.html
 *
 *      If IO[Either[MoleculeError, T]] was preferred:
 * @see https://guillaumebogard.dev/posts/functional-error-handling/
 */
trait Spi_io {

  def query_get[Tpl](
    q: Query[Tpl]
  )(using conn: Conn): IO[List[Tpl]]

  def query_inspect[Tpl](
    q: Query[Tpl]
  )(using conn: Conn): IO[String]


  def queryOffset_get[Tpl](
    q: QueryOffset[Tpl]
  )(using conn: Conn): IO[(List[Tpl], Int, Boolean)]

  def queryOffset_inspect[Tpl](
    q: QueryOffset[Tpl]
  )(using conn: Conn): IO[String]


  def queryCursor_get[Tpl](
    q: QueryCursor[Tpl]
  )(using conn: Conn): IO[(List[Tpl], String, Boolean)]


  def queryCursor_inspect[Tpl](
    q: QueryCursor[Tpl]
  )(using conn: Conn): IO[String]


  def query_stream[Tpl](
    q: Query[Tpl], chunkSize: Int = 100
  )(using conn: Conn): fs2.Stream[IO, Tpl] = {
    // (overridden on jvm side)
    fs2.Stream.eval {
      IO.raiseError(
        ExecutionError(
          "Streaming not implemented on JS platform. Maybe use subscribe instead?"
        )
      )
    }
  }


  def query_subscribe[Tpl](
    q: Query[Tpl], callback: List[Tpl] => Unit
  )(using conn: Conn): IO[Unit]

  def query_unsubscribe[Tpl](
    q: Query[Tpl]
  )(using conn: Conn): IO[Unit]


  def save_transact(save: Save)(using conn: Conn): IO[TxReport]
  def save_inspect(save: Save)(using conn: Conn): IO[String]
  def save_validate(save: Save)(using conn: Conn): IO[Map[String, Seq[String]]]

  def insert_transact(insert: Insert)(using conn: Conn): IO[TxReport]
  def insert_inspect(insert: Insert)(using conn: Conn): IO[String]
  def insert_validate(insert: Insert)(using conn: Conn): IO[Seq[(Int, Seq[InsertError])]]

  def update_transact(update: Update)(using conn: Conn): IO[TxReport]
  def update_inspect(update: Update)(using conn: Conn): IO[String]
  def update_validate(update: Update)(using conn: Conn): IO[Map[String, Seq[String]]]

  def delete_transact(delete: Delete)(using conn: Conn): IO[TxReport]
  def delete_inspect(delete: Delete)(using conn: Conn): IO[String]


  private def noJS(method: String): Nothing =
    throw new Exception(s"Fallback method '$method' not available from JS platform")

  def fallback_rawTransact(
    txData: String,
    debug: Boolean = false
  )(using conn: Conn): IO[TxReport] = noJS("rawTransact")

  def fallback_rawQuery(
    query: String,
    debug: Boolean = false,
  )(using conn: Conn): IO[List[List[Any]]] = noJS("rawQuery")
}
