package molecule.core.api

import cats.effect._
import cats.implicits.toTraverseOps
import molecule.base.error.InsertError
import molecule.core.action._
import molecule.core.spi._
import molecule.core.util.ModelUtils

trait Api_io extends ModelUtils { spi: Spi_io =>

  implicit class QueryApiIO[Tpl](q: Query[Tpl]) {
    def get(implicit conn: Conn): IO[List[Tpl]] = query_get(q)
    def subscribe(callback: List[Tpl] => Unit)
                 (implicit conn: Conn): IO[Unit] = query_subscribe(q, callback)
    def unsubscribe()(implicit conn: Conn): IO[Unit] = query_unsubscribe(q)
    def inspect(implicit conn: Conn): IO[Unit] = query_inspect(q)
  }

  implicit class QueryOffsetApiIO[Tpl](q: QueryOffset[Tpl]) {
    def get(implicit conn: Conn): IO[(List[Tpl], Int, Boolean)] = queryOffset_get(q)
    def inspect(implicit conn: Conn): IO[Unit] = queryOffset_inspect(q)
  }

  implicit class QueryCursorApiIO[Tpl](q: QueryCursor[Tpl]) {
    def get(implicit conn: Conn): IO[(List[Tpl], String, Boolean)] = queryCursor_get(q)
    def inspect(implicit conn: Conn): IO[Unit] = queryCursor_inspect(q)
  }

  implicit class SaveApiIO(save: Save) {
    def transact(implicit conn: Conn): IO[TxReport] = save_transact(save)
    def inspect(implicit conn: Conn): IO[Unit] = save_inspect(save)
    def validate(implicit conn: Conn): IO[Map[String, Seq[String]]] = save_validate(save)
  }

  implicit class InsertApiIO(insert: Insert) {
    def transact(implicit conn: Conn): IO[TxReport] = insert_transact(insert)
    def inspect(implicit conn: Conn): IO[Unit] = insert_inspect(insert)
    def validate(implicit conn: Conn): IO[Seq[(Int, Seq[InsertError])]] = insert_validate(insert)
  }

  implicit class UpdateApiIO(update: Update) {
    def transact(implicit conn0: Conn): IO[TxReport] = update_transact(update)
    def inspect(implicit conn0: Conn): IO[Unit] = update_inspect(update)
    def validate(implicit conn: Conn): IO[Map[String, Seq[String]]] = update_validate(update)
  }

  implicit class DeleteApiIO(delete: Delete) {
    def transact(implicit conn0: Conn): IO[TxReport] = delete_transact(delete)
    def inspect(implicit conn0: Conn): IO[Unit] = delete_inspect(delete)
  }

  def rawQuery(
    query: String,
    doPrint: Boolean = false,
  )(implicit conn: Conn): IO[List[List[Any]]] = fallback_rawQuery(query, doPrint)

  def rawTransact(
    txData: String,
    doPrint: Boolean = false
  )(implicit conn: Conn): IO[TxReport] = fallback_rawTransact(txData, doPrint)


  def transact(
    a1: Action, a2: Action, aa: Action*
  )(implicit conn: Conn): IO[Seq[TxReport]] = transact(a1 +: a2 +: aa)


  def transact(actions: Seq[Action])(implicit conn: Conn): IO[Seq[TxReport]] = {
    actions.map {
      case save: Save     => save_transact(save)
      case insert: Insert => insert_transact(insert)
      case update: Update => update_transact(update)
      case delete: Delete => delete_transact(delete)
    }.sequence
  }

  def unitOfWork[T](body: => IO[T])(implicit conn: Conn): IO[T] = {
    conn.waitCommitting()
    body.attempt.map {
      case Right(t: T)            =>
        // Commit all actions
        conn.commit()
        t
      case Left(error: Throwable) =>
        // Rollback all executed actions so far
        conn.rollback()
        throw error
    }
  }

  def savepoint[T](body: Savepoint => IO[T])(implicit conn: Conn): IO[T] = {
    conn.savepoint_io(body)
  }
}