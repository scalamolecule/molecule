package molecule.db.common.spi

import cats.effect.IO
import molecule.base.error.{ExecutionError, InsertError}
import molecule.db.common.action.*
import scala.concurrent.{Future, ExecutionContext as EC}

trait Spi_async {

  def query_get[Tpl](
    q: Query[Tpl]
  )(using conn: Conn, ec: EC): Future[List[Tpl]]

  def query_inspect[Tpl](
    q: Query[Tpl]
  )(using conn: Conn, ec: EC): Future[String]


  def queryOffset_get[Tpl](
    q: QueryOffset[Tpl]
  )(using conn: Conn, ec: EC): Future[(List[Tpl], Int, Boolean)]

  def queryOffset_inspect[Tpl](
    q: QueryOffset[Tpl]
  )(using conn: Conn, ec: EC): Future[String]


  def queryCursor_get[Tpl](
    q: QueryCursor[Tpl]
  )(using conn: Conn, ec: EC): Future[(List[Tpl], String, Boolean)]

  def queryCursor_inspect[Tpl](
    q: QueryCursor[Tpl]
  )(using conn: Conn, ec: EC): Future[String]


  def query_stream[Tpl](
    q: Query[Tpl], chunkSize: Int = 100
  )(using conn: Conn, ec: EC): fs2.Stream[IO, Tpl] = {
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
  )(using conn: Conn, ec: EC): Future[Unit]

  def query_unsubscribe[Tpl](
    q: Query[Tpl]
  )(using conn: Conn, ec: EC): Future[Unit]


  def save_transact(save: Save)(using conn: Conn, ec: EC): Future[TxReport]
  def save_inspect(save: Save)(using conn: Conn, ec: EC): Future[String]
  def save_validate(save: Save)(using conn: Conn, ec: EC): Future[Map[String, Seq[String]]]

  def insert_transact(insert: Insert)(using conn: Conn, ec: EC): Future[TxReport]
  def insert_inspect(insert: Insert)(using conn: Conn, ec: EC): Future[String]
  def insert_validate(insert: Insert)(using conn: Conn, ec: EC): Future[Seq[(Int, Seq[InsertError])]]

  def update_transact(update: Update)(using conn: Conn, ec: EC): Future[TxReport]
  def update_inspect(update: Update)(using conn: Conn, ec: EC): Future[String]
  def update_validate(update: Update)(using conn: Conn, ec: EC): Future[Map[String, Seq[String]]]

  def delete_transact(delete: Delete)(using conn: Conn, ec: EC): Future[TxReport]
  def delete_inspect(delete: Delete)(using conn: Conn, ec: EC): Future[String]


  private def noJS(method: String): Nothing =
    throw new Exception(s"Fallback method '$method' not available from JS platform")

  def fallback_rawQuery(
    query: String,
    debug: Boolean = false,
  )(using conn: Conn, ec: EC): Future[List[List[Any]]] = noJS("rawQuery")

  def fallback_rawTransact(
    txData: String,
    debug: Boolean = false
  )(using conn: Conn, ec: EC): Future[TxReport] = noJS("rawTransact")
}
