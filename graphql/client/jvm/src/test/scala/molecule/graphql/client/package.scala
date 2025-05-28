package molecule.graphql

import molecule.db.base.error.{InsertError, MoleculeError}
import molecule.db.core.action.{Delete, Insert, Query, QueryCursor, QueryOffset, Save, Update}
import molecule.db.core.api.{Api_async, Api_async_transact, Api_io, Api_io_transact, Api_sync, Api_sync_transact, Api_zio, Api_zio_transact}
import molecule.db.core.spi.{Conn, Spi_zio, TxReport}
import zio.ZIO

package object clientx {

//    object async extends Api_async with Api_async_transact with Spi_h2_async
//    object sync extends Api_sync with Api_sync_transact with Spi_h2_sync

    // With capital Z to avoid collision with zio namespace from ZIO
//    object Zio extends Api_zio with Api_zio_transact with Spi_h2_zio
    object Ziox extends Api_zio with Spi_zio {
      override def query_get[Tpl](q: Query[Tpl]): ZIO[Conn, MoleculeError, List[Tpl]] = ???
      override def query_inspect[Tpl](q: Query[Tpl]): ZIO[Conn, MoleculeError, Unit] = ???
      override def queryOffset_get[Tpl](q: QueryOffset[Tpl]): ZIO[Conn, MoleculeError, (List[Tpl], Int, Boolean)] = ???
      override def queryOffset_inspect[Tpl](q: QueryOffset[Tpl]): ZIO[Conn, MoleculeError, Unit] = ???
      override def queryCursor_get[Tpl](q: QueryCursor[Tpl]): ZIO[Conn, MoleculeError, (List[Tpl], String, Boolean)] = ???
      override def queryCursor_inspect[Tpl](q: QueryCursor[Tpl]): ZIO[Conn, MoleculeError, Unit] = ???
      override def query_subscribe[Tpl](q: Query[Tpl], callback: List[Tpl] => Unit): ZIO[Conn, MoleculeError, Unit] = ???
      override def query_unsubscribe[Tpl](q: Query[Tpl]): ZIO[Conn, MoleculeError, Unit] = ???
      override def save_transact(save: Save): ZIO[Conn, MoleculeError, TxReport] = ???
      override def save_inspect(save: Save): ZIO[Conn, MoleculeError, Unit] = ???
      override def save_validate(save: Save): ZIO[Conn, MoleculeError, Map[String, Seq[String]]] = ???
      override def insert_transact(insert: Insert): ZIO[Conn, MoleculeError, TxReport] = ???
      override def insert_inspect(insert: Insert): ZIO[Conn, MoleculeError, Unit] = ???
      override def insert_validate(insert: Insert): ZIO[Conn, MoleculeError, Seq[(Int, Seq[InsertError])]] = ???
      override def update_transact(update: Update): ZIO[Conn, MoleculeError, TxReport] = ???
      override def update_inspect(update: Update): ZIO[Conn, MoleculeError, Unit] = ???
      override def update_validate(update: Update): ZIO[Conn, MoleculeError, Map[String, Seq[String]]] = ???
      override def delete_transact(delete: Delete): ZIO[Conn, MoleculeError, TxReport] = ???
      override def delete_inspect(delete: Delete): ZIO[Conn, MoleculeError, Unit] = ???
    }
//    object io extends Api_io with Api_io_transact with Spi_h2_io
}
