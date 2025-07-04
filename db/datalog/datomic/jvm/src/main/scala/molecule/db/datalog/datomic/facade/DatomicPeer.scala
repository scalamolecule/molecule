package molecule.db.datalog.datomic.facade

import java.util.UUID.randomUUID
import cats.effect.IO
import datomic.Peer
import molecule.base.util.BaseHelpers
import molecule.db.core.api.MetaDb
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
    metaDb: MetaDb,
    protocol: String = "mem",
    dbIdentifier: String = ""
  ): DatomicConn_JVM = blocking {
    connect(
      DatomicProxy(protocol, dbIdentifier, metaDb),
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
    val conn = connect(proxy, protocol, id)
    conn.transactEdn(proxy.schemaStr).map(_ => conn)
  }

  def recreateDb(
    metaDb: MetaDb,
    protocol: String = "mem",
    dbIdentifier: String = ""
  )(implicit ec: ExecutionContext): Future[DatomicConn_JVM] = blocking {
    recreateDb(DatomicProxy(protocol, dbIdentifier, metaDb))
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
    val conn = connect(proxy, protocol, id)
    conn.transactEdnIO(proxy.schemaStr).map(_ => conn)
  }

  def recreateDbIO(
    metaDb: MetaDb,
    protocol: String = "mem",
    dbIdentifier: String = ""
  ): IO[DatomicConn_JVM] = {
    recreateDbIO(
      DatomicProxy(protocol, dbIdentifier, metaDb),
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
    metaDb: MetaDb,
    protocol: String = "mem",
    dbIdentifier: String = ""
  ): ZLayer[T, Throwable, DatomicConn_JVM] = {
    recreateDbZLayer(
      DatomicProxy(protocol, dbIdentifier, metaDb),
      protocol,
      dbIdentifier
    )
  }
}

object DatomicPeer extends DatomicPeer

