package molecule.datalog.datomic.facade

import java.util.UUID.randomUUID
import datomic.Peer
import molecule.core.marshalling.DatomicProxy
import scala.concurrent.{ExecutionContext, Future, blocking}
import molecule.base.api.Schema
import molecule.base.util.BaseHelpers


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
    val id = if (dbIdentifier == "") randomUUID().toString else dbIdentifier
    val uri = s"datomic:$protocol://$id"
    DatomicConn_JVM(proxy, Peer.connect(uri))
  }

  def connect(
    schema: Schema,
    protocol: String = "mem",
    dbIdentifier: String = ""
  ): DatomicConn_JVM = blocking {
    connect(
      DatomicProxy(
        protocol,
        dbIdentifier,
        schema.datomicPartitions,
        schema.datomicSchema,
        schema.datomicAliases,
        schema.metaSchema,
        schema.nsMap,
        schema.attrMap,
        schema.uniqueAttrs,
      ),
      protocol,
      dbIdentifier
    )
  }

  // OBS: if dbIdentifier is supplied, this database will be deleted entirely! Take care
  def recreateDb(
    proxy: DatomicProxy,
    protocol: String,
    dbIdentifier: String
  )(implicit ec: ExecutionContext): Future[DatomicConn_JVM] = blocking {
    val id = if (dbIdentifier == "")
      randomUUID().toString
    else
      dbIdentifier
    deleteDatabase(protocol, id)
    createDatabase(protocol, id)
    val conn = connect(proxy, protocol, id)
    // Ensure each transaction finishes before the next
    for {
      // partitions
      _ <- if (proxy.datomicPartitions.nonEmpty)
        conn.transactEdn(proxy.datomicPartitions) else Future.unit
      // attributes
      _ <- if (proxy.datomicSchema.nonEmpty)
        conn.transactEdn(proxy.datomicSchema) else Future.unit
      // aliases
      _ <- if (proxy.datomicAliases.nonEmpty)
        conn.transactEdn(proxy.datomicAliases) else Future.unit
    } yield conn
  }

  def recreateDb(
    schema: Schema,
    protocol: String = "mem",
    dbIdentifier: String = ""
  )(implicit ec: ExecutionContext): Future[DatomicConn_JVM] = blocking {
    recreateDb(
      DatomicProxy(
        protocol,
        dbIdentifier,
        schema.datomicPartitions,
        schema.datomicSchema,
        schema.datomicAliases,
        schema.metaSchema,
        schema.nsMap,
        schema.attrMap,
        schema.uniqueAttrs,
      ),
      protocol,
      dbIdentifier
    )
  }
}

object DatomicPeer extends DatomicPeer

