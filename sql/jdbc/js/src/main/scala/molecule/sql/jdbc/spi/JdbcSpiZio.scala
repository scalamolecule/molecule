package molecule.sql.jdbc.spi

import molecule.base.error._
import molecule.core.action._
import molecule.core.spi.{Conn, SpiZio, TxReport}
import molecule.sql.jdbc.facade.JdbcConn_js
import zio._
import scala.concurrent.{Future, ExecutionContext => EC}

trait JdbcSpiZio extends SpiZio with JdbcSpiZioBase {

  // Query --------------------------------------------------------

  override def query_get[Tpl](q: Query[Tpl]): ZIO[Conn, MoleculeError, List[Tpl]] = {
    async2zio[List[Tpl]]((conn: JdbcConn_js, ec: EC) => JdbcSpiAsync.query_get(q)(conn, ec))
  }

  override def query_subscribe[Tpl](q: Query[Tpl], callback: List[Tpl] => Unit): ZIO[Conn, MoleculeError, Unit] = {
    async2zio[Unit]((conn: JdbcConn_js, ec: EC) => JdbcSpiAsync.query_subscribe(q, callback)(conn, ec))
  }

  override def query_inspect[Tpl](q: Query[Tpl]): ZIO[Conn, MoleculeError, Unit] = {
    async2zio[Unit]((conn: JdbcConn_js, ec: EC) => JdbcSpiAsync.query_inspect(q)(conn, ec))
  }


  override def queryOffset_get[Tpl](q: QueryOffset[Tpl]): ZIO[Conn, MoleculeError, (List[Tpl], Int, Boolean)] = {
    async2zio[(List[Tpl], Int, Boolean)]((conn: JdbcConn_js, ec: EC) => JdbcSpiAsync.queryOffset_get(q)(conn, ec))
  }

  override def queryOffset_inspect[Tpl](q: QueryOffset[Tpl]): ZIO[Conn, MoleculeError, Unit] = {
    async2zio[Unit]((conn: JdbcConn_js, ec: EC) => JdbcSpiAsync.queryOffset_inspect(q)(conn, ec))
  }


  override def queryCursor_get[Tpl](q: QueryCursor[Tpl]): ZIO[Conn, MoleculeError, (List[Tpl], String, Boolean)] = {
    async2zio[(List[Tpl], String, Boolean)]((conn: JdbcConn_js, ec: EC) => JdbcSpiAsync.queryCursor_get(q)(conn, ec))
  }

  override def queryCursor_inspect[Tpl](q: QueryCursor[Tpl]): ZIO[Conn, MoleculeError, Unit] = {
    async2zio[Unit]((conn: JdbcConn_js, ec: EC) => JdbcSpiAsync.queryCursor_inspect(q)(conn, ec))
  }


  // Save --------------------------------------------------------

  override def save_transact(save: Save): ZIO[Conn, MoleculeError, TxReport] = {
    async2zio[TxReport]((conn: JdbcConn_js, ec: EC) => JdbcSpiAsync.save_transact(save)(conn, ec))
  }

  override def save_inspect(save: Save): ZIO[Conn, MoleculeError, Unit] = {
    async2zio[Unit]((conn: JdbcConn_js, ec: EC) => JdbcSpiAsync.save_inspect(save)(conn, ec))
  }

  override def save_validate(save: Save): ZIO[Conn, MoleculeError, Map[String, Seq[String]]] = {
    for {
      conn <- ZIO.service[Conn]
      errors <- ZIO.succeed[Map[String, Seq[String]]](
        JdbcSpiAsync.save_validate(save)(conn)
      )
    } yield errors
  }


  // Insert --------------------------------------------------------

  override def insert_transact(insert: Insert): ZIO[Conn, MoleculeError, TxReport] = {
    async2zio[TxReport]((conn: JdbcConn_js, ec: EC) => JdbcSpiAsync.insert_transact(insert)(conn, ec))
  }

  override def insert_inspect(insert: Insert): ZIO[Conn, MoleculeError, Unit] = {
    async2zio[Unit]((conn: JdbcConn_js, ec: EC) => JdbcSpiAsync.insert_inspect(insert)(conn, ec))
  }

  override def insert_validate(insert: Insert): ZIO[Conn, MoleculeError, Seq[(Int, Seq[InsertError])]] = {
    for {
      conn <- ZIO.service[Conn]
      errors <- ZIO.succeed[Seq[(Int, Seq[InsertError])]](
        JdbcSpiAsync.insert_validate(insert)(conn)
      )
    } yield errors
  }


  // Update --------------------------------------------------------

  override def update_transact(update: Update): ZIO[Conn, MoleculeError, TxReport] = {
    async2zio[TxReport]((conn: JdbcConn_js, ec: EC) => JdbcSpiAsync.update_transact(update)(conn, ec))
  }

  override def update_inspect(update: Update): ZIO[Conn, MoleculeError, Unit] = {
    async2zio[Unit]((conn: JdbcConn_js, ec: EC) => JdbcSpiAsync.update_inspect(update)(conn, ec))
  }

  override def update_validate(update: Update): ZIO[Conn, MoleculeError, Map[String, Seq[String]]] = {
    for {
      conn <- ZIO.service[Conn]
      errors <- ZIO.succeed[Map[String, Seq[String]]](
        JdbcSpiAsync.update_validate(update)(conn)
      )
    } yield errors
  }


  // Delete --------------------------------------------------------

  override def delete_transact(delete: Delete): ZIO[Conn, MoleculeError, TxReport] = {
    async2zio[TxReport]((conn: JdbcConn_js, ec: EC) => JdbcSpiAsync.delete_transact(delete)(conn, ec))
  }

  override def delete_inspect(delete: Delete): ZIO[Conn, MoleculeError, Unit] = {
    async2zio[Unit]((conn: JdbcConn_js, ec: EC) => JdbcSpiAsync.delete_inspect(delete)(conn, ec))
  }


  // Helpers ---------

  private def async2zio[T](run: (JdbcConn_js, EC) => Future[T]): ZIO[Conn, MoleculeError, T] = {
    for {
      conn0 <- ZIO.service[Conn]
      conn = conn0.asInstanceOf[JdbcConn_js]
      result <- moleculeError(ZIO.fromFuture(ec => run(conn, ec)))
    } yield result
  }
}