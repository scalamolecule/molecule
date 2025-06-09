package molecule.db.datalog.datomic.facade

import boopickle.Default.*
import molecule.db.core.marshalling.{DatomicProxy, MoleculeFrontend, MoleculeRpc}
import molecule.db.core.spi.Conn
import molecule.db.datalog.datomic.transaction.DatomicDataType_JS


case class DatomicConn_JS(
  override val proxy: DatomicProxy,
  host: String,
  port: Int,
  protocol: String = "http"
) extends Conn(proxy) with DatomicDataType_JS {

  private[molecule] final override lazy val rpc: MoleculeRpc =
    MoleculeFrontend(host, port, protocol)
}