package molecule.sql.jdbc.facade

import boopickle.Default._
import molecule.core.api.Connection
import molecule.core.marshalling.Boopicklers._
import molecule.core.marshalling.{DatomicPeerProxy, MoleculeRpc, MoleculeRpcJS, MoleculeRpcRequest}
import molecule.sql.jdbc.transaction.JdbcDataType_JS


case class JdbcConn_JS(
  override val proxy: DatomicPeerProxy,
  moleculeRpcRequest: MoleculeRpcRequest
) extends Connection(proxy) with JdbcDataType_JS {

  private[molecule] final override lazy val rpc: MoleculeRpc =
    MoleculeRpcJS("localhost", 8080)
}