package molecule.db.sql.h2.spi

import molecule.base.error.{InsertError, MoleculeError}
import molecule.db.core.action.*
import molecule.db.core.spi.{Conn, Spi_zio, TxReport}
import molecule.db.sql.core.facade.JdbcConn_JS
import zio.*
import scala.concurrent.{Future, ExecutionContext as EC}

trait Spi_h2_zio extends Spi_zio with SpiBase_h2_zio {

  // Query --------------------------------------------------------

  override def query_get[Tpl](q: Query[Tpl]): ZIO[Conn, MoleculeError, List[Tpl]] = {
    async2zio[List[Tpl]]((conn: JdbcConn_JS, ec: EC) => Spi_h2_async.query_get(q)(using conn, ec))
  }

  override def query_inspect[Tpl](q: Query[Tpl]): ZIO[Conn, MoleculeError, String] = {
    async2zio[String]((conn: JdbcConn_JS, ec: EC) => Spi_h2_async.query_inspect(q)(using conn, ec))
  }


  override def queryOffset_get[Tpl](q: QueryOffset[Tpl]): ZIO[Conn, MoleculeError, (List[Tpl], Int, Boolean)] = {
    async2zio[(List[Tpl], Int, Boolean)]((conn: JdbcConn_JS, ec: EC) => Spi_h2_async.queryOffset_get(q)(using conn, ec))
  }

  override def queryOffset_inspect[Tpl](q: QueryOffset[Tpl]): ZIO[Conn, MoleculeError, String] = {
    async2zio[String]((conn: JdbcConn_JS, ec: EC) => Spi_h2_async.queryOffset_inspect(q)(using conn, ec))
  }


  override def queryCursor_get[Tpl](q: QueryCursor[Tpl]): ZIO[Conn, MoleculeError, (List[Tpl], String, Boolean)] = {
    async2zio[(List[Tpl], String, Boolean)]((conn: JdbcConn_JS, ec: EC) => Spi_h2_async.queryCursor_get(q)(using conn, ec))
  }

  override def queryCursor_inspect[Tpl](q: QueryCursor[Tpl]): ZIO[Conn, MoleculeError, String] = {
    async2zio[String]((conn: JdbcConn_JS, ec: EC) => Spi_h2_async.queryCursor_inspect(q)(using conn, ec))
  }


  override def query_subscribe[Tpl](q: Query[Tpl], callback: List[Tpl] => Unit): ZIO[Conn, MoleculeError, Unit] = {
    async2zio[Unit]((conn: JdbcConn_JS, ec: EC) => Spi_h2_async.query_subscribe(q, callback)(using conn, ec))
  }

  override def query_unsubscribe[Tpl](q: Query[Tpl]): ZIO[Conn, MoleculeError, Unit] = {
    async2zio[Unit]((conn: JdbcConn_JS, ec: EC) => Spi_h2_async.query_unsubscribe(q)(using conn, ec))
  }


  // Save --------------------------------------------------------

  override def save_transact(save: Save): ZIO[Conn, MoleculeError, TxReport] = {
    async2zio[TxReport]((conn: JdbcConn_JS, ec: EC) => Spi_h2_async.save_transact(save)(using conn, ec))
  }

  override def save_inspect(save: Save): ZIO[Conn, MoleculeError, String] = {
    async2zio[String]((conn: JdbcConn_JS, ec: EC) => Spi_h2_async.save_inspect(save)(using conn, ec))
  }

  override def save_validate(save: Save): ZIO[Conn, MoleculeError, Map[String, Seq[String]]] = {
    async2zio[Map[String, Seq[String]]](
      (conn: JdbcConn_JS, ec: EC) => Spi_h2_async.save_validate(save)(using conn, ec)
    )
  }


  // Insert --------------------------------------------------------

  override def insert_transact(insert: Insert): ZIO[Conn, MoleculeError, TxReport] = {
    async2zio[TxReport]((conn: JdbcConn_JS, ec: EC) => Spi_h2_async.insert_transact(insert)(using conn, ec))
  }

  override def insert_inspect(insert: Insert): ZIO[Conn, MoleculeError, String] = {
    async2zio[String]((conn: JdbcConn_JS, ec: EC) => Spi_h2_async.insert_inspect(insert)(using conn, ec))
  }

  override def insert_validate(insert: Insert): ZIO[Conn, MoleculeError, Seq[(Int, Seq[InsertError])]] = {
    async2zio[Seq[(Int, Seq[InsertError])]](
      (conn: JdbcConn_JS, ec: EC) => Spi_h2_async.insert_validate(insert)(using conn, ec)
    )
  }


  // Update --------------------------------------------------------

  override def update_transact(update: Update): ZIO[Conn, MoleculeError, TxReport] = {
    async2zio[TxReport]((conn: JdbcConn_JS, ec: EC) => Spi_h2_async.update_transact(update)(using conn, ec))
  }

  override def update_inspect(update: Update): ZIO[Conn, MoleculeError, String] = {
    async2zio[String]((conn: JdbcConn_JS, ec: EC) => Spi_h2_async.update_inspect(update)(using conn, ec))
  }

  override def update_validate(update: Update): ZIO[Conn, MoleculeError, Map[String, Seq[String]]] = {
    async2zio[Map[String, Seq[String]]](
      (conn: JdbcConn_JS, ec: EC) => Spi_h2_async.update_validate(update)(using conn, ec)
    )
  }


  // Delete --------------------------------------------------------

  override def delete_transact(delete: Delete): ZIO[Conn, MoleculeError, TxReport] = {
    async2zio[TxReport]((conn: JdbcConn_JS, ec: EC) => Spi_h2_async.delete_transact(delete)(using conn, ec))
  }

  override def delete_inspect(delete: Delete): ZIO[Conn, MoleculeError, String] = {
    async2zio[String]((conn: JdbcConn_JS, ec: EC) => Spi_h2_async.delete_inspect(delete)(using conn, ec))
  }


  // Helpers ---------

  private def async2zio[T](run: (JdbcConn_JS, EC) => Future[T]): ZIO[Conn, MoleculeError, T] = {
    for {
      conn0 <- ZIO.service[Conn]
      conn = conn0.asInstanceOf[JdbcConn_JS]
      result <- mapError(ZIO.fromFuture(ec => run(conn, ec)))
    } yield result
  }
}
