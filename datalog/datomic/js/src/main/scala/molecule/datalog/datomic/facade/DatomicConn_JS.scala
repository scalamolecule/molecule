package molecule.datalog.datomic.facade

import boopickle.Default._
import molecule.core.marshalling.Boopicklers._
import molecule.core.marshalling.{DatomicProxy, MoleculeClient, MoleculeRpc}
import molecule.core.spi.Conn
import molecule.datalog.datomic.transaction.DatomicDataType_JS
import sttp.model.Uri


case class DatomicConn_JS(
  override val proxy: DatomicProxy,
  backendBaseUri: Uri
) extends Conn(proxy) with DatomicDataType_JS {

  private[molecule] final override lazy val rpc: MoleculeRpc =
    MoleculeClient(backendBaseUri)
}