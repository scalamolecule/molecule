package molecule.coreTests.api

import molecule.base.error._
import molecule.core.action._
import molecule.core.spi.{Conn, SpiZio, TxReport}
import zio._

trait ApiZioImplicits { dataProvider: SpiZio =>

  implicit class QueryApiAsync[Tpl](q: Query[Tpl]) {
    def get: ZIO[Conn, MoleculeError, List[Tpl]] = query_get(q)
    def subscribe(callback: List[Tpl] => Unit): ZIO[Conn, MoleculeError, Unit] = query_subscribe(q, callback)
    def unsubscribe(): ZIO[Conn, MoleculeError, Unit] = query_unsubscribe(q)
    def inspect: ZIO[Conn, MoleculeError, Unit] = query_inspect(q)
  }

  implicit class QueryOffsetApiAsync[Tpl](q: QueryOffset[Tpl]) {
    def get: ZIO[Conn, MoleculeError, (List[Tpl], Int, Boolean)] = queryOffset_get(q)
    def inspect: ZIO[Conn, MoleculeError, Unit] = queryOffset_inspect(q)
  }

  implicit class QueryCursorApiAsync[Tpl](q: QueryCursor[Tpl]) {
    def get: ZIO[Conn, MoleculeError, (List[Tpl], String, Boolean)] = queryCursor_get(q)
    def inspect: ZIO[Conn, MoleculeError, Unit] = queryCursor_inspect(q)
  }

  implicit class SaveApiAsync[Tpl](save: Save) {
    def transact: ZIO[Conn, MoleculeError, TxReport] = save_transact(save)
    def validate:ZIO[Conn, MoleculeError, Map[String, Seq[String]]] = save_validate(save)
    def inspect: ZIO[Conn, MoleculeError, Unit] = save_inspect(save)
  }

  implicit class InsertApiAsync[Tpl](insert: Insert) {
    def transact: ZIO[Conn, MoleculeError, TxReport] = insert_transact(insert)
    def validate: ZIO[Conn, MoleculeError, Seq[(Int, Seq[InsertError])]] = insert_validate(insert)
    def inspect: ZIO[Conn, MoleculeError, Unit] = insert_inspect(insert)
  }

  implicit class UpdateApiAsync[Tpl](update: Update) {
    def transact: ZIO[Conn, MoleculeError, TxReport] = update_transact(update)
    def validate: ZIO[Conn, MoleculeError, Map[String, Seq[String]]] = update_validate(update)
    def inspect: ZIO[Conn, MoleculeError, Unit] = update_inspect(update)
  }

  implicit class DeleteApiAsync[Tpl](delete: Delete) {
    def transact: ZIO[Conn, MoleculeError, TxReport] = delete_transact(delete)
    def inspect: ZIO[Conn, MoleculeError, Unit] = delete_inspect(delete)
  }

  def rawQuery(
    query: String,
    withNulls: Boolean = false,
    doPrint: Boolean = true,
  ): ZIO[Conn, MoleculeError, List[List[Any]]] = fallback_rawQuery(query, withNulls, doPrint)

  def rawTransact(
    txData: String,
    doPrint: Boolean = true
  ): ZIO[Conn, MoleculeError, TxReport] = fallback_rawTransact(txData, doPrint)
}
