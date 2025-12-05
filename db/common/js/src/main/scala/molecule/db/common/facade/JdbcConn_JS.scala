package molecule.db.common.facade

import java.nio.ByteBuffer
import scala.concurrent.Future
import boopickle.Default.*
import molecule.db.common.authentication.AuthContext
import molecule.db.common.marshalling.Boopicklers.*
import molecule.db.common.marshalling.{JdbcProxy, MoleculeFrontend, MoleculeRpc}
import molecule.db.common.spi.Conn
import molecule.db.common.util.Executor.global as x


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

  /** Convenience method for testing: Set auth on the backend for this connection
   *
   * ⚠️  TESTING ONLY - DO NOT USE IN PRODUCTION ⚠️
   *
   * SECURITY WARNING:
   * This method bypasses normal authentication and allows any userId/role to be claimed
   * without validation. It is restricted to molecule package for internal testing only.
   *
   * In production:
   * - Use proper token-based authentication (JWT, OAuth, etc.)
   * - Validate tokens on the backend before creating AuthContext
   * - Never expose this method or the /auth endpoint to end users
   * - Consider disabling the /auth endpoint in production deployments
   *
   * Architecture:
   * - Creates a NEW connection with a NEW UUID for this auth context
   * - The new UUID is registered server-side with the provided userId/role
   * - Returns a new JdbcConn_JS instance with the new UUID
   *
   * Why create a new UUID?
   * - Each auth context (unauthenticated, Admin, Guest, etc.) needs its own UUID
   * - The server caches auth contexts by UUID in its authCache
   * - This allows multiple connections with different auth contexts to coexist
   *
   * Database sharing:
   * - All connections share the same database URL (from the original proxy)
   * - The server uses the URL to connect to the same database instance
   * - Only the UUID changes, which is used for auth tracking, not database selection
   *
   * Example:
   * {{{
   *   val baseConn = JdbcConn_JS(proxy, "localhost", 8080)  // UUID-1, no auth
   *   val adminConn = baseConn.withAuth("user1", "Admin")    // UUID-2, Admin auth
   *   val guestConn = baseConn.withAuth("user2", "Guest")    // UUID-3, Guest auth
   *   // All three connect to the same database via proxy.url
   *   // But have different auth contexts via UUID-1, UUID-2, UUID-3
   * }}}
   */
  private[molecule] override def withAuth(userId: String, role: String): Future[Conn] = {
    // Create a new proxy with a new UUID for this auth context
    // Keep the same database URL so all connections access the same data
    val newProxy = proxy.asInstanceOf[JdbcProxy].copy(uuid = java.util.UUID.randomUUID())
    val args = Pickle.intoBytes((newProxy.uuid, userId, role))
    val frontend = rpc.asInstanceOf[MoleculeFrontend]

    // Call auth endpoint to register the new UUID with auth on the server
    frontend.fetch(
      frontend.moleculeEndpoint_auth,
      args,
      (buf: ByteBuffer) => Unpickle[Unit].fromBytes(buf)
    ).map {
      case Right(_) =>
        // Return a new connection with the new UUID
        // The server will apply this auth context for all requests with this UUID
        JdbcConn_JS(newProxy, host, port, protocol, authContext)
      case Left(err) =>
        throw new RuntimeException(s"Auth failed: ${err.msg}")
    }
  }

  /** Create a new connection without authentication (public access only) */
  override def clearAuth: Conn =
    copy(authContext = None)
}