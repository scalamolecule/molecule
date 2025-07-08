package molecule.db.common.facade

import molecule.db.common.marshalling.{JdbcProxy, MoleculeFrontend, MoleculeRpc}
import molecule.db.common.spi.Conn
import molecule.db.common.transaction.JdbcDataType_JS


case class JdbcConn_JS(
  override val proxy: JdbcProxy,
  host: String,
  port: Int,
  protocol: String = "http"
) extends Conn(proxy) with JdbcDataType_JS {

  private[molecule] final override lazy val rpc: MoleculeRpc =
    MoleculeFrontend(host, port, protocol)
}