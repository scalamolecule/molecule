package molecule.db.common.api

import cats.effect.*
import molecule.core.dataModel.Keywords
import molecule.core.error.InsertError
import molecule.db.common.crud.*
import molecule.db.common.spi.*
import molecule.db.common.util.ModelUtils

trait Api_io extends Keywords with ModelUtils { spi: Spi_io =>

  extension [Tpl](q: Query[Tpl]) {
    def get(using conn: Conn): IO[List[Tpl]] = query_get(q)
    def inspect(using conn: Conn): IO[String] = query_inspect(q)

    def stream(using conn: Conn): fs2.Stream[IO, Tpl] = query_stream(q, 100)
    def stream(chunkSize: Int)(using conn: Conn): fs2.Stream[IO, Tpl] = query_stream(q, chunkSize)

    def subscribe(callback: List[Tpl] => Unit)
                 (using conn: Conn): IO[Unit] = query_subscribe(q, callback)
    def unsubscribe()(using conn: Conn): IO[Unit] = query_unsubscribe(q)
  }

  extension [Tpl](q: QueryOffset[Tpl]) {
    def get(using conn: Conn): IO[(List[Tpl], Int, Boolean)] = queryOffset_get(q)
    def inspect(using conn: Conn): IO[String] = queryOffset_inspect(q)
  }

  extension [Tpl](q: QueryCursor[Tpl]) {
    def get(using conn: Conn): IO[(List[Tpl], String, Boolean)] = queryCursor_get(q)
    def inspect(using conn: Conn): IO[String] = queryCursor_inspect(q)
  }

  extension (save: Save) {
    def transact(using conn: Conn): IO[TxReport] = save_transact(save)
    def inspect(using conn: Conn): IO[String] = save_inspect(save)
    def validate(using conn: Conn): IO[Map[String, Seq[String]]] = save_validate(save)
  }

  extension (insert: Insert) {
    def transact(using conn: Conn): IO[TxReport] = insert_transact(insert)
    def inspect(using conn: Conn): IO[String] = insert_inspect(insert)
    def validate(using conn: Conn): IO[Seq[(Int, Seq[InsertError])]] = insert_validate(insert)
  }

  extension (update: Update) {
    def transact(using conn0: Conn): IO[TxReport] = update_transact(update)
    def inspect(using conn0: Conn): IO[String] = update_inspect(update)
    def validate(using conn: Conn): IO[Map[String, Seq[String]]] = update_validate(update)
  }

  extension (delete: Delete) {
    def transact(using conn0: Conn): IO[TxReport] = delete_transact(delete)
    def inspect(using conn0: Conn): IO[String] = delete_inspect(delete)
  }

  def rawQuery(
    query: String,
    doPrint: Boolean = false,
  )(using conn: Conn): IO[List[List[Any]]] = fallback_rawQuery(query, doPrint)

  def rawTransact(
    txData: String,
    doPrint: Boolean = false
  )(using conn: Conn): IO[TxReport] = fallback_rawTransact(txData, doPrint)
}


trait Api_io_transact { api: Api_io & Spi_io =>

  def transact(
    a: Mutation, b: Mutation, cc: Mutation*
  )(using conn: Conn): IO[Seq[TxReport]] = transact(a +: b +: cc)

  def transact(mutations: Seq[Mutation])(using conn: Conn): IO[Seq[TxReport]] = {
    mutations.foldLeft(IO.pure(Seq.empty[TxReport])) { (acc, mutation) =>
      val next = mutation match {
        case save: Save     => save_transact(save)
        case insert: Insert => insert_transact(insert)
        case update: Update => update_transact(update)
        case delete: Delete => delete_transact(delete)
      }
      for {
        reports <- acc
        report <- next
      } yield reports :+ report
    }
  }


  def unitOfWork[T](runUOW: => IO[T])(using conn: Conn): IO[T] = {
    conn.waitCommitting()
    runUOW.attempt.map {
      case Right(t)               =>
        // Commit all actions
        conn.commit()
        t
      case Left(error: Throwable) =>
        // Rollback all executed actions so far
        conn.rollback()
        throw error
    }
  }

  def savepoint[T](runSavepoint: Savepoint => IO[T])(using conn: Conn): IO[T] = {
    conn.savepoint_io(runSavepoint)
  }
}