package molecule.db.datomic.facade

import java.util.UUID.randomUUID
import datomic.Peer
import molecule.base.api.SchemaTransaction
import molecule.core.util.JavaConversions
import scala.concurrent.{ExecutionContext, Future, blocking}


/** Facade to Datomic Peer with selected methods.
 *
 * @groupname database  Database operations
 * @groupprio 10
 * */
trait DatomicPeer extends JavaConversions {

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
    schema: SchemaTransaction,
    protocol: String,
    dbIdentifier: String,
    isFreeVersion: Boolean
  ): DatomicConn_JVM = blocking {
    val id = if (dbIdentifier == "") randomUUID().toString else dbIdentifier
    DatomicConn_JVM(schema, s"datomic:$protocol://$id", isFreeVersion)
  }

  def recreateDbFromEdn(
    schema: SchemaTransaction,
    protocol: String = "mem",
    dbIdentifier: String = "",
    isFreeVersion: Boolean = false
  )(implicit ec: ExecutionContext): Future[DatomicConn_JVM] = blocking {
    val id = if (dbIdentifier == "") randomUUID().toString else dbIdentifier
    deleteDatabase(protocol, id)
    createDatabase(protocol, id)
    val conn = connect(schema, protocol, id, isFreeVersion)

    conn.transactEdn(schema.datomicSchema).map(_ => conn)
    //    for {
    //      _ <- deleteDatabase(protocol, id)
    //      _ <- createDatabase(protocol, id)
    //      conn <- connect(schema, protocol, id, isFreeVersion)
    //      // Ensure each transaction finishes before the next
    //      // partitions/attributes (or none for initial empty test dummy)
    //      _ <- conn.transactEdn(schema.datomicSchema)
    //
    //    } yield conn
  }

}

object DatomicPeer extends DatomicPeer

