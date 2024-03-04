package molecule.rpc.openapi.facade

import boopickle.Default._
import molecule.core.marshalling.Boopicklers._
import molecule.core.marshalling.{JdbcProxy, MoleculeRpc, MoleculeRpcJS, MoleculeRpcRequest}
import molecule.core.spi.Conn
import molecule.rpc.openapi.transaction.GraphqlDataType_JS


case class OpenapiConn_JS(
  override val proxy: JdbcProxy,
  moleculeRpcRequest: MoleculeRpcRequest
) extends Conn(proxy) with GraphqlDataType_JS {

  private[molecule] final override lazy val rpc: MoleculeRpc =
    MoleculeRpcJS("localhost", 8080)
}