package molecule.sql.jdbc.api

import molecule.base.error._
import molecule.core.action._
import molecule.core.api.ApiSync
import molecule.core.spi.{Conn, TxReport}
import molecule.sql.jdbc.spi.JdbcSpiSync


trait JdbcApiSync extends ApiSync with JdbcSpiSync {

  implicit class datomicQueryApiSync[Tpl](q: Query[Tpl]) extends QueryApiSync[Tpl] {
    override def get(implicit conn: Conn): List[Tpl] = query_get(q)
    override def subscribe(callback: List[Tpl] => Unit)
                          (implicit conn: Conn): Unit = query_subscribe(q, callback)
    override def inspect(implicit conn: Conn): Unit = query_inspect(q)
  }

  implicit class datomicQueryOffsetApiSync[Tpl](q: QueryOffset[Tpl]) extends QueryOffsetApiSync[Tpl] {
    override def get(implicit conn: Conn): (List[Tpl], Int, Boolean) = queryOffset_get(q)
    override def inspect(implicit conn: Conn): Unit = queryOffset_inspect(q)
  }

  implicit class datomicQueryCursorApiSync[Tpl](q: QueryCursor[Tpl]) extends QueryCursorApiSync[Tpl] {
    override def get(implicit conn: Conn): (List[Tpl], String, Boolean) = queryCursor_get(q)
    override def inspect(implicit conn: Conn): Unit = queryCursor_inspect(q)
  }

  implicit class datomicSaveApiSync[Tpl](save: Save) extends SaveApiSync {
    override def transact(implicit conn: Conn): TxReport = save_transact(save)
    override def inspect(implicit conn: Conn): Unit = save_inspect(save)
    override def validate(implicit conn: Conn): Map[String, Seq[String]] = save_validate(save)
  }

  implicit class datomicInsertApiSync[Tpl](insert: Insert) extends InsertApiSync {
    override def transact(implicit conn: Conn): TxReport = insert_transact(insert)
    override def inspect(implicit conn: Conn): Unit = insert_inspect(insert)
    override def validate(implicit conn: Conn): Seq[(Int, Seq[InsertError])] = insert_validate(insert)
  }

  implicit class datomicUpdateApiSync[Tpl](update: Update) extends UpdateApiSync {
    override def transact(implicit conn0: Conn): TxReport = update_transact(update)
    override def inspect(implicit conn0: Conn): Unit = update_inspect(update)
    override def validate(implicit conn: Conn): Map[String, Seq[String]] = update_validate(update)
  }

  implicit class datomicDeleteApiSync[Tpl](delete: Delete) extends DeleteApiSync {
    override def transact(implicit conn0: Conn): TxReport = delete_transact(delete)
    override def inspect(implicit conn0: Conn): Unit = delete_inspect(delete)
  }
}
