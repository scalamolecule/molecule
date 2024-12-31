package molecule.datalog.datomic.setup

import molecule.base.api.Schema_datomic
import molecule.core.marshalling.DatomicProxy
import molecule.core.spi.Conn
import molecule.core.util.Executor._
import molecule.coreTests.setup.DbConnection
import molecule.datalog.datomic.facade.DatomicPeer
import scala.concurrent.{Await, Future}
import scala.concurrent.duration._

trait DbConnection_datomic extends DbConnection {

  override val platform = "jvm"

  def run(test: Conn => Any, schema: Schema_datomic): Any = {
    val proxy = DatomicProxy("mem", "", schema)

    // Block to enable supplying Connection instead of Future[Connection] to tests
    val conn = Await.result(
      DatomicPeer.recreateDb(proxy),
      10.second
    )
    test(conn)
  }

  def delay[T](ms: Int)(body: => T): Future[T] = Future {
    Thread.sleep(ms)
    body
  }
}
