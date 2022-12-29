package molecule.db.datomic.facade

import boopickle.Default._
import molecule.base.api.SchemaTransaction
import molecule.core.api.{Connection, TxReport}
import molecule.core.marshalling.{MoleculeRpc, WebClient}
import molecule.db.datomic.transaction.DatomicDataType_JS
import scala.concurrent.{ExecutionContext, Future}


case class DatomicConn_JS(
  override val schema: SchemaTransaction,
    interface: String,
    port: Int
) extends Connection(schema) with DatomicDataType_JS with  WebClient {

  private[molecule] final override lazy val rpc: MoleculeRpc =
    moleculeAjax(interface, port).wire[MoleculeRpc]

  //  private[molecule] final override lazy val rpc: MoleculeRpc =
  //    Client.apply[ByteBuffer, Future](
  //      MoleculeRpcRequest("localhost", 8080)
  //    ).wire[MoleculeRpc]

//  override type Data = String // edn string

//  override def transact[T <: Data](data: T)(implicit ec: ExecutionContext): Future[TxReport] = ???
  override def transact(data: Data)(implicit ec: ExecutionContext): Future[TxReport] = ???
}


//object DatomicConn_JS {
//  def apply(
//    schema: SchemaTransaction,
//    uri: String,
//    isFreeVersion: Boolean
//  ): DatomicConn_JS =
//    new DatomicConn_JS(schema, datomic.Peer.connect(uri), isFreeVersion)
//
//  def apply(
//    schema: SchemaTransaction,
//    peerConn: DatomicConnection,
//    isFreeVersion: Boolean
//  ): DatomicConn_JS =
//    new DatomicConn_JS(schema, peerConn, isFreeVersion)
//}