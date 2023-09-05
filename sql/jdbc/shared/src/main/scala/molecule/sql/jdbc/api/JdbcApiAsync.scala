package molecule.sql.jdbc.api

import molecule.base.error._
import molecule.core.action._
import molecule.core.api.ApiAsync
import molecule.core.spi.{Conn, TxReport}
import molecule.sql.jdbc.spi.JdbcSpiAsync
import scala.concurrent.{Future, ExecutionContext => EC}


trait JdbcApiAsync extends ApiAsync with JdbcSpiAsync {

  implicit class jdbcQueryApiAsync[Tpl](q: Query[Tpl]) extends QueryApiAsync[Tpl] {
    override def get(implicit conn: Conn, ec: EC): Future[List[Tpl]] = query_get(q)
    override def subscribe(callback: List[Tpl] => Unit)
                          (implicit conn: Conn, ec: EC): Future[Unit] = query_subscribe(q, callback)
    override def unsubscribe()(implicit conn: Conn, ec: EC): Future[Unit] = query_unsubscribe(q)
    override def inspect(implicit conn: Conn, ec: EC): Future[Unit] = query_inspect(q)
  }

  implicit class jdbcQueryOffsetApiAsync[Tpl](q: QueryOffset[Tpl]) extends QueryOffsetApiAsync[Tpl] {
    override def get(implicit conn: Conn, ec: EC): Future[(List[Tpl], Int, Boolean)] = queryOffset_get(q)
    override def inspect(implicit conn: Conn, ec: EC): Future[Unit] = queryOffset_inspect(q)
  }

  implicit class jdbcQueryCursorApiAsync[Tpl](q: QueryCursor[Tpl]) extends QueryCursorApiAsync[Tpl] {
    override def get(implicit conn: Conn, ec: EC): Future[(List[Tpl], String, Boolean)] = queryCursor_get(q)
    override def inspect(implicit conn: Conn, ec: EC): Future[Unit] = queryCursor_inspect(q)
  }

  implicit class jdbcSaveApiAsync[Tpl](save: Save) extends SaveApiAsync {
    override def transact(implicit conn: Conn, ec: EC): Future[TxReport] = save_transact(save)
    override def inspect(implicit conn: Conn, ec: EC): Future[Unit] = save_inspect(save)
    override def validate(implicit conn: Conn): Map[String, Seq[String]] = save_validate(save)
  }

  implicit class jdbcInsertApiAsync[Tpl](insert: Insert) extends InsertApiAsync {
    override def transact(implicit conn: Conn, ec: EC): Future[TxReport] = insert_transact(insert)
    override def inspect(implicit conn: Conn, ec: EC): Future[Unit] = insert_inspect(insert)
    override def validate(implicit conn: Conn): Seq[(Int, Seq[InsertError])] = insert_validate(insert)
  }

  implicit class jdbcUpdateApiAsync[Tpl](update: Update) extends UpdateApiAsync {
    override def transact(implicit conn: Conn, ec: EC): Future[TxReport] = update_transact(update)
    override def inspect(implicit conn: Conn, ec: EC): Future[Unit] = update_inspect(update)
    override def validate(implicit conn: Conn): Map[String, Seq[String]] = update_validate(update)
  }

  implicit class jdbcDeleteApiAsync[Tpl](delete: Delete) extends DeleteApiAsync {
    override def transact(implicit conn: Conn, ec: EC): Future[TxReport] = delete_transact(delete)
    override def inspect(implicit conn: Conn, ec: EC): Future[Unit] = delete_inspect(delete)
  }

  override def rawQuery(
    query: String,
    withNulls: Boolean = false,
    doPrint: Boolean = true,
  )(implicit conn: Conn, ec: EC): Future[List[List[Any]]] = fallback_rawQuery(query, withNulls, doPrint)

  override def rawTransact(
    txData: String,
    doPrint: Boolean = true
  )(implicit conn: Conn, ec: EC): Future[TxReport] = fallback_rawTransact(txData, doPrint)
}
