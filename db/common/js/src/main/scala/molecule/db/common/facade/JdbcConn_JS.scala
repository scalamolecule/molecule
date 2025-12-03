package molecule.db.common.facade

import molecule.db.common.authentication.AuthContext
import molecule.db.common.marshalling.{JdbcProxy, MoleculeFrontend, MoleculeRpc}
import molecule.db.common.spi.Conn


case class JdbcConn_JS(
  override val proxy: JdbcProxy,
  host: String,
  port: Int,
  protocol: String = "http",
  override val authContext: Option[AuthContext] = None
) extends Conn(proxy, authContext) {

  private[molecule] final override lazy val rpc: MoleculeRpc =
    MoleculeFrontend(host, port, protocol)

  /** Create a new connection with the specified authentication context
   *
   * SECURITY WARNING: This method should NOT be called directly on the frontend.
   * Authentication must happen on the backend. Calling this method on the frontend
   * defeats the entire security model.
   *
   * This implementation throws an exception to prevent misuse.
   */
  override def withAuthContext(authCtx: AuthContext): Conn =
    throw new UnsupportedOperationException(
      "Authentication is not supported on the frontend. " +
        "AuthContext instances must be created on the backend after validating tokens. " +
        "Send authentication tokens to your backend for validation instead."
    )

  /** Create a new connection without authentication (public access only) */
  override def clearAuth: Conn =
    copy(authContext = None)
}