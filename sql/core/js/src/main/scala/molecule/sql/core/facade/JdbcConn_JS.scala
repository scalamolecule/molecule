package molecule.sql.core.facade

import boopickle.Default._
import molecule.core.marshalling.Boopicklers._
import molecule.core.marshalling.{JdbcProxy, MoleculeClient, MoleculeRpc}
import molecule.core.spi.Conn
import molecule.sql.core.transaction.JdbcDataType_JS
import sttp.model.Uri


case class JdbcConn_JS(
  override val proxy: JdbcProxy,
  backendBaseUri: Uri
) extends Conn(proxy) with JdbcDataType_JS {

  private[molecule] final override lazy val rpc: MoleculeRpc =
    MoleculeClient(backendBaseUri)
}