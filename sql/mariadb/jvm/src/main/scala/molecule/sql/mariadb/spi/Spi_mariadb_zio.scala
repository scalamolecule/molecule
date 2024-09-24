package molecule.sql.mariadb.spi

import molecule.base.error._
import molecule.core.action._
import molecule.core.spi.{Conn, Spi_zio, TxReport}
import molecule.core.util.ModelUtils
import molecule.sql.core.facade.JdbcConn_JVM
import zio.ZIO

trait Spi_mariadb_zio extends Spi_zio with SpiBase_mariadb_zio with ModelUtils {

  // Query --------------------------------------------------------

  override def query_get[Tpl](q: Query[Tpl]): ZIO[Conn, MoleculeError, List[Tpl]] = {
    sync2zio[List[Tpl]]((conn: JdbcConn_JVM) => Spi_mariadb_sync.query_get(q)(conn))
  }

  override def query_subscribe[Tpl](q: Query[Tpl], callback: List[Tpl] => Unit): ZIO[Conn, MoleculeError, Unit] = {
    sync2zio[Unit]((conn: JdbcConn_JVM) => Spi_mariadb_sync.query_subscribe(q, callback)(conn))
  }

  override def query_unsubscribe[Tpl](q: Query[Tpl]): ZIO[Conn, MoleculeError, Unit] = {
    sync2zio[Unit]((conn: JdbcConn_JVM) => Spi_mariadb_sync.query_unsubscribe(q)(conn))
  }

  override def query_inspect[Tpl](q: Query[Tpl]): ZIO[Conn, MoleculeError, Unit] = {
    sync2zio[Unit]((conn: JdbcConn_JVM) => Spi_mariadb_sync.query_inspect(q)(conn))
  }


  override def queryOffset_get[Tpl](q: QueryOffset[Tpl]): ZIO[Conn, MoleculeError, (List[Tpl], Int, Boolean)] = {
    sync2zio[(List[Tpl], Int, Boolean)]((conn: JdbcConn_JVM) => Spi_mariadb_sync.queryOffset_get(q)(conn))
  }

  override def queryOffset_inspect[Tpl](q: QueryOffset[Tpl]): ZIO[Conn, MoleculeError, Unit] = {
    sync2zio[Unit]((conn: JdbcConn_JVM) => Spi_mariadb_sync.queryOffset_inspect(q)(conn))
  }


  override def queryCursor_get[Tpl](q: QueryCursor[Tpl]): ZIO[Conn, MoleculeError, (List[Tpl], String, Boolean)] = {
    sync2zio[(List[Tpl], String, Boolean)]((conn: JdbcConn_JVM) => Spi_mariadb_sync.queryCursor_get(q)(conn))
  }

  override def queryCursor_inspect[Tpl](q: QueryCursor[Tpl]): ZIO[Conn, MoleculeError, Unit] = {
    sync2zio[Unit]((conn: JdbcConn_JVM) => Spi_mariadb_sync.queryCursor_inspect(q)(conn))
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
            case errors if errors.isEmpty => Spi_mariadb_sync.save_transact(save)(conn)
            case errors                   => throw ValidationErrors(errors)
          }
        )
      )
    } yield txReport
  }

  override def save_inspect(save: Save): ZIO[Conn, MoleculeError, Unit] = {
    sync2zio[Unit]((conn: JdbcConn_JVM) => Spi_mariadb_sync.save_inspect(save)(conn))
  }

  override def save_validate(save: Save): ZIO[Conn, MoleculeError, Map[String, Seq[String]]] = {
    sync2zio[Map[String, Seq[String]]]((conn: JdbcConn_JVM) => Spi_mariadb_sync.save_validate(save)(conn))
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
            case errors if errors.isEmpty => Spi_mariadb_sync.insert_transact(insert)(conn)
            case errors                   => throw InsertErrors(errors)
          }
        )
      )
    } yield txReport
  }

  override def insert_inspect(insert: Insert): ZIO[Conn, MoleculeError, Unit] = {
    sync2zio[Unit]((conn: JdbcConn_JVM) => Spi_mariadb_sync.insert_inspect(insert)(conn))
  }

  override def insert_validate(insert: Insert): ZIO[Conn, MoleculeError, Seq[(Int, Seq[InsertError])]] = {
    sync2zio[Seq[(Int, Seq[InsertError])]]((conn: JdbcConn_JVM) => Spi_mariadb_sync.insert_validate(insert)(conn))
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
            case errors if errors.isEmpty => Spi_mariadb_sync.update_transact(update)(conn)
            case errors                   => throw ValidationErrors(errors)
          }
        )
      )
    } yield txReport
  }

  override def update_inspect(update: Update): ZIO[Conn, MoleculeError, Unit] = {
    sync2zio[Unit]((conn: JdbcConn_JVM) => Spi_mariadb_sync.update_inspect(update)(conn))
  }

  override def update_validate(update: Update): ZIO[Conn, MoleculeError, Map[String, Seq[String]]] = {
    sync2zio[Map[String, Seq[String]]]((conn: JdbcConn_JVM) => Spi_mariadb_sync.update_validate(update)(conn))
  }


  // Delete --------------------------------------------------------

  override def delete_transact(delete: Delete): ZIO[Conn, MoleculeError, TxReport] = {
    for {
      conn0 <- ZIO.service[Conn]
      conn = conn0.asInstanceOf[JdbcConn_JVM]
      txReport <- mapError(
        ZIO.attemptBlocking(
          Spi_mariadb_sync.delete_transact(delete)(conn)
        )
      )
    } yield txReport
  }

  override def delete_inspect(delete: Delete): ZIO[Conn, MoleculeError, Unit] = {
    sync2zio[Unit]((conn: JdbcConn_JVM) => Spi_mariadb_sync.delete_inspect(delete)(conn))
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
        Spi_mariadb_sync.fallback_rawQuery(query, debug)(conn)
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
        Spi_mariadb_sync.fallback_rawTransact(txData, debug)(conn)
      ))
    } yield result
  }



  // Helpers

  protected def sync2zio[T](process: JdbcConn_JVM => T): ZIO[Conn, MoleculeError, T] = {
    for {
      conn0 <- ZIO.service[Conn]
      conn = conn0.asInstanceOf[JdbcConn_JVM]
      result <- mapError(ZIO.attemptBlocking(process(conn)))
    } yield result
  }
}