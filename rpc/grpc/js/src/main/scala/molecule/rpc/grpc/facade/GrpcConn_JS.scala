package molecule.rpc.grpc.facade

import boopickle.Default.*
import molecule.db.common.marshalling.Boopicklers.*
import molecule.db.common.marshalling.{JdbcProxy, MoleculeRpc, MoleculeRpcJS, MoleculeRpcRequest}
import molecule.db.common.spi.Conn
import molecule.rpc.grpc.transaction.GraphqlDataType_JS


case class GrpcConn_JS(
  override val proxy: JdbcProxy,
  moleculeRpcRequest: MoleculeRpcRequest
) extends Conn(proxy) with GraphqlDataType_JS {

  private[molecule] final override lazy val rpc: MoleculeRpc =
    MoleculeRpcJS("localhost", 8080)
}