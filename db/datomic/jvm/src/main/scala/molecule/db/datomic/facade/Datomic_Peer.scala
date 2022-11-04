package molecule.db.datomic.facade

import java.util.UUID.randomUUID
import datomic.Peer
import molecule.base.api.SchemaTransaction
import molecule.core.util.JavaConversions

import scala.concurrent.{ExecutionContext, Future}


/** Facade to Datomic Peer with selected methods.
 *
 * @groupname database  Database operations
 * @groupprio 10
 * */
trait Datomic_Peer extends JavaConversions {


  def createDatabase(
    protocol: String = "mem",
    dbIdentifier: String = ""
  ): Boolean = {
    Peer.createDatabase(s"datomic:$protocol://$dbIdentifier")
  }

  def deleteDatabase(
    protocol: String = "mem",
    dbIdentifier: String = "localhost:4334/"
  ): Boolean = {
    Peer.deleteDatabase(s"datomic:$protocol://$dbIdentifier")
  }

  private[molecule] def connect(
    protocol: String,
    dbIdentifier: String,
  ): Conn_Peer = {
    val id = if (dbIdentifier == "") randomUUID().toString else dbIdentifier
    Conn_Peer(s"datomic:$protocol://$id")
  }

  def recreateDbFromEdn(
    schemaTransaction: SchemaTransaction,
    protocol: String = "mem",
    dbIdentifier: String = ""
  ): Conn_Peer = {
    val id = if (dbIdentifier == "") randomUUID().toString else dbIdentifier
    deleteDatabase(protocol, id)
    createDatabase(protocol, id)
    val conn = connect(protocol, id)
    // Ensure each transaction finishes before the next
    // partitions/attributes (or none for initial empty test dummy)
    conn.transactEdn(schemaTransaction.datomicSchema)
    conn
  }

}

object Datomic_Peer extends Datomic_Peer

