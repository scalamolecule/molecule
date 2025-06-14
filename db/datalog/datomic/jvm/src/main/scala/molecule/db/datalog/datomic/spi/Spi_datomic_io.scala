package molecule.db.datalog.datomic.spi

import cats.effect.IO
import molecule.base.error.{InsertError, InsertErrors, ValidationErrors}
import molecule.db.core.action.*
import molecule.db.core.spi.{Conn, Spi_io, TxReport}
import molecule.db.core.util.Executor.global as ec
import molecule.db.datalog.core.spi.StreamingDatomic

trait Spi_datomic_io
  extends Spi_io
    with JVMDatomicSpiBase
    with StreamingDatomic
    with SpiBase_datomic_io {

  // Query --------------------------------------------------------

  override def query_get[Tpl](
    q: Query[Tpl]
  )(implicit conn: Conn): IO[List[Tpl]] = {
    IO(Spi_datomic_sync.query_get(q)(conn))
  }

  override def query_inspect[Tpl](
    q: Query[Tpl]
  )(implicit conn: Conn): IO[String] = {
    IO(Spi_datomic_sync.query_inspect(q)(conn))
  }


  override def queryOffset_get[Tpl](
    q: QueryOffset[Tpl]
  )(implicit conn: Conn): IO[(List[Tpl], Int, Boolean)] = {
    IO(Spi_datomic_sync.queryOffset_get(q)(conn))
  }

  override def queryOffset_inspect[Tpl](
    q: QueryOffset[Tpl]
  )(implicit conn: Conn): IO[String] = {
    renderInspectQuery("QUERY (offset)", q.dataModel)
  }


  override def queryCursor_get[Tpl](
    q: QueryCursor[Tpl]
  )(implicit conn: Conn): IO[(List[Tpl], String, Boolean)] = {
    IO(Spi_datomic_sync.queryCursor_get(q)(conn))
  }

  override def queryCursor_inspect[Tpl](
    q: QueryCursor[Tpl]
  )(implicit conn: Conn): IO[String] = {
    renderInspectQuery("QUERY (cursor)", q.dataModel)
  }


  override def query_stream[Tpl](
    q: Query[Tpl], chunkSize: Int
  )(implicit conn0: Conn): fs2.Stream[IO, Tpl] = {
    fs2streamDatomic(
      q, chunkSize,
      (q: Query[Tpl], conn: Conn) => Spi_datomic_sync.query_inspect[Tpl](q)(conn),
      Spi_datomic_sync.getJavaStreamAndRowResolver[Tpl]
    )
  }


  override def query_subscribe[Tpl](
    q: Query[Tpl], callback: List[Tpl] => Unit
  )(implicit conn: Conn): IO[Unit] = {
    IO(Spi_datomic_sync.query_subscribe(q, callback)(conn))
  }

  override def query_unsubscribe[Tpl](
    q: Query[Tpl]
  )(implicit conn: Conn): IO[Unit] = {
    IO(Spi_datomic_sync.query_unsubscribe(q)(conn))
  }


  // Save --------------------------------------------------------

  override def save_transact(save: Save)(implicit conn: Conn): IO[TxReport] = {
    IO.fromFuture {
      IO.blocking {
        Spi_datomic_sync.save_validate(save)(conn) match {
          case errors if errors.isEmpty =>
            val cleanElements  = keywordsSuffixed(save.dataModel.elements, conn.proxy)
            val cleanDataModel = save.dataModel.copy(elements = cleanElements)
            val saveClean      = save.copy(dataModel = cleanDataModel)
            Spi_datomic_async.save_transact(saveClean)(conn, ec)
          case errors                   => throw ValidationErrors(errors)
        }
      }
    }
  }

  override def save_inspect(save: Save)(implicit conn: Conn): IO[String] = {
    IO(Spi_datomic_sync.save_inspect(save)(conn))
  }

  override def save_validate(save: Save)(implicit conn: Conn): IO[Map[String, Seq[String]]] = {
    IO(Spi_datomic_sync.save_validate(save)(conn))
  }


  // Insert --------------------------------------------------------

  override def insert_transact(insert: Insert)(implicit conn: Conn): IO[TxReport] = {
    IO.fromFuture {
      IO.blocking {
        Spi_datomic_sync.insert_validate(insert)(conn) match {
          case errors if errors.isEmpty =>
            val cleanElements  = keywordsSuffixed(insert.dataModel.elements, conn.proxy)
            val cleanDataModel = insert.dataModel.copy(elements = cleanElements)
            val insertClean    = insert.copy(dataModel = cleanDataModel)
            Spi_datomic_async.insert_transact(insertClean)(conn, ec)
          case errors                   => throw InsertErrors(errors)
        }
      }
    }
  }

  override def insert_inspect(insert: Insert)(implicit conn: Conn): IO[String] = {
    IO(Spi_datomic_sync.insert_inspect(insert)(conn))
  }

  override def insert_validate(insert: Insert)(implicit conn: Conn): IO[Seq[(Int, Seq[InsertError])]] = {
    IO(Spi_datomic_sync.insert_validate(insert)(conn))
  }


  // Update --------------------------------------------------------

  override def update_transact(update: Update)(implicit conn: Conn): IO[TxReport] = {
    IO.fromFuture {
      IO.blocking {
        Spi_datomic_sync.update_validate(update)(conn) match {
          case errors if errors.isEmpty =>
            val cleanElements  = keywordsSuffixed(update.dataModel.elements, conn.proxy)
            val cleanDataModel = update.dataModel.copy(elements = cleanElements)
            val updateClean    = update.copy(dataModel = cleanDataModel)
            Spi_datomic_async.update_transact(updateClean)(conn, ec)
          case errors                   => throw ValidationErrors(errors)
        }
      }
    }
  }

  override def update_inspect(update: Update)(implicit conn: Conn): IO[String] = {
    IO(Spi_datomic_sync.update_inspect(update)(conn))
  }

  override def update_validate(update: Update)(implicit conn: Conn): IO[Map[String, Seq[String]]] = {
    IO(Spi_datomic_sync.update_validate(update)(conn))
  }


  // Delete --------------------------------------------------------

  override def delete_transact(delete: Delete)(implicit conn: Conn): IO[TxReport] = {
    IO.fromFuture {
      IO.blocking {
        val cleanElements  = keywordsSuffixed(delete.dataModel.elements, conn.proxy)
        val cleanDataModel = delete.dataModel.copy(elements = cleanElements)
        val deleteClean    = delete.copy(dataModel = cleanDataModel)
        Spi_datomic_async.delete_transact(deleteClean)(conn, ec)
      }
    }
  }

  override def delete_inspect(delete: Delete)(implicit conn: Conn): IO[String] = {
    IO(Spi_datomic_sync.delete_inspect(delete)(conn))
  }


  // Fallbacks --------------------------------------------------------

  override def fallback_rawQuery(
    query: String,
    debug: Boolean = false,
  )(implicit conn: Conn): IO[List[List[Any]]] = {
    IO(Spi_datomic_sync.fallback_rawQuery(query, debug)(conn))
  }

  override def fallback_rawTransact(
    txData: String,
    debug: Boolean = false
  )(implicit conn: Conn): IO[TxReport] = {
    IO.fromFuture {
      IO.blocking {
        Spi_datomic_async.fallback_rawTransact(txData, debug)
      }
    }
  }
}