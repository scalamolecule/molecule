package molecule.core.spi

import molecule.base.error.*
import molecule.core.action.*
import zio.stream.ZStream
import zio.{Task, ZIO}

trait Spi_zio {

  def query_get[Tpl](
    q: Query[Tpl]
  ): ZIO[Conn, MoleculeError, List[Tpl]]

  def query_inspect[Tpl](
    q: Query[Tpl]
  ): ZIO[Conn, MoleculeError, Unit]


  def queryOffset_get[Tpl](
    q: QueryOffset[Tpl]
  ): ZIO[Conn, MoleculeError, (List[Tpl], Int, Boolean)]

  def queryOffset_inspect[Tpl](
    q: QueryOffset[Tpl]
  ): ZIO[Conn, MoleculeError, Unit]


  def queryCursor_get[Tpl](
    q: QueryCursor[Tpl]
  ): ZIO[Conn, MoleculeError, (List[Tpl], String, Boolean)]

  def queryCursor_inspect[Tpl](
    q: QueryCursor[Tpl]
  ): ZIO[Conn, MoleculeError, Unit]


  def query_stream[Tpl](
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


  def query_subscribe[Tpl](
    q: Query[Tpl], callback: List[Tpl] => Unit
  ): ZIO[Conn, MoleculeError, Unit]

  def query_unsubscribe[Tpl](
    q: Query[Tpl]
  ): ZIO[Conn, MoleculeError, Unit]


  def save_transact(save: Save): ZIO[Conn, MoleculeError, TxReport]
  def save_inspect(save: Save): ZIO[Conn, MoleculeError, Unit]
  def save_validate(save: Save): ZIO[Conn, MoleculeError, Map[String, Seq[String]]]

  def insert_transact(insert: Insert): ZIO[Conn, MoleculeError, TxReport]
  def insert_inspect(insert: Insert): ZIO[Conn, MoleculeError, Unit]
  def insert_validate(insert: Insert): ZIO[Conn, MoleculeError, Seq[(Int, Seq[InsertError])]]

  def update_transact(update: Update): ZIO[Conn, MoleculeError, TxReport]
  def update_inspect(update: Update): ZIO[Conn, MoleculeError, Unit]
  def update_validate(update: Update): ZIO[Conn, MoleculeError, Map[String, Seq[String]]]

  def delete_transact(delete: Delete): ZIO[Conn, MoleculeError, TxReport]
  def delete_inspect(delete: Delete): ZIO[Conn, MoleculeError, Unit]


  private def noJS(method: String): Nothing =
    throw new Exception(s"Fallback method '$method' not available from JS platform")

  def fallback_rawQuery(
    query: String,
    debug: Boolean = false,
  ): ZIO[Conn, MoleculeError, List[List[Any]]] = noJS("rawQuery")

  def fallback_rawTransact(
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
