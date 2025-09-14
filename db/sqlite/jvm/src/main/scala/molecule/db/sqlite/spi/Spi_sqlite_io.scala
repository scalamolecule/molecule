package molecule.db.sqlite.spi

import cats.effect.IO
import molecule.core.error.InsertError
import molecule.db.common.crud.*
import molecule.db.common.spi.{Conn, Spi_io, StreamingJdbc, TxReport}
import molecule.db.common.util.ModelUtils

trait Spi_sqlite_io extends Spi_io with StreamingJdbc with ModelUtils {

  override def query_get[Tpl](q: Query[Tpl])
                             (using conn: Conn): IO[List[Tpl]] = IO.blocking {
    Spi_sqlite_sync.query_get(q)
  }

  override def query_inspect[Tpl](q: Query[Tpl])
                                 (using conn: Conn): IO[String] = IO.blocking {
    Spi_sqlite_sync.query_inspect(q)
  }

  override def queryOffset_get[Tpl](q: QueryOffset[Tpl])
                                   (using conn: Conn): IO[(List[Tpl], Int, Boolean)] = IO.blocking {
    Spi_sqlite_sync.queryOffset_get(q)
  }

  override def queryOffset_inspect[Tpl](q: QueryOffset[Tpl])
                                       (using conn: Conn): IO[String] = IO.blocking {
    Spi_sqlite_sync.queryOffset_inspect(q)
  }

  override def queryCursor_get[Tpl](q: QueryCursor[Tpl])
                                   (using conn: Conn): IO[(List[Tpl], String, Boolean)] = IO.blocking {
    Spi_sqlite_sync.queryCursor_get(q)
  }

  override def queryCursor_inspect[Tpl](q: QueryCursor[Tpl])
                                       (using conn: Conn): IO[String] = IO.blocking {
    Spi_sqlite_sync.queryCursor_inspect(q)
  }


  override def query_stream[Tpl](
    q: Query[Tpl],
    chunkSize: Int
  )(using conn: Conn): fs2.Stream[IO, Tpl] = fs2stream(
    q, chunkSize,
    (q: Query[Tpl], conn: Conn) => Spi_sqlite_sync.query_inspect[Tpl](q)(using conn),
    Spi_sqlite_sync.getResultSetAndRowResolver[Tpl]
  )


  override def query_subscribe[Tpl](q: Query[Tpl], callback: List[Tpl] => Unit)
                                   (using conn: Conn): IO[Unit] = IO.blocking {
    Spi_sqlite_sync.query_subscribe(q, callback)
  }

  override def query_unsubscribe[Tpl](q: Query[Tpl])
                                     (using conn: Conn): IO[Unit] = IO.blocking {
    Spi_sqlite_sync.query_unsubscribe(q)
  }


  override def save_transact(save: Save)(using conn: Conn): IO[TxReport] = IO.blocking {
    Spi_sqlite_sync.save_transact(save)
  }

  override def save_inspect(save: Save)(using conn: Conn): IO[String] = IO.blocking {
    Spi_sqlite_sync.save_inspect(save)
  }

  override def save_validate(save: Save)(using conn: Conn): IO[Map[String, Seq[String]]] = IO.blocking {
    Spi_sqlite_sync.save_validate(save)
  }


  override def insert_transact(insert: Insert)(using conn: Conn): IO[TxReport] = IO.blocking {
    Spi_sqlite_sync.insert_transact(insert)
  }

  override def insert_inspect(insert: Insert)(using conn: Conn): IO[String] = IO.blocking {
    Spi_sqlite_sync.insert_inspect(insert)
  }

  override def insert_validate(insert: Insert)(using conn: Conn): IO[Seq[(Int, Seq[InsertError])]] = IO.blocking {
    Spi_sqlite_sync.insert_validate(insert)
  }


  override def update_transact(update: Update)(using conn: Conn): IO[TxReport] = IO.blocking {
    Spi_sqlite_sync.update_transact(update)
  }

  override def update_inspect(update: Update)(using conn: Conn): IO[String] = IO.blocking {
    Spi_sqlite_sync.update_inspect(update)
  }

  override def update_validate(update: Update)(using conn: Conn): IO[Map[String, Seq[String]]] = IO.blocking {
    Spi_sqlite_sync.update_validate(update)
  }


  override def delete_transact(delete: Delete)(using conn: Conn): IO[TxReport] = IO.blocking {
    Spi_sqlite_sync.delete_transact(delete)
  }

  override def delete_inspect(delete: Delete)(using conn: Conn): IO[String] = IO.blocking {
    Spi_sqlite_sync.delete_inspect(delete)
  }


  override def fallback_rawQuery(
    query: String,
    debug: Boolean = false,
  )(using conn: Conn): IO[List[List[Any]]] = IO.blocking {
    Spi_sqlite_sync.fallback_rawQuery(query, debug)
  }

  override def fallback_rawTransact(
    txData: String,
    debug: Boolean = false
  )(using conn: Conn): IO[TxReport] = IO.blocking {
    Spi_sqlite_sync.fallback_rawTransact(txData, debug)
  }
}
