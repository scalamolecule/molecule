package molecule.db.postgresql.spi

import molecule.core.error.{InsertError, InsertErrors, MoleculeError, ValidationErrors}
import molecule.db.common.crud.*
import molecule.db.common.facade.JdbcConn_JVM
import molecule.db.common.spi.{Conn, Spi_zio, StreamingJdbc, TxReport}
import molecule.db.common.util.ModelUtils
import zio.*
import zio.stream.*

trait Spi_postgresql_zio extends Spi_zio with SpiBase_postgresql_zio with StreamingJdbc with ModelUtils {

  // Query --------------------------------------------------------

  override def query_get[Tpl](q: Query[Tpl]): ZIO[Conn, MoleculeError, List[Tpl]] = {
    sync2zio[List[Tpl]]((conn: JdbcConn_JVM) => Spi_postgresql_sync.query_get(q)(using conn))
  }

  override def query_inspect[Tpl](q: Query[Tpl]): ZIO[Conn, MoleculeError, String] = {
    sync2zio[String]((conn: JdbcConn_JVM) => Spi_postgresql_sync.query_inspect(q)(using conn))
  }


  override def queryOffset_get[Tpl](q: QueryOffset[Tpl]): ZIO[Conn, MoleculeError, (List[Tpl], Int, Boolean)] = {
    sync2zio[(List[Tpl], Int, Boolean)]((conn: JdbcConn_JVM) => Spi_postgresql_sync.queryOffset_get(q)(using conn))
  }

  override def queryOffset_inspect[Tpl](q: QueryOffset[Tpl]): ZIO[Conn, MoleculeError, String] = {
    sync2zio[String]((conn: JdbcConn_JVM) => Spi_postgresql_sync.queryOffset_inspect(q)(using conn))
  }


  override def queryCursor_get[Tpl](q: QueryCursor[Tpl]): ZIO[Conn, MoleculeError, (List[Tpl], String, Boolean)] = {
    sync2zio[(List[Tpl], String, Boolean)]((conn: JdbcConn_JVM) => Spi_postgresql_sync.queryCursor_get(q)(using conn))
  }

  override def queryCursor_inspect[Tpl](q: QueryCursor[Tpl]): ZIO[Conn, MoleculeError, String] = {
    sync2zio[String]((conn: JdbcConn_JVM) => Spi_postgresql_sync.queryCursor_inspect(q)(using conn))
  }


  override def query_stream[Tpl](
    q: Query[Tpl],
    chunkSize: Int = 100
  ): ZStream[Conn, MoleculeError, Tpl] = {
    zioStream(
      q, chunkSize,
      (q: Query[Tpl], conn: Conn) => Spi_postgresql_sync.query_inspect[Tpl](q)(using conn),
      Spi_postgresql_sync.getResultSetAndRowResolver[Tpl]
    )
  }


  override def query_subscribe[Tpl](q: Query[Tpl], callback: List[Tpl] => Unit): ZIO[Conn, MoleculeError, Unit] = {
    sync2zio[Unit]((conn: JdbcConn_JVM) => Spi_postgresql_sync.query_subscribe(q, callback)(using conn))
  }

  override def query_unsubscribe[Tpl](q: Query[Tpl]): ZIO[Conn, MoleculeError, Unit] = {
    sync2zio[Unit]((conn: JdbcConn_JVM) => Spi_postgresql_sync.query_unsubscribe(q)(using conn))
  }


  // Save --------------------------------------------------------

  override def save_transact(save: Save): ZIO[Conn, MoleculeError, TxReport] = {
    for {
      conn0 <- ZIO.service[Conn]
      conn = conn0.asInstanceOf[JdbcConn_JVM]
      errors <- save_validate(save)
      txReport <- mapError(
        ZIO.attemptBlocking(
          errors match {
            case errors if errors.isEmpty => Spi_postgresql_sync.save_transact(save)(using conn)
            case errors                   => throw ValidationErrors(errors)
          }
        )
      )
    } yield txReport
  }

  override def save_inspect(save: Save): ZIO[Conn, MoleculeError, String] = {
    sync2zio[String]((conn: JdbcConn_JVM) => Spi_postgresql_sync.save_inspect(save)(using conn))
  }

  override def save_validate(save: Save): ZIO[Conn, MoleculeError, Map[String, Seq[String]]] = {
    sync2zio[Map[String, Seq[String]]]((conn: JdbcConn_JVM) => Spi_postgresql_sync.save_validate(save)(using conn))
  }


  // Insert --------------------------------------------------------

  override def insert_transact(insert: Insert): ZIO[Conn, MoleculeError, TxReport] = {
    for {
      conn0 <- ZIO.service[Conn]
      conn = conn0.asInstanceOf[JdbcConn_JVM]
      errors <- insert_validate(insert)
      txReport <- mapError(
        ZIO.attemptBlocking(
          errors match {
            case errors if errors.isEmpty => Spi_postgresql_sync.insert_transact(insert)(using conn)
            case errors                   => throw InsertErrors(errors)
          }
        )
      )
    } yield txReport
  }

  override def insert_inspect(insert: Insert): ZIO[Conn, MoleculeError, String] = {
    sync2zio[String]((conn: JdbcConn_JVM) => Spi_postgresql_sync.insert_inspect(insert)(using conn))
  }

  override def insert_validate(insert: Insert): ZIO[Conn, MoleculeError, Seq[(Int, Seq[InsertError])]] = {
    sync2zio[Seq[(Int, Seq[InsertError])]]((conn: JdbcConn_JVM) => Spi_postgresql_sync.insert_validate(insert)(using conn))
  }


  // Update --------------------------------------------------------

  override def update_transact(update: Update): ZIO[Conn, MoleculeError, TxReport] = {
    for {
      conn0 <- ZIO.service[Conn]
      conn = conn0.asInstanceOf[JdbcConn_JVM]
      errors <- update_validate(update)
      txReport <- mapError(
        ZIO.attemptBlocking(
          errors match {
            case errors if errors.isEmpty => Spi_postgresql_sync.update_transact(update)(using conn)
            case errors                   => throw ValidationErrors(errors)
          }
        )
      )
    } yield txReport
  }

  override def update_inspect(update: Update): ZIO[Conn, MoleculeError, String] = {
    sync2zio[String]((conn: JdbcConn_JVM) => Spi_postgresql_sync.update_inspect(update)(using conn))
  }

  override def update_validate(update: Update): ZIO[Conn, MoleculeError, Map[String, Seq[String]]] = {
    sync2zio[Map[String, Seq[String]]]((conn: JdbcConn_JVM) => Spi_postgresql_sync.update_validate(update)(using conn))
  }


  // Delete --------------------------------------------------------

  override def delete_transact(delete: Delete): ZIO[Conn, MoleculeError, TxReport] = {
    for {
      conn0 <- ZIO.service[Conn]
      conn = conn0.asInstanceOf[JdbcConn_JVM]
      txReport <- mapError(
        ZIO.attemptBlocking(
          Spi_postgresql_sync.delete_transact(delete)(using conn)
        )
      )
    } yield txReport
  }

  override def delete_inspect(delete: Delete): ZIO[Conn, MoleculeError, String] = {
    sync2zio[String]((conn: JdbcConn_JVM) => Spi_postgresql_sync.delete_inspect(delete)(using conn))
  }


  // Fallbacks --------------------------------------------------------

  override def fallback_rawQuery(
    query: String,
    debug: Boolean = false,
  ): ZIO[Conn, MoleculeError, List[List[Any]]] = {
    for {
      conn0 <- ZIO.service[Conn]
      conn = conn0.asInstanceOf[JdbcConn_JVM]
      result <- mapError(ZIO.attemptBlocking(
        Spi_postgresql_sync.fallback_rawQuery(query, debug)(using conn)
      ))
    } yield result
  }

  override def fallback_rawTransact(
    txData: String,
    debug: Boolean = false
  ): ZIO[Conn, MoleculeError, TxReport] = {
    for {
      conn0 <- ZIO.service[Conn]
      conn = conn0.asInstanceOf[JdbcConn_JVM]
      result <- mapError(ZIO.attemptBlocking(
        Spi_postgresql_sync.fallback_rawTransact(txData, debug)(using conn)
      ))
    } yield result
  }


  // Helpers

  protected def sync2zio[T](query: JdbcConn_JVM => T): ZIO[Conn, MoleculeError, T] = {
    for {
      conn0 <- ZIO.service[Conn]
      conn = conn0.asInstanceOf[JdbcConn_JVM]
      result <- mapError(ZIO.attemptBlocking(query(conn)))
    } yield result
  }
}