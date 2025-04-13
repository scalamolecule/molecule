package molecule.datalog.datomic.setup

import molecule.base.api.{Schema_datomic, Schema_sqlite}
import molecule.core.marshalling.{DatomicProxy, JdbcProxy}
import molecule.core.spi.Conn
import molecule.core.util.Executor._
import molecule.coreTests.setup.DbConnection
import molecule.datalog.datomic.facade.{DatomicConn_JVM, DatomicPeer}
import zio.{ZIO, ZLayer}
import scala.concurrent.duration._
import scala.concurrent.{Await, Future}

trait DbConnection_datomic extends DbConnection {

  def getConnection(schema: Schema_datomic): DatomicConn_JVM = {
    val proxy = DatomicProxy("mem", "", schema)

    // Block to enable supplying Connection instead of Future[Connection] to tests
    Await.result(
      DatomicPeer.recreateDb(proxy),
      10.second
    )
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

  def delay[T](ms: Int)(body: => T): Future[T] = Future {
    Thread.sleep(ms)
    body
  }
}
