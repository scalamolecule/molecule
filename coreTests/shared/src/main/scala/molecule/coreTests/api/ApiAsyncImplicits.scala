package molecule.coreTests.api

import molecule.base.error._
import molecule.core.action._
import molecule.core.spi.{Conn, SpiAsync, TxReport}
import scala.concurrent.{Future, ExecutionContext => EC}

trait ApiAsyncImplicits { dataProvider: SpiAsync =>

  implicit class QueryApiAsync[Tpl](q: Query[Tpl]) {
    def get(implicit conn: Conn, ec: EC): Future[List[Tpl]] = query_get(q)
    def subscribe(callback: List[Tpl] => Unit)
                 (implicit conn: Conn, ec: EC): Future[Unit] = query_subscribe(q, callback)
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

  implicit class SaveApiAsync[Tpl](save: Save) {
    def transact(implicit conn: Conn, ec: EC): Future[TxReport] = save_transact(save)
    def inspect(implicit conn: Conn, ec: EC): Future[Unit] = save_inspect(save)
    def validate(implicit conn: Conn): Map[String, Seq[String]] = save_validate(save)
  }

  implicit class InsertApiAsync[Tpl](insert: Insert) {
    def transact(implicit conn: Conn, ec: EC): Future[TxReport] = insert_transact(insert)
    def inspect(implicit conn: Conn, ec: EC): Future[Unit] = insert_inspect(insert)
    def validate(implicit conn: Conn): Seq[(Int, Seq[InsertError])] = insert_validate(insert)
  }

  implicit class UpdateApiAsync[Tpl](update: Update) {
    def transact(implicit conn0: Conn, ec: EC): Future[TxReport] = update_transact(update)
    def inspect(implicit conn0: Conn, ec: EC): Future[Unit] = update_inspect(update)
    def validate(implicit conn: Conn): Map[String, Seq[String]] = update_validate(update)
  }


  implicit class DeleteApiAsync[Tpl](delete: Delete) {
    def transact(implicit conn0: Conn, ec: EC): Future[TxReport] = delete_transact(delete)
    def inspect(implicit conn0: Conn, ec: EC): Future[Unit] = delete_inspect(delete)
  }
}