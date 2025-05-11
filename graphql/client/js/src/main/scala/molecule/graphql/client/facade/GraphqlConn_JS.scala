package molecule.graphql.client.facade

import boopickle.Default._
import molecule.db.core.marshalling.Boopicklers._
import molecule.db.core.marshalling.{JdbcProxy, MoleculeRpc, MoleculeRpcJS, MoleculeRpcRequest}
import molecule.db.core.spi.Conn
import molecule.graphql.client.transaction.GraphqlDataType_JS


case class GraphqlConn_JS(
  override val proxy: JdbcProxy,
  moleculeRpcRequest: MoleculeRpcRequest
) extends Conn(proxy) with GraphqlDataType_JS {

  private[molecule] final override lazy val rpc: MoleculeRpc =
    MoleculeRpcJS("localhost", 8080)
}