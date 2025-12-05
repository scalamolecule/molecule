package molecule.db.common.transaction

import java.util.UUID
import scala.collection.concurrent.TrieMap
import scala.collection.mutable
import scala.concurrent.Future
import molecule.db.common.authentication.AuthContext
import molecule.db.common.facade.JdbcConn_JVM
import molecule.db.common.marshalling.ConnProxy
import molecule.db.common.util.Executor.global as x

trait CachedConnection {

  // Cache database connections by UUID
  // Multiple UUIDs can point to the same database (via proxy.url)
  private val cachedConn = mutable.HashMap.empty[UUID, Future[JdbcConn_JVM]]

  // Optional: Server-side auth cache (injected by server endpoints)
  // Maps connection UUID to authentication context
  // Used for frontend (JS) connections where auth is managed server-side
  protected var authCache: Option[TrieMap[UUID, AuthContext]] = None

  // Setter for server endpoints to inject auth cache
  def setAuthCache(cache: TrieMap[UUID, AuthContext]): Unit = {
    authCache = Some(cache)
  }

  protected def getJdbcConn(proxy: ConnProxy): Future[JdbcConn_JVM] = ???

  /** Get or create a database connection for the given proxy
   *
   * For frontend (JS) connections:
   * 1. Frontend sends requests with a UUID (from JdbcConn_JS.proxy.uuid)
   * 2. This method looks up the UUID in authCache to find the auth context
   * 3. Applies the auth context to the connection before returning it
   *
   * Multiple UUIDs with different auth contexts can share the same database:
   * - All use the same proxy.url to connect to the same database
   * - Each UUID has its own entry in authCache with different auth
   * - This allows testing multiple roles against the same data
   *
   * Example: Testing authorization with different roles
   * - UUID-1 (no auth): connects to jdbc:h2:mem:test123, no authContext
   * - UUID-2 (Admin):   connects to jdbc:h2:mem:test123, authContext(Admin)
   * - UUID-3 (Guest):   connects to jdbc:h2:mem:test123, authContext(Guest)
   */
  protected def getConn(proxy: ConnProxy): Future[JdbcConn_JVM] = {
    val futConn = cachedConn.getOrElse(proxy.uuid, getJdbcConn(proxy))
    cachedConn(proxy.uuid) = futConn

    // Apply auth from cache if available (for frontend requests)
    authCache match {
      case Some(cache) if cache.contains(proxy.uuid) =>
        futConn.map(conn => conn.withAuthContext(cache(proxy.uuid)).asInstanceOf[JdbcConn_JVM])
      case _ =>
        futConn
    }
  }
}
