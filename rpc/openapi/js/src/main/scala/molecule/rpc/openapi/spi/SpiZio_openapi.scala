package molecule.rpc.openapi.spi

import molecule.base.error._
import molecule.core.action._
import molecule.core.spi.{Conn, SpiZio, TxReport}
import molecule.rpc.openapi.facade.GraphqlConn_JS
import zio._
import scala.concurrent.{Future, ExecutionContext => EC}

trait SpiZio_openapi extends SpiZio with SpiZioBase_openapi {

  // Query --------------------------------------------------------

  override def query_get[Tpl](q: Query[Tpl]): ZIO[Conn, MoleculeError, List[Tpl]] = {
    //    async2zio[List[Tpl]]((conn: JdbcConn_JS, ec: EC) => Spi_postgres_async.query_get(q)(conn, ec))
    ???
  }

  override def query_subscribe[Tpl](q: Query[Tpl], callback: List[Tpl] => Unit): ZIO[Conn, MoleculeError, Unit] = {
    //    async2zio[Unit]((conn: JdbcConn_JS, ec: EC) => Spi_postgres_async.query_subscribe(q, callback)(conn, ec))
    ???
  }

  override def query_unsubscribe[Tpl](q: Query[Tpl]): ZIO[Conn, MoleculeError, Unit] = {
    //    async2zio[Unit]((conn: JdbcConn_JS, ec: EC) => Spi_postgres_async.query_unsubscribe(q)(conn, ec))
    ???
  }

  override def query_inspect[Tpl](q: Query[Tpl]): ZIO[Conn, MoleculeError, Unit] = {
    //    async2zio[Unit]((conn: JdbcConn_JS, ec: EC) => Spi_postgres_async.query_inspect(q)(conn, ec))
    ???
  }


  override def queryOffset_get[Tpl](q: QueryOffset[Tpl]): ZIO[Conn, MoleculeError, (List[Tpl], Int, Boolean)] = {
    //    async2zio[(List[Tpl], Int, Boolean)]((conn: JdbcConn_JS, ec: EC) => Spi_postgres_async.queryOffset_get(q)(conn, ec))
    ???
  }

  override def queryOffset_inspect[Tpl](q: QueryOffset[Tpl]): ZIO[Conn, MoleculeError, Unit] = {
    //    async2zio[Unit]((conn: JdbcConn_JS, ec: EC) => Spi_postgres_async.queryOffset_inspect(q)(conn, ec))
    ???
  }


  override def queryCursor_get[Tpl](q: QueryCursor[Tpl]): ZIO[Conn, MoleculeError, (List[Tpl], String, Boolean)] = {
    //    async2zio[(List[Tpl], String, Boolean)]((conn: JdbcConn_JS, ec: EC) => Spi_postgres_async.queryCursor_get(q)(conn, ec))
    ???
  }

  override def queryCursor_inspect[Tpl](q: QueryCursor[Tpl]): ZIO[Conn, MoleculeError, Unit] = {
    //    async2zio[Unit]((conn: JdbcConn_JS, ec: EC) => Spi_postgres_async.queryCursor_inspect(q)(conn, ec))
    ???
  }


  // Save --------------------------------------------------------

  override def save_transact(save: Save): ZIO[Conn, MoleculeError, TxReport] = {
    //    async2zio[TxReport]((conn: JdbcConn_JS, ec: EC) => Spi_postgres_async.save_transact(save)(conn, ec))
    ???
  }

  override def save_inspect(save: Save): ZIO[Conn, MoleculeError, Unit] = {
    //    async2zio[Unit]((conn: JdbcConn_JS, ec: EC) => Spi_postgres_async.save_inspect(save)(conn, ec))
    ???
  }

  override def save_validate(save: Save): ZIO[Conn, MoleculeError, Map[String, Seq[String]]] = {
    //    async2zio[Map[String, Seq[String]]](
    //      (conn: JdbcConn_JS, ec: EC) => Spi_postgres_async.save_validate(save)(conn, ec)
    //    )
    ???
  }


  // Insert --------------------------------------------------------

  override def insert_transact(insert: Insert): ZIO[Conn, MoleculeError, TxReport] = {
    //    async2zio[TxReport]((conn: JdbcConn_JS, ec: EC) => Spi_postgres_async.insert_transact(insert)(conn, ec))
    ???
  }

  override def insert_inspect(insert: Insert): ZIO[Conn, MoleculeError, Unit] = {
    //    async2zio[Unit]((conn: JdbcConn_JS, ec: EC) => Spi_postgres_async.insert_inspect(insert)(conn, ec))
    ???
  }

  override def insert_validate(insert: Insert): ZIO[Conn, MoleculeError, Seq[(Int, Seq[InsertError])]] = {
    //    async2zio[Seq[(Int, Seq[InsertError])]](
    //      (conn: JdbcConn_JS, ec: EC) => Spi_postgres_async.insert_validate(insert)(conn, ec)
    //    )
    ???
  }


  // Update --------------------------------------------------------

  override def update_transact(update: Update): ZIO[Conn, MoleculeError, TxReport] = {
    //    async2zio[TxReport]((conn: JdbcConn_JS, ec: EC) => Spi_postgres_async.update_transact(update)(conn, ec))
    ???
  }

  override def update_inspect(update: Update): ZIO[Conn, MoleculeError, Unit] = {
    //    async2zio[Unit]((conn: JdbcConn_JS, ec: EC) => Spi_postgres_async.update_inspect(update)(conn, ec))
    ???
  }

  override def update_validate(update: Update): ZIO[Conn, MoleculeError, Map[String, Seq[String]]] = {
    //    async2zio[Map[String, Seq[String]]](
    //      (conn: JdbcConn_JS, ec: EC) => Spi_postgres_async.update_validate(update)(conn, ec)
    //    )
    ???
  }


  // Delete --------------------------------------------------------

  override def delete_transact(delete: Delete): ZIO[Conn, MoleculeError, TxReport] = {
    //    async2zio[TxReport]((conn: JdbcConn_JS, ec: EC) => Spi_postgres_async.delete_transact(delete)(conn, ec))
    ???
  }

  override def delete_inspect(delete: Delete): ZIO[Conn, MoleculeError, Unit] = {
    //    async2zio[Unit]((conn: JdbcConn_JS, ec: EC) => Spi_postgres_async.delete_inspect(delete)(conn, ec))
    ???
  }


  // Helpers ---------

  private def async2zio[T](run: (GraphqlConn_JS, EC) => Future[T]): ZIO[Conn, MoleculeError, T] = {
    //    for {
    //      conn0 <- ZIO.service[Conn]
    //      conn = conn0.asInstanceOf[JdbcConn_JS]
    //      result <- mapError(ZIO.fromFuture(ec => run(conn, ec)))
    //    } yield result
    ???
  }
}
