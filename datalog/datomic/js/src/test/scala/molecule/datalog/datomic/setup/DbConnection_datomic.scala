package molecule.datalog.datomic.setup

import molecule.base.api.Schema_datomic
import molecule.core.marshalling.DatomicProxy
import molecule.core.spi.Conn
import molecule.coreTests.setup.DbConnection
import molecule.datalog.datomic.facade.DatomicConn_JS
import sttp.client4.UriContext
import zio.{ZIO, ZLayer}
import scala.concurrent.{Future, Promise}
import scala.scalajs.js.timers.setTimeout
import scala.util.Try

trait DbConnection_datomic extends DbConnection {


  def getConnection(schema: Schema_datomic): DatomicConn_JS = {
    val proxy = DatomicProxy("mem", "", schema)
    DatomicConn_JS(proxy, uri"http://localhost:8080")
  }

  def run(test: Conn => Any, schema: Schema_datomic): Any = {
    test(getConnection(schema))
  }

  def connZLayer(schema: Schema_datomic): ZLayer[Any, Throwable, Conn] = {
    ZLayer.scoped(
      ZIO.attemptBlocking {
        getConnection(schema)
      }
    )
  }

  def delay[T](ms: Int)(body: => T): Future[T] = {
    val promise = Promise[T]()
    setTimeout(ms)(
      promise.complete(Try(body))
    )
    promise.future
  }
}
