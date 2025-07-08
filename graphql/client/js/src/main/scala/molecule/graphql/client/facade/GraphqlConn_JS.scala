//package molecule.graphql.client.facade
//
//package molecule.graphql.client.facade
//
//import boopickle.Default._
//import molecule.db.common.marshalling.Boopicklers.*
//import molecule.db.common.marshalling.{JdbcProxy, MoleculeRpc, MoleculeRpcJS, MoleculeRpcRequest}
//import molecule.db.common.spi.Conn
//import molecule.graphql.client.transaction.GraphqlDataType_JS
//
//
//case class GraphqlConn_JS(
//  override val proxy: JdbcProxy,
//  moleculeRpcRequest: MoleculeRpcRequest
//) extends Conn(proxy) with GraphqlDataType_JS {
//
//  private[molecule] final override lazy val rpc: MoleculeRpc =
//    MoleculeRpcJS("localhost", 8080)
//}