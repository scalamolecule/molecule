package molecule.document.mongodb.facade

import boopickle.Default._
import molecule.core.marshalling.Boopicklers._
import molecule.core.marshalling.{MoleculeRpc, MoleculeRpcJS, MoleculeRpcRequest, MongoProxy}
import molecule.core.spi.Conn
import molecule.document.mongodb.transaction.DataType_JS_mongodb


case class MongoConn_JS(
  override val proxy: MongoProxy,
  moleculeRpcRequest: MoleculeRpcRequest
) extends Conn(proxy) with DataType_JS_mongodb {

  private[molecule] final override lazy val rpc: MoleculeRpc =
    MoleculeRpcJS("localhost", 8080)
}