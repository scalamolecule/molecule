package molecule.datalog.datomic.setup

import molecule.base.api.Schema_datomic
import molecule.core.marshalling.{DatomicProxy, RpcRequest}
import molecule.core.spi.Conn
import molecule.coreTests.setup.DbConnection
import molecule.datalog.datomic.facade.DatomicConn_JS
import scala.concurrent.{Future, Promise}
import scala.scalajs.js.timers.setTimeout
import scala.util.Try

trait DbConnection_datomic extends DbConnection {

  override val platform = "js"

  def run(test: Conn => Any, schema: Schema_datomic): Any = {
    val proxy = DatomicProxy("mem", "", schema)
    val conn  = DatomicConn_JS(proxy, RpcRequest.request)
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
