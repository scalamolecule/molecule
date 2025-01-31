package molecule.core.api

import molecule.base.error._
import molecule.core.action._
import molecule.core.spi.{Conn, Spi_zio, TxReport}
import zio.ZIO

trait Api_zio extends Keywords { spi: Spi_zio =>

  implicit class QueryApiZIO[Tpl](q: Query[Tpl]) {
    def get: ZIO[Conn, MoleculeError, List[Tpl]] = query_get(q)
    def subscribe(callback: List[Tpl] => Unit): ZIO[Conn, MoleculeError, Unit] = query_subscribe(q, callback)
    def unsubscribe(): ZIO[Conn, MoleculeError, Unit] = query_unsubscribe(q)
    def inspect: ZIO[Conn, MoleculeError, Unit] = query_inspect(q)
  }

  implicit class QueryOffsetApiZIO[Tpl](q: QueryOffset[Tpl]) {
    def get: ZIO[Conn, MoleculeError, (List[Tpl], Int, Boolean)] = queryOffset_get(q)
    def inspect: ZIO[Conn, MoleculeError, Unit] = queryOffset_inspect(q)
  }

  implicit class QueryCursorApiZIO[Tpl](q: QueryCursor[Tpl]) {
    def get: ZIO[Conn, MoleculeError, (List[Tpl], String, Boolean)] = queryCursor_get(q)
    def inspect: ZIO[Conn, MoleculeError, Unit] = queryCursor_inspect(q)
  }

  implicit class SaveApiZIO[Tpl](save: Save) {
    def transact: ZIO[Conn, MoleculeError, TxReport] = save_transact(save)
    def validate: ZIO[Conn, MoleculeError, Map[String, Seq[String]]] = save_validate(save)
    def inspect: ZIO[Conn, MoleculeError, Unit] = save_inspect(save)
  }

  implicit class InsertApiZIO[Tpl](insert: Insert) {
    def transact: ZIO[Conn, MoleculeError, TxReport] = insert_transact(insert)
    def validate: ZIO[Conn, MoleculeError, Seq[(Int, Seq[InsertError])]] = insert_validate(insert)
    def inspect: ZIO[Conn, MoleculeError, Unit] = insert_inspect(insert)
  }

  implicit class UpdateApiZIO[Tpl](update: Update) {
    def transact: ZIO[Conn, MoleculeError, TxReport] = update_transact(update)
    def validate: ZIO[Conn, MoleculeError, Map[String, Seq[String]]] = update_validate(update)
    def inspect: ZIO[Conn, MoleculeError, Unit] = update_inspect(update)
  }

  implicit class DeleteApiZIO[Tpl](delete: Delete) {
    def transact: ZIO[Conn, MoleculeError, TxReport] = delete_transact(delete)
    def inspect: ZIO[Conn, MoleculeError, Unit] = delete_inspect(delete)
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


trait Api_zio_transact { api: Api_zio with Spi_zio =>

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