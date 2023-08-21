package molecule.sql.jdbc.api

import molecule.base.error._
import molecule.core.action._
import molecule.core.api.ApiZio
import molecule.core.spi.{Conn, TxReport}
import molecule.sql.jdbc.spi.JdbcSpiZio
import zio.ZIO


trait JdbcApiZio extends ApiZio with JdbcSpiZio {

  implicit class jdbcQueryApiZio[Tpl](q: Query[Tpl]) extends QueryApiZio[Tpl] {
    override def get: ZIO[Conn, MoleculeError, List[Tpl]] = query_get(q)
    override def subscribe(callback: List[Tpl] => Unit)
    : ZIO[Conn, MoleculeError, Unit] = query_subscribe(q, callback)
    override def inspect: ZIO[Conn, MoleculeError, Unit] = query_inspect(q)
  }

  implicit class jdbcQueryOffsetApiZio[Tpl](q: QueryOffset[Tpl]) extends QueryOffsetApiZio[Tpl] {
    override def get: ZIO[Conn, MoleculeError, (List[Tpl], Int, Boolean)] = queryOffset_get(q)
    override def inspect: ZIO[Conn, MoleculeError, Unit] = queryOffset_inspect(q)
  }

  implicit class jdbcQueryCursorApiZio[Tpl](q: QueryCursor[Tpl]) extends QueryCursorApiZio[Tpl] {
    override def get: ZIO[Conn, MoleculeError, (List[Tpl], String, Boolean)] = queryCursor_get(q)
    override def inspect: ZIO[Conn, MoleculeError, Unit] = queryCursor_inspect(q)
  }

  implicit class jdbcSaveApiZio[Tpl](save: Save) extends SaveApiZio {
    override def transact: ZIO[Conn, MoleculeError, TxReport] = save_transact(save)
    override def inspect: ZIO[Conn, MoleculeError, Unit] = save_inspect(save)
    override def validate: ZIO[Conn, MoleculeError, Map[String, Seq[String]]] = save_validate(save)
  }

  implicit class jdbcInsertApiZio[Tpl](insert: Insert) extends InsertApiZio {
    override def transact: ZIO[Conn, MoleculeError, TxReport] = insert_transact(insert)
    override def inspect: ZIO[Conn, MoleculeError, Unit] = insert_inspect(insert)
    override def validate: ZIO[Conn, MoleculeError, Seq[(Int, Seq[InsertError])]] = insert_validate(insert)
  }

  implicit class jdbcUpdateApiZio[Tpl](update: Update) extends UpdateApiZio {
    override def transact: ZIO[Conn, MoleculeError, TxReport] = update_transact(update)
    override def inspect: ZIO[Conn, MoleculeError, Unit] = update_inspect(update)
    override def validate: ZIO[Conn, MoleculeError, Map[String, Seq[String]]] = update_validate(update)
  }

  implicit class jdbcDeleteApiZio[Tpl](delete: Delete) extends DeleteApiZio {
    override def transact: ZIO[Conn, MoleculeError, TxReport] = delete_transact(delete)
    override def inspect: ZIO[Conn, MoleculeError, Unit] = delete_inspect(delete)
  }

  override def rawQuery(
    query: String,
    withNulls: Boolean = false,
    doPrint: Boolean = true,
  ): ZIO[Conn, MoleculeError, List[List[Any]]] = fallback_rawQuery(query, withNulls, doPrint)

  override def rawTransact(
    txData: String,
    doPrint: Boolean = true
  ): ZIO[Conn, MoleculeError, TxReport] = fallback_rawTransact(txData, doPrint)
}
