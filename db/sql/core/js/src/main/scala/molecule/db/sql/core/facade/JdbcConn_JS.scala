package molecule.db.sql.core.facade

import boopickle.Default.*
import molecule.db.core.marshalling.Boopicklers.*
import molecule.db.core.marshalling.{JdbcProxy, MoleculeFrontend, MoleculeRpc}
import molecule.db.core.spi.Conn
import molecule.db.sql.core.transaction.JdbcDataType_JS


case class JdbcConn_JS(
  override val proxy: JdbcProxy,
  host: String,
  port: Int
) extends Conn(proxy) with JdbcDataType_JS {

  private[molecule] final override lazy val rpc: MoleculeRpc =
    MoleculeFrontend(host, port)
}