package molecule.db.common.spi

import scala.concurrent.{Future, ExecutionContext as EC}
import cats.effect.IO
import molecule.core.error.{ExecutionError, InsertError}
import molecule.db.common.crud.*

trait Spi_async {

  private[common] def query_get[Tpl](
    q: Query[Tpl]
  )(using conn: Conn, ec: EC): Future[List[Tpl]]

  private[common] def query_inspect[Tpl](
    q: Query[Tpl]
  )(using conn: Conn, ec: EC): Future[String]


  private[common] def queryOffset_get[Tpl](
    q: QueryOffset[Tpl]
  )(using conn: Conn, ec: EC): Future[(List[Tpl], Int, Boolean)]

  private[common] def queryOffset_inspect[Tpl](
    q: QueryOffset[Tpl]
  )(using conn: Conn, ec: EC): Future[String]


  private[common] def queryCursor_get[Tpl](
    q: QueryCursor[Tpl]
  )(using conn: Conn, ec: EC): Future[(List[Tpl], String, Boolean)]

  private[common] def queryCursor_inspect[Tpl](
    q: QueryCursor[Tpl]
  )(using conn: Conn, ec: EC): Future[String]


  private[common] def query_stream[Tpl](
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

  private[common] def query_subscribe[Tpl](
    q: Query[Tpl], callback: List[Tpl] => Unit
  )(using conn: Conn, ec: EC): Future[Unit]

  private[common] def query_unsubscribe[Tpl](
    q: Query[Tpl]
  )(using conn: Conn, ec: EC): Future[Unit]


  private[common] def save_transact(save: Save)(using conn: Conn, ec: EC): Future[TxReport]
  private[common] def save_inspect(save: Save)(using conn: Conn, ec: EC): Future[String]
  private[common] def save_validate(save: Save)(using conn: Conn, ec: EC): Future[Map[String, Seq[String]]]

  private[common] def insert_transact(insert: Insert)(using conn: Conn, ec: EC): Future[TxReport]
  private[common] def insert_inspect(insert: Insert)(using conn: Conn, ec: EC): Future[String]
  private[common] def insert_validate(insert: Insert)(using conn: Conn, ec: EC): Future[Seq[(Int, Seq[InsertError])]]

  private[common] def update_transact(update: Update)(using conn: Conn, ec: EC): Future[TxReport]
  private[common] def update_inspect(update: Update)(using conn: Conn, ec: EC): Future[String]
  private[common] def update_validate(update: Update)(using conn: Conn, ec: EC): Future[Map[String, Seq[String]]]

  private[common] def delete_transact(delete: Delete)(using conn: Conn, ec: EC): Future[TxReport]
  private[common] def delete_inspect(delete: Delete)(using conn: Conn, ec: EC): Future[String]


  private def noJS(method: String): Nothing =
    throw new Exception(s"Fallback method '$method' not available from JS platform")

  private[common] def fallback_rawQuery(
    query: String,
    debug: Boolean = false,
  )(using conn: Conn, ec: EC): Future[List[List[Any]]] = noJS("rawQuery")

  private[common] def fallback_rawTransact(
    txData: String,
    debug: Boolean = false
  )(using conn: Conn, ec: EC): Future[TxReport] = noJS("rawTransact")
}
