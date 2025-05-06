package molecule.db.datalog.datomic.facade

import boopickle.Default.*
import molecule.core.marshalling.Boopicklers.*
import molecule.core.marshalling.{DatomicProxy, MoleculeFrontend, MoleculeRpc}
import molecule.core.spi.Conn
import molecule.db.datalog.datomic.transaction.DatomicDataType_JS


case class DatomicConn_JS(
  override val proxy: DatomicProxy,
  host: String,
  port: Int
) extends Conn(proxy) with DatomicDataType_JS {

  private[molecule] final override lazy val rpc: MoleculeRpc =
    MoleculeFrontend(host, port)
}