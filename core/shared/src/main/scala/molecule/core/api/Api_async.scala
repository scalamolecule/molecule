package molecule.core.api

import cats.effect.IO
import molecule.base.error.InsertError
import molecule.core.action._
import molecule.core.spi._
import molecule.core.util.ModelUtils
import scala.concurrent.{Future, ExecutionContext => EC}

trait Api_async extends Keywords with ModelUtils { spi: Spi_async =>

  implicit class QueryApiAsync[Tpl](q: Query[Tpl]) {
    def get(implicit conn: Conn, ec: EC): Future[List[Tpl]] = query_get(q)
    def stream(implicit conn: Conn, ec: EC): fs2.Stream[IO, Tpl] = query_stream(q, 100)
    def stream(chunkSize: Int)(implicit conn: Conn, ec: EC): fs2.Stream[IO, Tpl] = query_stream(q, chunkSize)
    def subscribe(callback: List[Tpl] => Unit)
                 (implicit conn: Conn, ec: EC): Future[Unit] = query_subscribe(q, callback)
    def unsubscribe()(implicit conn: Conn, ec: EC): Future[Unit] = query_unsubscribe(q)
    def inspect(implicit conn: Conn, ec: EC): Future[Unit] = query_inspect(q)
  }

  implicit class QueryOffsetApiAsync[Tpl](q: QueryOffset[Tpl]) {
    def get(implicit conn: Conn, ec: EC): Future[(List[Tpl], Int, Boolean)] = queryOffset_get(q)
    def inspect(implicit conn: Conn, ec: EC): Future[Unit] = queryOffset_inspect(q)
  }

  implicit class QueryCursorApiAsync[Tpl](q: QueryCursor[Tpl]) {
    def get(implicit conn: Conn, ec: EC): Future[(List[Tpl], String, Boolean)] = queryCursor_get(q)
    def inspect(implicit conn: Conn, ec: EC): Future[Unit] = queryCursor_inspect(q)
  }

  implicit class SaveApiAsync(save: Save) {
    def transact(implicit conn: Conn, ec: EC): Future[TxReport] = save_transact(save)
    def inspect(implicit conn: Conn, ec: EC): Future[Unit] = save_inspect(save)
    def validate(implicit conn: Conn, ec: EC): Future[Map[String, Seq[String]]] = save_validate(save)
  }

  implicit class InsertApiAsync(insert: Insert) {
    def transact(implicit conn: Conn, ec: EC): Future[TxReport] = insert_transact(insert)
    def inspect(implicit conn: Conn, ec: EC): Future[Unit] = insert_inspect(insert)
    def validate(implicit conn: Conn, ec: EC): Future[Seq[(Int, Seq[InsertError])]] = insert_validate(insert)
  }

  implicit class UpdateApiAsync(update: Update) {
    def transact(implicit conn0: Conn, ec: EC): Future[TxReport] = update_transact(update)
    def inspect(implicit conn0: Conn, ec: EC): Future[Unit] = update_inspect(update)
    def validate(implicit conn: Conn, ec: EC): Future[Map[String, Seq[String]]] = update_validate(update)
  }

  implicit class DeleteApiAsync(delete: Delete) {
    def transact(implicit conn0: Conn, ec: EC): Future[TxReport] = delete_transact(delete)
    def inspect(implicit conn0: Conn, ec: EC): Future[Unit] = delete_inspect(delete)
  }

  def rawQuery(
    query: String,
    doPrint: Boolean = false,
  )(implicit conn: Conn, ec: EC): Future[List[List[Any]]] = fallback_rawQuery(query, doPrint)

  def rawTransact(
    txData: String,
    doPrint: Boolean = false
  )(implicit conn: Conn, ec: EC): Future[TxReport] = fallback_rawTransact(txData, doPrint)
}


trait Api_async_transact { api: Api_async with Spi_async =>

  def transact(a1: Action, a2: Action, aa: Action*)
              (implicit conn: Conn, ec: EC): Future[Seq[TxReport]] = transact(a1 +: a2 +: aa)

  def transact(actions: Seq[Action])
              (implicit conn: Conn, ec: EC): Future[Seq[TxReport]] = {
    var ok = true
    conn.waitCommitting()
    val txReports = Future.sequence(
      actions.flatMap {
        case action if ok => Some(
          (action match {
            case save: Save     => save_transact(save)
            case insert: Insert => insert_transact(insert)
            case update: Update => update_transact(update)
            case delete: Delete => delete_transact(delete)
          }).recover { case e =>
            // Rollback all executed actions so far
            conn.rollback()
            ok = false
            throw e
          }
        )

        case _ => None // Don't execute remaining actions
      }
    )

    // Commit if all executions succeeded
    if (ok) conn.commit()

    // Return transaction reports of all actions (can be failed Future)
    txReports
  }


  def unitOfWork[T](runUOW: => Future[T])
                   (implicit conn: Conn, ec: EC): Future[T] = {
    conn.waitCommitting()
    runUOW
      .map { t =>
        // Commit all actions
        conn.commit()
        t
      }
      .recover { case e =>
        // Rollback all executed actions so far
        conn.rollback()
        throw e
      }
  }


  def savepoint[T](runSavepoint: Savepoint => Future[T])
                  (implicit conn: Conn, ec: EC): Future[T] = {
    conn.savepoint_async(runSavepoint)
  }
}
