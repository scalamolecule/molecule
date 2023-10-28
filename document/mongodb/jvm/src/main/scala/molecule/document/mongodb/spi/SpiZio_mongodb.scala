package molecule.document.mongodb.spi

import molecule.base.error._
import molecule.core.action._
import molecule.core.spi.{Conn, SpiZio, TxReport}
import molecule.core.util.ModelUtils
import molecule.document.mongodb.facade.MongoConn_JVM
import zio.ZIO

trait SpiZio_mongodb extends SpiZio with SpiZioBase_mongodb with ModelUtils {

  // Query --------------------------------------------------------

  override def query_get[Tpl](q: Query[Tpl]): ZIO[Conn, MoleculeError, List[Tpl]] = {
    sync2zio[List[Tpl]]((conn: MongoConn_JVM) => SpiSync_mongodb.query_get(q)(conn))
  }

  override def query_subscribe[Tpl](q: Query[Tpl], callback: List[Tpl] => Unit): ZIO[Conn, MoleculeError, Unit] = {
    sync2zio[Unit]((conn: MongoConn_JVM) => SpiSync_mongodb.query_subscribe(q, callback)(conn))
  }

  override def query_unsubscribe[Tpl](q: Query[Tpl]): ZIO[Conn, MoleculeError, Unit] = {
    sync2zio[Unit]((conn: MongoConn_JVM) => SpiSync_mongodb.query_unsubscribe(q)(conn))
  }

  override def query_inspect[Tpl](q: Query[Tpl]): ZIO[Conn, MoleculeError, Unit] = {
    sync2zio[Unit]((conn: MongoConn_JVM) => SpiSync_mongodb.query_inspect(q)(conn))
  }


  override def queryOffset_get[Tpl](q: QueryOffset[Tpl]): ZIO[Conn, MoleculeError, (List[Tpl], Int, Boolean)] = {
    sync2zio[(List[Tpl], Int, Boolean)]((conn: MongoConn_JVM) => SpiSync_mongodb.queryOffset_get(q)(conn))
  }

  override def queryOffset_inspect[Tpl](q: QueryOffset[Tpl]): ZIO[Conn, MoleculeError, Unit] = {
    sync2zio[Unit]((conn: MongoConn_JVM) => SpiSync_mongodb.queryOffset_inspect(q)(conn))
  }


  override def queryCursor_get[Tpl](q: QueryCursor[Tpl]): ZIO[Conn, MoleculeError, (List[Tpl], String, Boolean)] = {
    sync2zio[(List[Tpl], String, Boolean)]((conn: MongoConn_JVM) => SpiSync_mongodb.queryCursor_get(q)(conn))
  }

  override def queryCursor_inspect[Tpl](q: QueryCursor[Tpl]): ZIO[Conn, MoleculeError, Unit] = {
    sync2zio[Unit]((conn: MongoConn_JVM) => SpiSync_mongodb.queryCursor_inspect(q)(conn))
  }


  // Save --------------------------------------------------------

  override def save_transact(save: Save): ZIO[Conn, MoleculeError, TxReport] = {
    for {
      conn0 <- ZIO.service[Conn]
      conn = conn0.asInstanceOf[MongoConn_JVM]
      errors <- save_validate(save)
      txReport <- mapError(
        ZIO.attemptBlocking(
          errors match {
            case errors if errors.isEmpty => SpiSync_mongodb.save_transact(
              save.copy(elements = noKeywords(save.elements, Some(conn.proxy)))
            )(conn)
            case errors                   => throw ValidationErrors(errors)
          }
        )
      )
    } yield txReport
  }

  override def save_inspect(save: Save): ZIO[Conn, MoleculeError, Unit] = {
    sync2zio[Unit]((conn: MongoConn_JVM) => SpiSync_mongodb.save_inspect(save)(conn))
  }

  override def save_validate(save: Save): ZIO[Conn, MoleculeError, Map[String, Seq[String]]] = {
    sync2zio[Map[String, Seq[String]]]((conn: MongoConn_JVM) => SpiSync_mongodb.save_validate(save)(conn))
  }


  // Insert --------------------------------------------------------

  override def insert_transact(insert: Insert): ZIO[Conn, MoleculeError, TxReport] = {
    for {
      conn0 <- ZIO.service[Conn]
      conn = conn0.asInstanceOf[MongoConn_JVM]
      errors <- insert_validate(insert)
      txReport <- mapError(
        ZIO.attemptBlocking(
          errors match {
            case errors if errors.isEmpty => SpiSync_mongodb.insert_transact(
              insert.copy(elements = noKeywords(insert.elements, Some(conn.proxy)))
            )(conn)
            case errors                   => throw InsertErrors(errors)
          }
        )
      )
    } yield txReport
  }

  override def insert_inspect(insert: Insert): ZIO[Conn, MoleculeError, Unit] = {
    sync2zio[Unit]((conn: MongoConn_JVM) => SpiSync_mongodb.insert_inspect(insert)(conn))
  }

  override def insert_validate(insert: Insert): ZIO[Conn, MoleculeError, Seq[(Int, Seq[InsertError])]] = {
    sync2zio[Seq[(Int, Seq[InsertError])]]((conn: MongoConn_JVM) => SpiSync_mongodb.insert_validate(insert)(conn))
  }


  // Update --------------------------------------------------------

  override def update_transact(update: Update): ZIO[Conn, MoleculeError, TxReport] = {
    for {
      conn0 <- ZIO.service[Conn]
      conn = conn0.asInstanceOf[MongoConn_JVM]
      errors <- update_validate(update)
      txReport <- mapError(
        ZIO.attemptBlocking(
          errors match {
            case errors if errors.isEmpty => SpiSync_mongodb.update_transact(
              update.copy(elements = noKeywords(update.elements, Some(conn.proxy)))
            )(conn)
            case errors                   => throw ValidationErrors(errors)
          }
        )
      )
    } yield txReport
  }

  override def update_inspect(update: Update): ZIO[Conn, MoleculeError, Unit] = {
    sync2zio[Unit]((conn: MongoConn_JVM) => SpiSync_mongodb.update_inspect(update)(conn))
  }

  override def update_validate(update: Update): ZIO[Conn, MoleculeError, Map[String, Seq[String]]] = {
    sync2zio[Map[String, Seq[String]]]((conn: MongoConn_JVM) => SpiSync_mongodb.update_validate(update)(conn))
  }


  // Delete --------------------------------------------------------

  override def delete_transact(delete: Delete): ZIO[Conn, MoleculeError, TxReport] = {
    for {
      conn0 <- ZIO.service[Conn]
      conn = conn0.asInstanceOf[MongoConn_JVM]
      txReport <- mapError(
        ZIO.attemptBlocking(
          SpiSync_mongodb.delete_transact(
            delete.copy(elements = noKeywords(delete.elements, Some(conn.proxy)))
          )(conn)
        )
      )
    } yield txReport
  }

  override def delete_inspect(delete: Delete): ZIO[Conn, MoleculeError, Unit] = {
    sync2zio[Unit]((conn: MongoConn_JVM) => SpiSync_mongodb.delete_inspect(delete)(conn))
  }


  // Fallbacks --------------------------------------------------------

  override def fallback_rawQuery(
    query: String,
    debug: Boolean = false,
  ): ZIO[Conn, MoleculeError, List[List[Any]]] = {
    for {
      conn0 <- ZIO.service[Conn]
      conn = conn0.asInstanceOf[MongoConn_JVM]
      result <- mapError(ZIO.attemptBlocking(
        SpiSync_mongodb.fallback_rawQuery(query, debug)(conn)
      ))
    } yield result
  }

  override def fallback_rawTransact(
    txData: String,
    debug: Boolean = false
  ): ZIO[Conn, MoleculeError, TxReport] = {
    for {
      conn0 <- ZIO.service[Conn]
      conn = conn0.asInstanceOf[MongoConn_JVM]
      result <- mapError(ZIO.attemptBlocking(
        SpiSync_mongodb.fallback_rawTransact(txData, debug)(conn)
      ))
    } yield result
  }


  // Helpers

  protected def sync2zio[T](query: MongoConn_JVM => T): ZIO[Conn, MoleculeError, T] = {
    for {
      conn0 <- ZIO.service[Conn]
      conn = conn0.asInstanceOf[MongoConn_JVM]
      result <- mapError(ZIO.attemptBlocking(query(conn)))
    } yield result
  }
}