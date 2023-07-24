package molecule.sql.jdbc.facade

import boopickle.Default._
import molecule.core.spi.Conn
import molecule.core.marshalling.Boopicklers._
import molecule.core.marshalling.{DatomicProxy, JdbcProxy, MoleculeRpc, MoleculeRpcJS, MoleculeRpcRequest}
import molecule.sql.jdbc.transaction.JdbcDataType_js


case class JdbcConn_js(
  override val proxy: JdbcProxy,
  moleculeRpcRequest: MoleculeRpcRequest
) extends Conn(proxy) with JdbcDataType_js {

  private[molecule] final override lazy val rpc: MoleculeRpc =
    MoleculeRpcJS("localhost", 8080)
}