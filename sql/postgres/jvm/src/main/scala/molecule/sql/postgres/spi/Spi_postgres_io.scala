package molecule.sql.postgres.spi

import cats.effect.IO
import molecule.base.error.InsertError
import molecule.core.action._
import molecule.core.spi.{Conn, Spi_io, TxReport}
import molecule.core.util.ModelUtils

trait Spi_postgres_io extends Spi_io with ModelUtils {

  override def query_get[Tpl](q: Query[Tpl])
                             (implicit conn: Conn): IO[List[Tpl]] = IO {
    //    // Check that rawQuery can handle all SPI queries
    //    val q1  = q.copy(elements = noKeywords(q.elements, Some(conn.proxy)))
    //    val m2q = getModel2SqlQuery(q1.elements)
    //    val qu  = m2q.getSqlQuery(q1.elements, None, None)
    //    SpiSync_postgres.fallback_rawQuery(qu, true)

    Spi_postgres_sync.query_get(q)
  }
  override def query_subscribe[Tpl](q: Query[Tpl], callback: List[Tpl] => Unit)
                                   (implicit conn: Conn): IO[Unit] = IO {
    Spi_postgres_sync.query_subscribe(q, callback)
  }
  override def query_unsubscribe[Tpl](q: Query[Tpl])
                                     (implicit conn: Conn): IO[Unit] = IO {
    Spi_postgres_sync.query_unsubscribe(q)
  }
  override def query_inspect[Tpl](q: Query[Tpl])
                                 (implicit conn: Conn): IO[Unit] = IO {
    Spi_postgres_sync.query_inspect(q)
  }

  override def queryOffset_get[Tpl](q: QueryOffset[Tpl])
                                   (implicit conn: Conn): IO[(List[Tpl], Int, Boolean)] = IO {
    Spi_postgres_sync.queryOffset_get(q)
  }
  override def queryOffset_inspect[Tpl](q: QueryOffset[Tpl])
                                       (implicit conn: Conn): IO[Unit] = IO {
    Spi_postgres_sync.queryOffset_inspect(q)
  }

  override def queryCursor_get[Tpl](q: QueryCursor[Tpl])
                                   (implicit conn: Conn): IO[(List[Tpl], String, Boolean)] = IO {
    Spi_postgres_sync.queryCursor_get(q)
  }
  override def queryCursor_inspect[Tpl](q: QueryCursor[Tpl])
                                       (implicit conn: Conn): IO[Unit] = IO {
    Spi_postgres_sync.queryCursor_inspect(q)
  }


  override def save_transact(save: Save)(implicit conn: Conn): IO[TxReport] = IO {
    Spi_postgres_sync.save_transact(save)
  }
  override def save_inspect(save: Save)(implicit conn: Conn): IO[Unit] = IO {
    Spi_postgres_sync.save_inspect(save)
  }
  override def save_validate(save: Save)(implicit conn: Conn): IO[Map[String, Seq[String]]] = IO {
    Spi_postgres_sync.save_validate(save)
  }

  override def insert_transact(insert: Insert)(implicit conn: Conn): IO[TxReport] = IO {
    Spi_postgres_sync.insert_transact(insert)
  }
  override def insert_inspect(insert: Insert)(implicit conn: Conn): IO[Unit] = IO {
    Spi_postgres_sync.insert_inspect(insert)
  }
  override def insert_validate(insert: Insert)(implicit conn: Conn): IO[Seq[(Int, Seq[InsertError])]] = IO {
    Spi_postgres_sync.insert_validate(insert)
  }


  override def update_transact(update: Update)(implicit conn: Conn): IO[TxReport] = IO {
    Spi_postgres_sync.update_transact(update)
  }
  override def update_inspect(update: Update)(implicit conn: Conn): IO[Unit] = IO {
    Spi_postgres_sync.update_inspect(update)
  }
  override def update_validate(update: Update)(implicit conn: Conn): IO[Map[String, Seq[String]]] = IO {
    Spi_postgres_sync.update_validate(update)
  }


  override def delete_transact(delete: Delete)(implicit conn: Conn): IO[TxReport] = IO {
    Spi_postgres_sync.delete_transact(delete)
  }
  override def delete_inspect(delete: Delete)(implicit conn: Conn): IO[Unit] = IO {
    Spi_postgres_sync.delete_inspect(delete)
  }

  override def fallback_rawQuery(
    query: String,
    debug: Boolean = false,
  )(implicit conn: Conn): IO[List[List[Any]]] = IO {
    Spi_postgres_sync.fallback_rawQuery(query, debug)
  }

  override def fallback_rawTransact(
    txData: String,
    debug: Boolean = false
  )(implicit conn: Conn): IO[TxReport] = IO {
    Spi_postgres_sync.fallback_rawTransact(txData, debug)
  }
}
