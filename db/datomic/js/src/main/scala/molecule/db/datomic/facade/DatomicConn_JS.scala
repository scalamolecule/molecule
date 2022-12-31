package molecule.db.datomic.facade

import boopickle.Default._
import molecule.core.api.{Connection, TxReport}
import molecule.core.marshalling.{BooPicklers, DatomicPeerProxy, MoleculeRpc, WebClient}
import molecule.db.datomic.transaction.DatomicDataType_JS
import scala.concurrent.{ExecutionContext, Future}


case class DatomicConn_JS(
  override val proxy: DatomicPeerProxy,
  interface: String,
  port: Int
) extends Connection(proxy)
  with DatomicDataType_JS
  with BooPicklers
  with  WebClient {

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