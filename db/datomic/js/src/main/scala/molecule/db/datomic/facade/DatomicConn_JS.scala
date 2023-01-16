package molecule.db.datomic.facade

import boopickle.Default._
import molecule.core.api.Connection
import molecule.core.marshalling.Boopicklers._
import molecule.core.marshalling.{DatomicPeerProxy, MoleculeRpc, MoleculeRpcRequest}
import molecule.db.datomic.marshalling.DatomicRpcJS
import molecule.db.datomic.transaction.DatomicDataType_JS


case class DatomicConn_JS(
  override val proxy: DatomicPeerProxy,
  moleculeRpcRequest: MoleculeRpcRequest
) extends Connection(proxy) with DatomicDataType_JS {

  private[molecule] final override lazy val rpc: MoleculeRpc =
    DatomicRpcJS("localhost", 8080)
}