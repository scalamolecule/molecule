package molecule.db.core.api

import molecule.base.error.{InsertError, MoleculeError}
import molecule.core.dataModel.Keywords
import molecule.db.core.action.*
import molecule.db.core.spi.*
import zio.ZIO
import zio.stream.ZStream

trait Api_zio extends Keywords { spi: Spi_zio =>

  implicit class QueryApiZIO[Tpl](q: Query[Tpl]) {
    def get: ZIO[Conn, MoleculeError, List[Tpl]] = query_get(q)
    def inspect: ZIO[Conn, MoleculeError, String] = query_inspect(q)

    def stream: ZStream[Conn, MoleculeError, Tpl] = query_stream(q, 100)
    def stream(chunkSize: Int): ZStream[Conn, MoleculeError, Tpl] = query_stream(q, chunkSize)

    def subscribe(callback: List[Tpl] => Unit): ZIO[Conn, MoleculeError, Unit] = query_subscribe(q, callback)
    def unsubscribe(): ZIO[Conn, MoleculeError, Unit] = query_unsubscribe(q)
  }

  implicit class QueryOffsetApiZIO[Tpl](q: QueryOffset[Tpl]) {
    def get: ZIO[Conn, MoleculeError, (List[Tpl], Int, Boolean)] = queryOffset_get(q)
    def inspect: ZIO[Conn, MoleculeError, String] = queryOffset_inspect(q)
  }

  implicit class QueryCursorApiZIO[Tpl](q: QueryCursor[Tpl]) {
    def get: ZIO[Conn, MoleculeError, (List[Tpl], String, Boolean)] = queryCursor_get(q)
    def inspect: ZIO[Conn, MoleculeError, String] = queryCursor_inspect(q)
  }

  implicit class SaveApiZIO[Tpl](save: Save) {
    def transact: ZIO[Conn, MoleculeError, TxReport] = save_transact(save)
    def validate: ZIO[Conn, MoleculeError, Map[String, Seq[String]]] = save_validate(save)
    def inspect: ZIO[Conn, MoleculeError, String] = save_inspect(save)
  }

  implicit class InsertApiZIO[Tpl](insert: Insert) {
    def transact: ZIO[Conn, MoleculeError, TxReport] = insert_transact(insert)
    def validate: ZIO[Conn, MoleculeError, Seq[(Int, Seq[InsertError])]] = insert_validate(insert)
    def inspect: ZIO[Conn, MoleculeError, String] = insert_inspect(insert)
  }

  implicit class UpdateApiZIO[Tpl](update: Update) {
    def transact: ZIO[Conn, MoleculeError, TxReport] = update_transact(update)
    def validate: ZIO[Conn, MoleculeError, Map[String, Seq[String]]] = update_validate(update)
    def inspect: ZIO[Conn, MoleculeError, String] = update_inspect(update)
  }

  implicit class DeleteApiZIO[Tpl](delete: Delete) {
    def transact: ZIO[Conn, MoleculeError, TxReport] = delete_transact(delete)
    def inspect: ZIO[Conn, MoleculeError, String] = delete_inspect(delete)
  }

  def rawQuery(
    query: String,
    debug: Boolean = false,
  ): ZIO[Conn, MoleculeError, List[List[Any]]] = fallback_rawQuery(query, debug)

  def rawTransact(
    txData: String,
    debug: Boolean = false
  ): ZIO[Conn, MoleculeError, TxReport] = fallback_rawTransact(txData, debug)
}


trait Api_zio_transact { api: Api_zio & Spi_zio =>

  def transact(
    a1: Action, a2: Action, aa: Action*
  ): ZIO[Conn, MoleculeError, Seq[TxReport]] = transact(a1 +: a2 +: aa)


  def transact(actions: Seq[Action]): ZIO[Conn, MoleculeError, Seq[TxReport]] = {
    ZIO.collectAll(
      actions.map {
        case save: Save     => save_transact(save)
        case insert: Insert => insert_transact(insert)
        case update: Update => update_transact(update)
        case delete: Delete => delete_transact(delete)
      }
    )
  }

  def unitOfWork[T](runUOW: => ZIO[Conn, MoleculeError, T]): ZIO[Conn, MoleculeError, T] = {
    for {
      conn <- ZIO.service[Conn]
      _ = conn.waitCommitting()
      result <- runUOW
        .map { t =>
          // Commit all actions
          conn.commit()
          t
        }
        .mapError { error =>
          // Rollback all executed actions so far
          conn.rollback()
          error
        }
    } yield result
  }

  def savepoint[T](runSavepoint: Savepoint => ZIO[Conn, MoleculeError, T]): ZIO[Conn, MoleculeError, T] = {
    for {
      conn <- ZIO.service[Conn]
      result <- conn.savepoint_zio(runSavepoint)
    } yield result
  }
}