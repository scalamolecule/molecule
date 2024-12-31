package molecule.sql.sqlite.setup

import molecule.base.api.Schema_sqlite
import molecule.core.marshalling.{JdbcProxy_sqlite, RpcRequest}
import molecule.core.spi.Conn
import molecule.coreTests.setup.DbConnection
import molecule.sql.core.facade.JdbcConn_JS
import scala.concurrent.{Future, Promise}
import scala.scalajs.js.timers.setTimeout
import scala.util.Try

trait DbConnection_sqlite extends DbConnection {

  override val platform = "js"

  def run(test: Conn => Any, schema: Schema_sqlite): Any = {
    val proxy = JdbcProxy_sqlite("jdbc:sqlite::memory:", schema)
    test(JdbcConn_JS(proxy, RpcRequest.request))
  }

  def delay[T](ms: Int)(body: => T): Future[T] = {
    val promise = Promise[T]()
    setTimeout(ms)(
      promise.complete(Try(body))
    )
    promise.future
  }
}
