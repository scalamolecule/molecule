package molecule.db.datalog.datomic.spi

import molecule.db.base.error.{InsertError, InsertErrors, MoleculeError, ValidationErrors}
import molecule.db.core.action.*
import molecule.db.core.spi.{Conn, Spi_zio, TxReport}
import molecule.db.datalog.core.spi.StreamingDatomic
import molecule.db.datalog.datomic.facade.DatomicConn_JVM
import zio.*
import zio.stream.*

trait Spi_datomic_zio
  extends Spi_zio
    with JVMDatomicSpiBase
    with StreamingDatomic
    with SpiBase_datomic_zio {

  // Query --------------------------------------------------------

  override def query_get[Tpl](
    q: Query[Tpl]
  ): ZIO[Conn, MoleculeError, List[Tpl]] = {
    sync2zio[List[Tpl]]((conn: DatomicConn_JVM) => Spi_datomic_sync.query_get(q)(conn))
  }

  override def query_inspect[Tpl](
    q: Query[Tpl]
  ): ZIO[Conn, MoleculeError, String] = {
    sync2zio[String]((conn: DatomicConn_JVM) => Spi_datomic_sync.query_inspect(q)(conn))
  }


  override def queryOffset_get[Tpl](
    q: QueryOffset[Tpl]
  ): ZIO[Conn, MoleculeError, (List[Tpl], Int, Boolean)] = {
    sync2zio[(List[Tpl], Int, Boolean)]((conn: DatomicConn_JVM) => Spi_datomic_sync.queryOffset_get(q)(conn))
  }

  override def queryOffset_inspect[Tpl](
    q: QueryOffset[Tpl]
  ): ZIO[Conn, MoleculeError, String] = {
    renderInspectQuery("QUERY (offset)", q.dataModel)
  }


  override def queryCursor_get[Tpl](
    q: QueryCursor[Tpl]
  ): ZIO[Conn, MoleculeError, (List[Tpl], String, Boolean)] = {
    sync2zio[(List[Tpl], String, Boolean)]((conn: DatomicConn_JVM) => Spi_datomic_sync.queryCursor_get(q)(conn))
  }

  override def queryCursor_inspect[Tpl](
    q: QueryCursor[Tpl]
  ): ZIO[Conn, MoleculeError, String] = {
    renderInspectQuery("QUERY (cursor)", q.dataModel)
  }


  override def query_stream[Tpl](
    q: Query[Tpl], chunkSize: Int
  ): ZStream[Conn, MoleculeError, Tpl] = {
    zioStreamDatomic(
      q, chunkSize,
      (q: Query[Tpl], conn: Conn) => Spi_datomic_sync.query_inspect[Tpl](q)(conn),
      Spi_datomic_sync.getJavaStreamAndRowResolver[Tpl]
    )
  }


  override def query_subscribe[Tpl](
    q: Query[Tpl], callback: List[Tpl] => Unit
  ): ZIO[Conn, MoleculeError, Unit] = {
    sync2zio[Unit]((conn: DatomicConn_JVM) => Spi_datomic_sync.query_subscribe(q, callback)(conn))
  }

  override def query_unsubscribe[Tpl](
    q: Query[Tpl]
  ): ZIO[Conn, MoleculeError, Unit] = {
    sync2zio[Unit]((conn: DatomicConn_JVM) => Spi_datomic_sync.query_unsubscribe(q)(conn))
  }


  // Save --------------------------------------------------------

  override def save_transact(save: Save): ZIO[Conn, MoleculeError, TxReport] = {
    for {
      conn0 <- ZIO.service[Conn]
      conn = conn0.asInstanceOf[DatomicConn_JVM]
      txReport <- mapError(
        ZIO.fromFuture(ec =>
          Spi_datomic_sync.save_validate(save)(conn) match {
            case errors if errors.isEmpty =>
              val cleanElements  = keywordsSuffixed(save.dataModel.elements, conn.proxy)
              val cleanDataModel = save.dataModel.copy(elements = cleanElements)
              val saveClean      = save.copy(dataModel = cleanDataModel)
              Spi_datomic_async.save_transact(saveClean)(conn, ec)
            case errors                   => throw ValidationErrors(errors)
          }
        )
      )
    } yield txReport
  }

  override def save_inspect(save: Save): ZIO[Conn, MoleculeError, String] = {
    sync2zio[String]((conn: DatomicConn_JVM) => Spi_datomic_sync.save_inspect(save)(conn))
  }

  override def save_validate(save: Save): ZIO[Conn, MoleculeError, Map[String, Seq[String]]] = {
    sync2zio[Map[String, Seq[String]]]((conn: DatomicConn_JVM) => Spi_datomic_sync.save_validate(save)(conn))
  }


  // Insert --------------------------------------------------------

  override def insert_transact(insert: Insert): ZIO[Conn, MoleculeError, TxReport] = {
    for {
      conn0 <- ZIO.service[Conn]
      conn = conn0.asInstanceOf[DatomicConn_JVM]
      txReport <- mapError(
        ZIO.fromFuture(ec =>
          Spi_datomic_sync.insert_validate(insert)(conn) match {
            case errors if errors.isEmpty =>
              val cleanElements  = keywordsSuffixed(insert.dataModel.elements, conn.proxy)
              val cleanDataModel = insert.dataModel.copy(elements = cleanElements)
              val insertClean    = insert.copy(dataModel = cleanDataModel)
              Spi_datomic_async.insert_transact(insertClean)(conn, ec)
            case errors                   => throw InsertErrors(errors)
          }
        )
      )
    } yield txReport
  }

  override def insert_inspect(insert: Insert): ZIO[Conn, MoleculeError, String] = {
    sync2zio[String]((conn: DatomicConn_JVM) => Spi_datomic_sync.insert_inspect(insert)(conn))
  }

  override def insert_validate(insert: Insert): ZIO[Conn, MoleculeError, Seq[(Int, Seq[InsertError])]] = {
    sync2zio[Seq[(Int, Seq[InsertError])]]((conn: DatomicConn_JVM) => Spi_datomic_sync.insert_validate(insert)(conn))
  }


  // Update --------------------------------------------------------

  override def update_transact(update: Update): ZIO[Conn, MoleculeError, TxReport] = {
    for {
      conn0 <- ZIO.service[Conn]
      conn = conn0.asInstanceOf[DatomicConn_JVM]
      txReport <- mapError(
        ZIO.fromFuture(ec =>
          Spi_datomic_sync.update_validate(update)(conn) match {
            case errors if errors.isEmpty =>
              val cleanElements  = keywordsSuffixed(update.dataModel.elements, conn.proxy)
              val cleanDataModel = update.dataModel.copy(elements = cleanElements)
              val updateClean    = update.copy(dataModel = cleanDataModel)
              Spi_datomic_async.update_transact(updateClean)(conn, ec)
            case errors                   => throw ValidationErrors(errors)
          }
        )
      )
    } yield txReport
  }

  override def update_inspect(update: Update): ZIO[Conn, MoleculeError, String] = {
    sync2zio[String]((conn: DatomicConn_JVM) => Spi_datomic_sync.update_inspect(update)(conn))
  }

  override def update_validate(update: Update): ZIO[Conn, MoleculeError, Map[String, Seq[String]]] = {
    sync2zio[Map[String, Seq[String]]]((conn: DatomicConn_JVM) => Spi_datomic_sync.update_validate(update)(conn))
  }


  // Delete --------------------------------------------------------

  override def delete_transact(delete: Delete): ZIO[Conn, MoleculeError, TxReport] = {
    for {
      conn0 <- ZIO.service[Conn]
      conn = conn0.asInstanceOf[DatomicConn_JVM]
      txReport <- mapError(
        ZIO.fromFuture(ec => {
          val cleanElements  = keywordsSuffixed(delete.dataModel.elements, conn.proxy)
          val cleanDataModel = delete.dataModel.copy(elements = cleanElements)
          val deleteClean    = delete.copy(dataModel = cleanDataModel)
          Spi_datomic_async.delete_transact(deleteClean)(conn, ec)
        })
      )
    } yield txReport
  }

  override def delete_inspect(delete: Delete): ZIO[Conn, MoleculeError, String] = {
    sync2zio[String]((conn: DatomicConn_JVM) => Spi_datomic_sync.delete_inspect(delete)(conn))
  }


  // Fallbacks --------------------------------------------------------

  override def fallback_rawQuery(
    query: String,
    debug: Boolean = false,
  ): ZIO[Conn, MoleculeError, List[List[Any]]] = {
    for {
      conn0 <- ZIO.service[Conn]
      conn = conn0.asInstanceOf[DatomicConn_JVM]
      result <- mapError(ZIO.attemptBlocking(
        Spi_datomic_sync.fallback_rawQuery(query, debug)(conn)
      ))
    } yield result
  }

  override def fallback_rawTransact(
    txData: String,
    debug: Boolean = false
  ): ZIO[Conn, MoleculeError, TxReport] = {
    for {
      conn0 <- ZIO.service[Conn]
      conn = conn0.asInstanceOf[DatomicConn_JVM]
      result <- mapError(ZIO.fromFuture(ec =>
        Spi_datomic_async.fallback_rawTransact(txData, debug)(conn, ec)
      ))
    } yield result
  }


  // Helpers ---------

  private def sync2zio[T](process: DatomicConn_JVM => T): ZIO[Conn, MoleculeError, T] = {
    for {
      conn0 <- ZIO.service[Conn]
      conn = conn0.asInstanceOf[DatomicConn_JVM]
      result <- mapError(ZIO.attemptBlocking(process(conn)))
    } yield result
  }
}