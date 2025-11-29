package molecule.db.common.facade

import molecule.db.common.marshalling.{JdbcProxy, MoleculeFrontend, MoleculeRpc}
import molecule.db.common.spi.Conn


case class JdbcConn_JS(
  override val proxy: JdbcProxy,
  host: String,
  port: Int,
  protocol: String = "http",
  override val authContext: Option[molecule.db.common.api.AuthContext] = None
) extends Conn(proxy, authContext)  {

  private[molecule] final override lazy val rpc: MoleculeRpc =
    MoleculeFrontend(host, port, protocol)

  /** Create a new connection with the specified authentication context */
  override def withAuthContext(authCtx: molecule.db.common.api.AuthContext): Conn =
    copy(authContext = Some(authCtx))

  /** Create a new connection without authentication (public access only) */
  override def clearAuth: Conn =
    copy(authContext = None)
}