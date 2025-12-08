package molecule.db.common.spi

import molecule.core.error.{ExecutionError, InsertError, MoleculeError}
import molecule.db.common.crud.*
import zio.stream.ZStream
import zio.{Task, ZIO}

trait Spi_zio {

  private[common] def query_get[Tpl](
    q: Query[Tpl]
  ): ZIO[Conn, MoleculeError, List[Tpl]]

  private[common] def query_inspect[Tpl](
    q: Query[Tpl]
  ): ZIO[Conn, MoleculeError, String]


  private[common] def queryOffset_get[Tpl](
    q: QueryOffset[Tpl]
  ): ZIO[Conn, MoleculeError, (List[Tpl], Int, Boolean)]

  private[common] def queryOffset_inspect[Tpl](
    q: QueryOffset[Tpl]
  ): ZIO[Conn, MoleculeError, String]


  private[common] def queryCursor_get[Tpl](
    q: QueryCursor[Tpl]
  ): ZIO[Conn, MoleculeError, (List[Tpl], String, Boolean)]

  private[common] def queryCursor_inspect[Tpl](
    q: QueryCursor[Tpl]
  ): ZIO[Conn, MoleculeError, String]


  private[common] def query_stream[Tpl](
    q: Query[Tpl], chunkSize: Int = 100
  ): ZStream[Conn, MoleculeError, Tpl] = {
    // (overridden on jvm side)
    ZStream.fromZIO(
      ZIO.fail(
        ExecutionError(
          "Streaming not implemented on JS platform. Maybe use subscribe instead?"
        )
      )
    )
  }


  private[common] def query_subscribe[Tpl](
    q: Query[Tpl], callback: List[Tpl] => Unit
  ): ZIO[Conn, MoleculeError, Unit]

  private[common] def query_unsubscribe[Tpl](
    q: Query[Tpl]
  ): ZIO[Conn, MoleculeError, Unit]


  private[common] def save_transact(save: Save): ZIO[Conn, MoleculeError, TxReport]
  private[common] def save_inspect(save: Save): ZIO[Conn, MoleculeError, String]
  private[common] def save_validate(save: Save): ZIO[Conn, MoleculeError, Map[String, Seq[String]]]

  private[common] def insert_transact(insert: Insert): ZIO[Conn, MoleculeError, TxReport]
  private[common] def insert_inspect(insert: Insert): ZIO[Conn, MoleculeError, String]
  private[common] def insert_validate(insert: Insert): ZIO[Conn, MoleculeError, Seq[(Int, Seq[InsertError])]]

  private[common] def update_transact(update: Update): ZIO[Conn, MoleculeError, TxReport]
  private[common] def update_inspect(update: Update): ZIO[Conn, MoleculeError, String]
  private[common] def update_validate(update: Update): ZIO[Conn, MoleculeError, Map[String, Seq[String]]]

  private[common] def delete_transact(delete: Delete): ZIO[Conn, MoleculeError, TxReport]
  private[common] def delete_inspect(delete: Delete): ZIO[Conn, MoleculeError, String]


  private def noJS(method: String): Nothing =
    throw new Exception(s"Fallback method '$method' not available from JS platform")

  private[common] def fallback_rawQuery(
    query: String,
    debug: Boolean = false,
  ): ZIO[Conn, MoleculeError, List[List[Any]]] = noJS("rawQuery")

  private[common] def fallback_rawTransact(
    txData: String,
    debug: Boolean = false
  ): ZIO[Conn, MoleculeError, TxReport] = noJS("rawTransact")

  protected def mapError[T](result: Task[T]): ZIO[Conn, MoleculeError, T] = {
    result.mapError {
      case e: MoleculeError => e
      case e: Throwable     => ExecutionError(e.toString)
    }
  }
}
