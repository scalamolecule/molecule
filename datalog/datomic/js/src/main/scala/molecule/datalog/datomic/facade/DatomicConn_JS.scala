package molecule.datalog.datomic.facade

import boopickle.Default._
import molecule.core.spi.Conn
import molecule.core.marshalling.Boopicklers._
import molecule.core.marshalling.{DatomicProxy, MoleculeRpc, MoleculeRpcJS, MoleculeRpcRequest}
import molecule.datalog.datomic.transaction.DatomicDataType_JS


case class DatomicConn_JS(
  override val proxy: DatomicProxy,
  moleculeRpcRequest: MoleculeRpcRequest
) extends Conn(proxy) with DatomicDataType_JS {

  private[molecule] final override lazy val rpc: MoleculeRpc =
    MoleculeRpcJS("localhost", 8080)
}