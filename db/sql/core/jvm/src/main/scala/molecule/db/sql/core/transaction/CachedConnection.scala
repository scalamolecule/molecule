package molecule.db.sql.core.transaction

import java.util.UUID
import molecule.db.core.marshalling.ConnProxy
import molecule.db.sql.core.facade.JdbcConn_JVM
import scala.collection.mutable
import scala.concurrent.Future

trait CachedConnection {

  private val cachedConn = mutable.HashMap.empty[UUID, Future[JdbcConn_JVM]]

  protected def getJdbcConn(proxy: ConnProxy): Future[JdbcConn_JVM] = ???

  protected def getConn(proxy: ConnProxy): Future[JdbcConn_JVM] = {
    val futConn = cachedConn.getOrElse(proxy.uuid, getJdbcConn(proxy))
    cachedConn(proxy.uuid) = futConn
    futConn
  }
}
