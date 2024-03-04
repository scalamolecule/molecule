package molecule.rpc.openapi.spi

import molecule.base.error._
import molecule.core.action._
import molecule.core.spi.{Conn, SpiZio, TxReport}
import molecule.core.util.Executor._
import molecule.rpc.openapi.facade.GraphqlConn_JVM
//import molecule.datalog.datomic.facade.DatomicConn_JVM
import zio.ZIO
import scala.concurrent.{Future, ExecutionContext => EC}

trait SpiZio_openapi
  extends SpiZio
//    with JVMDatomicSpiBase
//    with DatomicSpiZioBase
    {

  // Query --------------------------------------------------------

  override def query_get[Tpl](
    q: Query[Tpl]
  ): ZIO[Conn, MoleculeError, List[Tpl]] = {
//    sync2zio[List[Tpl]]((conn: DatomicConn_JVM) => SpiSync_openapi.query_get(q)(conn))
    ???
  }

  override def query_subscribe[Tpl](
    q: Query[Tpl], callback: List[Tpl] => Unit
  ): ZIO[Conn, MoleculeError, Unit] = {
//    sync2zio[Unit]((conn: DatomicConn_JVM) => SpiSync_openapi.query_subscribe(q, callback)(conn))
    ???
  }
  override def query_unsubscribe[Tpl](
    q: Query[Tpl]
  ): ZIO[Conn, MoleculeError, Unit] = {
//    sync2zio[Unit]((conn: DatomicConn_JVM) => SpiSync_openapi.query_unsubscribe(q)(conn))
    ???
  }

  override def query_inspect[Tpl](
    q: Query[Tpl]
  ): ZIO[Conn, MoleculeError, Unit] = {
//    sync2zio[Unit]((conn: DatomicConn_JVM) => SpiSync_openapi.query_inspect(q)(conn))
    ???
  }


  override def queryOffset_get[Tpl](
    q: QueryOffset[Tpl]
  ): ZIO[Conn, MoleculeError, (List[Tpl], Int, Boolean)] = {
//    sync2zio[(List[Tpl], Int, Boolean)]((conn: DatomicConn_JVM) => SpiSync_openapi.queryOffset_get(q)(conn))
    ???
  }

  override def queryOffset_inspect[Tpl](
    q: QueryOffset[Tpl]
  ): ZIO[Conn, MoleculeError, Unit] = {
//    printInspectQuery("QUERY (offset)", q.elements)
    ???
  }


  override def queryCursor_get[Tpl](
    q: QueryCursor[Tpl]
  ): ZIO[Conn, MoleculeError, (List[Tpl], String, Boolean)] = {
//    sync2zio[(List[Tpl], String, Boolean)]((conn: DatomicConn_JVM) => SpiSync_openapi.queryCursor_get(q)(conn))
    ???
  }

  override def queryCursor_inspect[Tpl](
    q: QueryCursor[Tpl]
  ): ZIO[Conn, MoleculeError, Unit] = {
//    printInspectQuery("QUERY (cursor)", q.elements)
    ???
  }


  // Save --------------------------------------------------------

  override def save_transact(save: Save): ZIO[Conn, MoleculeError, TxReport] = {
//    for {
//      conn0 <- ZIO.service[Conn]
//      conn = conn0.asInstanceOf[DatomicConn_JVM]
//      txReport <- mapError(
//        ZIO.fromFuture(ec =>
//          SpiSync_openapi.save_validate(save)(conn) match {
//            case errors if errors.isEmpty => SpiAsync_datomic.save_transact(
//              save.copy(elements = noKeywords(save.elements, Some(conn.proxy)))
//            )(conn, ec)
//            case errors                   => throw ValidationErrors(errors)
//          }
//        )
//      )
//    } yield txReport
    ???
  }

  override def save_inspect(save: Save): ZIO[Conn, MoleculeError, Unit] = {
//    sync2zio[Unit]((conn: DatomicConn_JVM) => SpiSync_openapi.save_inspect(save)(conn))
    ???
  }

  override def save_validate(save: Save): ZIO[Conn, MoleculeError, Map[String, Seq[String]]] = {
//    sync2zio[Map[String, Seq[String]]]((conn: DatomicConn_JVM) => SpiSync_openapi.save_validate(save)(conn))
    ???
  }


  // Insert --------------------------------------------------------

  override def insert_transact(insert: Insert): ZIO[Conn, MoleculeError, TxReport] = {
//    for {
//      conn0 <- ZIO.service[Conn]
//      conn = conn0.asInstanceOf[DatomicConn_JVM]
//      txReport <- mapError(
//        ZIO.fromFuture(ec =>
//          SpiSync_openapi.insert_validate(insert)(conn) match {
//            case errors if errors.isEmpty => SpiAsync_datomic.insert_transact(
//              insert.copy(elements = noKeywords(insert.elements, Some(conn.proxy)))
//            )(conn, ec)
//            case errors                   => throw InsertErrors(errors)
//          }
//        )
//      )
//    } yield txReport
    ???
  }

  override def insert_inspect(insert: Insert): ZIO[Conn, MoleculeError, Unit] = {
//    sync2zio[Unit]((conn: DatomicConn_JVM) => SpiSync_openapi.insert_inspect(insert)(conn))
    ???
  }

  override def insert_validate(insert: Insert): ZIO[Conn, MoleculeError, Seq[(Int, Seq[InsertError])]] = {
//    sync2zio[Seq[(Int, Seq[InsertError])]]((conn: DatomicConn_JVM) => SpiSync_openapi.insert_validate(insert)(conn))
    ???
  }


  // Update --------------------------------------------------------

  override def update_transact(update: Update): ZIO[Conn, MoleculeError, TxReport] = {
//    for {
//      conn0 <- ZIO.service[Conn]
//      conn = conn0.asInstanceOf[DatomicConn_JVM]
//      txReport <- mapError(
//        ZIO.fromFuture(ec =>
//          SpiSync_openapi.update_validate(update)(conn) match {
//            case errors if errors.isEmpty => SpiAsync_datomic.update_transact(
//              update.copy(elements = noKeywords(update.elements, Some(conn.proxy)))
//            )(conn, ec)
//            case errors                   => throw ValidationErrors(errors)
//          }
//        )
//      )
//    } yield txReport
    ???
  }

  override def update_inspect(update: Update): ZIO[Conn, MoleculeError, Unit] = {
//    sync2zio[Unit]((conn: DatomicConn_JVM) => SpiSync_openapi.update_inspect(update)(conn))
    ???
  }

  override def update_validate(update: Update): ZIO[Conn, MoleculeError, Map[String, Seq[String]]] = {
//    sync2zio[Map[String, Seq[String]]]((conn: DatomicConn_JVM) => SpiSync_openapi.update_validate(update)(conn))
    ???
  }


  // Delete --------------------------------------------------------

  override def delete_transact(delete: Delete): ZIO[Conn, MoleculeError, TxReport] = {
//    for {
//      conn0 <- ZIO.service[Conn]
//      conn = conn0.asInstanceOf[DatomicConn_JVM]
//      txReport <- mapError(
//        ZIO.fromFuture(ec =>
//          SpiAsync_datomic.delete_transact(
//            delete.copy(elements = noKeywords(delete.elements, Some(conn.proxy)))
//          )(conn, ec)
//        )
//      )
//    } yield txReport
    ???
  }

  override def delete_inspect(delete: Delete): ZIO[Conn, MoleculeError, Unit] = {
//    sync2zio[Unit]((conn: DatomicConn_JVM) => SpiSync_openapi.delete_inspect(delete)(conn))
    ???
  }


  // Fallbacks --------------------------------------------------------

  override def fallback_rawQuery(
    query: String,
    debug: Boolean = false,
  ): ZIO[Conn, MoleculeError, List[List[Any]]] = {
//    for {
//      conn0 <- ZIO.service[Conn]
//      conn = conn0.asInstanceOf[DatomicConn_JVM]
//      result <- mapError(ZIO.attemptBlocking(
//        SpiSync_openapi.fallback_rawQuery(query, debug)(conn)
//      ))
//    } yield result
    ???
  }

  override def fallback_rawTransact(
    txData: String,
    debug: Boolean = false
  ): ZIO[Conn, MoleculeError, TxReport] = {
//    for {
//      conn0 <- ZIO.service[Conn]
//      conn = conn0.asInstanceOf[DatomicConn_JVM]
//      result <- mapError(ZIO.fromFuture(_ =>
//        SpiAsync_datomic.fallback_rawTransact(txData, debug)(conn, global)
//      ))
//    } yield result
    ???
  }


  // Helpers ---------

  private def sync2zio[T](process: GraphqlConn_JVM => T): ZIO[Conn, MoleculeError, T] = {
//    for {
//      conn0 <- ZIO.service[Conn]
//      conn = conn0.asInstanceOf[DatomicConn_JVM]
//      result <- mapError(ZIO.attemptBlocking(process(conn)))
//    } yield result
    ???
  }
}