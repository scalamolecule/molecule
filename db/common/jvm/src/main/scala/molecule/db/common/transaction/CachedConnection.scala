package molecule.db.common.transaction

import java.util.UUID
import molecule.db.common.facade.JdbcConn_JVM
import molecule.db.common.marshalling.ConnProxy
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
