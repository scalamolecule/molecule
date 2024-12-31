package molecule.sql.mariadb.setup

import molecule.base.api.Schema_mariadb
import molecule.core.marshalling.{JdbcProxy_mariadb, RpcRequest}
import molecule.core.spi.Conn
import molecule.coreTests.setup.DbConnection
import molecule.sql.core.facade.JdbcConn_JS
import scala.concurrent.{Future, Promise}
import scala.scalajs.js.timers.setTimeout
import scala.util.{Random, Try}

trait DbConnection_mariadb extends DbConnection {

  override val platform = "js"

  def run(test: Conn => Any, schema: Schema_mariadb): Any = {
    // Since RPC calls run in parallel we need a new connection for
    // each test when using Docker containers.
    // This makes the test suite run slower compared to sequential runs
    // of jvm tests.
    val n   = Random.nextInt().abs
    val url = s"jdbc:tc:mariadb:latest:///test$n" +
      s"?allowMultiQueries=true" +
      s"&autoReconnect=true" +
      s"&user=root" +
      s"&password="

    val proxy = JdbcProxy_mariadb(url, schema)
    val conn  = JdbcConn_JS(proxy, RpcRequest.request)
    test(conn)
  }

  def delay[T](ms: Int)(body: => T): Future[T] = {
    val promise = Promise[T]()
    setTimeout(ms)(
      promise.complete(Try(body))
    )
    promise.future
  }
}
