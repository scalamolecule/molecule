package molecule.sql.core.facade

import boopickle.Default._
import molecule.core.api.Connection
import molecule.core.marshalling.Boopicklers._
import molecule.core.marshalling.{DatomicPeerProxy, MoleculeRpc, MoleculeRpcJS, MoleculeRpcRequest}
import molecule.sql.core.transaction.SqlDataType_JS


case class SqlConn_JS(
  override val proxy: DatomicPeerProxy,
  moleculeRpcRequest: MoleculeRpcRequest
) extends Connection(proxy) with SqlDataType_JS {

  private[molecule] final override lazy val rpc: MoleculeRpc =
    MoleculeRpcJS("localhost", 8080)
}