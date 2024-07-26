package molecule.sql.core.transaction

import java.util.UUID
import molecule.core.marshalling.ConnProxy
import molecule.core.util.Executor._
import molecule.sql.core.facade.JdbcConn_JVM
import scala.collection.mutable
import scala.concurrent.Future

trait ConnectionPool {

  // todo: use Hikari or other real connection pool solution

  private val connectionPool = mutable.HashMap.empty[UUID, Future[JdbcConn_JVM]]

  protected def getJdbcConn(proxy: ConnProxy): Future[JdbcConn_JVM] = ???

  protected def getConn(proxy: ConnProxy): Future[JdbcConn_JVM] = {
    val futConn             = connectionPool.getOrElse(proxy.uuid, getJdbcConn(proxy))
    val futConnTimeAdjusted = futConn.map { conn =>
      conn
    }
    connectionPool(proxy.uuid) = futConnTimeAdjusted
    futConnTimeAdjusted
  }
}
