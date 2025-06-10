package molecule.db.datalog.datomic.facade

import java.util.UUID.randomUUID
import cats.effect.IO
import datomic.Peer
import molecule.base.util.BaseHelpers
import molecule.core.ast.Schema
import molecule.db.core.marshalling.DatomicProxy
import molecule.db.core.util.Executor.*
import zio.{ZIO, ZLayer}
import scala.concurrent.{ExecutionContext, Future, blocking}

/** Facade to Datomic Peer with selected methods.
 *
 * @groupname database  Database operations
 * @groupprio 10
 * */
trait DatomicPeer extends BaseHelpers {

  def createDatabase(
    protocol: String = "mem",
    dbIdentifier: String = ""
  ): Boolean = blocking {
    Peer.createDatabase(s"datomic:$protocol://$dbIdentifier")
  }

  def deleteDatabase(
    protocol: String = "mem",
    dbIdentifier: String = "localhost:4334/"
  ): Boolean = blocking {
    Peer.deleteDatabase(s"datomic:$protocol://$dbIdentifier")
  }

  def connect(
    proxy: DatomicProxy,
    protocol: String,
    dbIdentifier: String
  ): DatomicConn_JVM = blocking {
    val id  = if (dbIdentifier == "") randomUUID().toString else dbIdentifier
    val uri = s"datomic:$protocol://$id"
    DatomicConn_JVM(proxy, Peer.connect(uri))
  }

  def connect(
    schema: Schema,
    protocol: String = "mem",
    dbIdentifier: String = ""
  ): DatomicConn_JVM = blocking {
    connect(
      DatomicProxy(protocol, dbIdentifier, schema),
      protocol,
      dbIdentifier
    )
  }

  // OBS: if dbIdentifier is supplied, this database will be deleted entirely! Take care
  def recreateDb(
    proxy: DatomicProxy,
  )(implicit ec: ExecutionContext): Future[DatomicConn_JVM] = blocking {
    val protocol     = proxy.protocol
    val dbIdentifier = proxy.dbIdentifier

    val id = if (dbIdentifier == "")
      randomUUID().toString
    else
      dbIdentifier
    deleteDatabase(protocol, id)
    createDatabase(protocol, id)
    val conn                                                   = connect(proxy, protocol, id)
    val List(datomicPartitions, datomicSchema, datomicAliases) =
      conn.proxy.schemaData

    // Ensure each transaction finishes before the next
    for {
      // partitions
      _ <- if (datomicPartitions.nonEmpty)
        conn.transactEdn(datomicPartitions) else Future.unit
      // attributes
      _ <- if (datomicSchema.nonEmpty)
        conn.transactEdn(datomicSchema) else Future.unit
      // aliases
      _ <- if (datomicAliases.nonEmpty)
        conn.transactEdn(datomicAliases) else Future.unit
    } yield conn
  }

  def recreateDb(
    schema: Schema,
    protocol: String = "mem",
    dbIdentifier: String = ""
  )(implicit ec: ExecutionContext): Future[DatomicConn_JVM] = blocking {
    recreateDb(DatomicProxy(protocol, dbIdentifier, schema))
  }

  def recreateDbIO(
    proxy: DatomicProxy,
    protocol: String,
    dbIdentifier: String
  ): IO[DatomicConn_JVM] = {
    val id = if (dbIdentifier == "")
      randomUUID().toString
    else
      dbIdentifier
    deleteDatabase(protocol, id)
    createDatabase(protocol, id)
    val conn                                                   = connect(proxy, protocol, id)
    val List(datomicPartitions, datomicSchema, datomicAliases) =
      conn.proxy.schemaData

    // Ensure each transaction finishes before the next
    for {
      // partitions
      _ <- if (datomicPartitions.nonEmpty)
        conn.transactEdnIO(datomicPartitions) else IO.unit
      // attributes
      _ <- if (datomicSchema.nonEmpty)
        conn.transactEdnIO(datomicSchema) else IO.unit
      // aliases
      _ <- if (datomicAliases.nonEmpty)
        conn.transactEdnIO(datomicAliases) else IO.unit
    } yield conn
  }

  def recreateDbIO(
    schema: Schema,
    protocol: String = "mem",
    dbIdentifier: String = ""
  ): IO[DatomicConn_JVM] = {
    recreateDbIO(
      DatomicProxy(protocol, dbIdentifier, schema),
      protocol,
      dbIdentifier
    )
  }

  def recreateDbZLayer[T](
    proxy: DatomicProxy,
    protocol: String,
    dbIdentifier: String
  ): ZLayer[T, Throwable, DatomicConn_JVM] = {
    ZLayer.scoped(
      ZIO.fromFuture(
        _ => recreateDb(proxy)
      )
    )
  }

  def recreateDbZLayer[T](
    schema: Schema,
    protocol: String = "mem",
    dbIdentifier: String = ""
  ): ZLayer[T, Throwable, DatomicConn_JVM] = {
    recreateDbZLayer(
      DatomicProxy(protocol, dbIdentifier, schema),
      protocol,
      dbIdentifier
    )
  }
}

object DatomicPeer extends DatomicPeer

