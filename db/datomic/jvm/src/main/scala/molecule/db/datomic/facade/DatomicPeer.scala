package molecule.db.datomic.facade

import java.util.UUID.randomUUID
import datomic.Peer
import molecule.core.marshalling.DatomicPeerProxy
import molecule.db.datomic.util.DatomicApiLoader
import scala.concurrent.{ExecutionContext, Future, blocking}


/** Facade to Datomic Peer with selected methods.
 *
 * @groupname database  Database operations
 * @groupprio 10
 * */
trait DatomicPeer extends DatomicApiLoader {

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

  private[molecule] def connect(
    proxy: DatomicPeerProxy,
    protocol: String,
    dbIdentifier: String = "",
    isFreeVersion: Boolean = true
  ): DatomicConn_JVM = blocking {
    val id = if (dbIdentifier == "") randomUUID().toString else dbIdentifier
    DatomicConn_JVM(proxy, s"datomic:$protocol://$id", isFreeVersion)
  }

  def recreateDbFromEdn(
    proxy: DatomicPeerProxy,
    protocol: String = "mem",
    dbIdentifier: String = "",
    isFreeVersion: Boolean = false
  )(implicit ec: ExecutionContext): Future[DatomicConn_JVM] = blocking {
    val id = if (dbIdentifier == "") randomUUID().toString else dbIdentifier
    deleteDatabase(protocol, id)
    createDatabase(protocol, id)
    val conn = connect(proxy, protocol, id, isFreeVersion)
    // Ensure each transaction finishes before the next
    for {
      // partitions
      _ <- if (proxy.schema.head.nonEmpty)
        conn.transactEdn(proxy.schema.head) else Future.unit
      // attributes
      _ <- if (proxy.schema(1).nonEmpty)
        conn.transactEdn(proxy.schema(1)) else Future.unit
      // aliases
      _ <- if (proxy.schema(2).nonEmpty)
        conn.transactEdn(proxy.schema(2)) else Future.unit
    } yield conn
  }
}

object DatomicPeer extends DatomicPeer

