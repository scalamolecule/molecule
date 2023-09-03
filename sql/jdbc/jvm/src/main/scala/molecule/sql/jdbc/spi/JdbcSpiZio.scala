package molecule.sql.jdbc.spi

import molecule.base.error._
import molecule.core.action._
import molecule.core.spi.{Conn, SpiZio, TxReport}
import molecule.sql.jdbc.facade.JdbcConn_jvm
import zio.ZIO

trait JdbcSpiZio extends SpiZio with JdbcSpiZioBase {

  // Query --------------------------------------------------------

  override def query_get[Tpl](q: Query[Tpl]): ZIO[Conn, MoleculeError, List[Tpl]] = {
    sync2zio[List[Tpl]]((conn: JdbcConn_jvm) => JdbcSpiSync.query_get(q)(conn))
  }

  override def query_subscribe[Tpl](q: Query[Tpl], callback: List[Tpl] => Unit): ZIO[Conn, MoleculeError, Unit] = {
    sync2zio[Unit]((conn: JdbcConn_jvm) => JdbcSpiSync.query_subscribe(q, callback)(conn))
  }

  override def query_inspect[Tpl](q: Query[Tpl]): ZIO[Conn, MoleculeError, Unit] = {
    sync2zio[Unit]((conn: JdbcConn_jvm) => JdbcSpiSync.query_inspect(q)(conn))
  }


  override def queryOffset_get[Tpl](q: QueryOffset[Tpl]): ZIO[Conn, MoleculeError, (List[Tpl], Int, Boolean)] = {
    sync2zio[(List[Tpl], Int, Boolean)]((conn: JdbcConn_jvm) => JdbcSpiSync.queryOffset_get(q)(conn))
  }

  override def queryOffset_inspect[Tpl](q: QueryOffset[Tpl]): ZIO[Conn, MoleculeError, Unit] = {
    sync2zio[Unit]((conn: JdbcConn_jvm) => JdbcSpiSync.queryOffset_inspect(q)(conn))
  }


  override def queryCursor_get[Tpl](q: QueryCursor[Tpl]): ZIO[Conn, MoleculeError, (List[Tpl], String, Boolean)] = {
    sync2zio[(List[Tpl], String, Boolean)]((conn: JdbcConn_jvm) => JdbcSpiSync.queryCursor_get(q)(conn))
  }

  override def queryCursor_inspect[Tpl](q: QueryCursor[Tpl]): ZIO[Conn, MoleculeError, Unit] = {
    sync2zio[Unit]((conn: JdbcConn_jvm) => JdbcSpiSync.queryCursor_inspect(q)(conn))
  }


  // Save --------------------------------------------------------

  override def save_transact(save: Save): ZIO[Conn, MoleculeError, TxReport] = {
    sync2zio[TxReport]((conn: JdbcConn_jvm) => JdbcSpiSync.save_transact(save)(conn))
  }

  override def save_inspect(save: Save): ZIO[Conn, MoleculeError, Unit] = {
    sync2zio[Unit]((conn: JdbcConn_jvm) => JdbcSpiSync.save_inspect(save)(conn))
  }

  override def save_validate(save: Save): ZIO[Conn, MoleculeError, Map[String, Seq[String]]] = {
    sync2zio[Map[String, Seq[String]]]((conn: JdbcConn_jvm) => JdbcSpiSync.save_validate(save)(conn))
  }


  // Insert --------------------------------------------------------

  override def insert_transact(insert: Insert): ZIO[Conn, MoleculeError, TxReport] = {
    sync2zio[TxReport]((conn: JdbcConn_jvm) => JdbcSpiSync.insert_transact(insert)(conn))
  }

  override def insert_inspect(insert: Insert): ZIO[Conn, MoleculeError, Unit] = {
    sync2zio[Unit]((conn: JdbcConn_jvm) => JdbcSpiSync.insert_inspect(insert)(conn))
  }

  override def insert_validate(insert: Insert): ZIO[Conn, MoleculeError, Seq[(Int, Seq[InsertError])]] = {
    sync2zio[Seq[(Int, Seq[InsertError])]]((conn: JdbcConn_jvm) => JdbcSpiSync.insert_validate(insert)(conn))
  }


  // Update --------------------------------------------------------

  override def update_transact(update: Update): ZIO[Conn, MoleculeError, TxReport] = {
    sync2zio[TxReport]((conn: JdbcConn_jvm) => JdbcSpiSync.update_transact(update)(conn))
  }

  override def update_inspect(update: Update): ZIO[Conn, MoleculeError, Unit] = {
    sync2zio[Unit]((conn: JdbcConn_jvm) => JdbcSpiSync.update_inspect(update)(conn))
  }

  override def update_validate(update: Update): ZIO[Conn, MoleculeError, Map[String, Seq[String]]] = {
    sync2zio[Map[String, Seq[String]]]((conn: JdbcConn_jvm) => JdbcSpiSync.update_validate(update)(conn))
  }


  // Delete --------------------------------------------------------

  override def delete_transact(delete: Delete): ZIO[Conn, MoleculeError, TxReport] = {
    sync2zio[TxReport]((conn: JdbcConn_jvm) => JdbcSpiSync.delete_transact(delete)(conn))
  }

  override def delete_inspect(delete: Delete): ZIO[Conn, MoleculeError, Unit] = {
    sync2zio[Unit]((conn: JdbcConn_jvm) => JdbcSpiSync.delete_inspect(delete)(conn))
  }

  // Fallbacks

  override def fallback_rawQuery(
    query: String,
    withNulls: Boolean = false,
    doPrint: Boolean = true,
  ): ZIO[Conn, MoleculeError, List[List[Any]]] = {
    for {
      conn0 <- ZIO.service[Conn]
      conn = conn0.asInstanceOf[JdbcConn_jvm]
      result <- moleculeError(ZIO.attemptBlocking(
        JdbcSpiSync.fallback_rawQuery(query, withNulls, doPrint)(conn)
      ))
    } yield result
  }

  override def fallback_rawTransact(
    txData: String,
    doPrint: Boolean = true
  ): ZIO[Conn, MoleculeError, TxReport] = {
    for {
      conn0 <- ZIO.service[Conn]
      conn = conn0.asInstanceOf[JdbcConn_jvm]
      result <- moleculeError(ZIO.attemptBlocking(
        JdbcSpiSync.fallback_rawTransact(txData, doPrint)(conn)
      ))
    } yield result
  }



  // Helpers

  protected def sync2zio[T](query: JdbcConn_jvm => T): ZIO[Conn, MoleculeError, T] = {
    for {
      conn0 <- ZIO.service[Conn]
      conn = conn0.asInstanceOf[JdbcConn_jvm]
      result <- moleculeError(ZIO.attemptBlocking(query(conn)))
    } yield result
  }
}