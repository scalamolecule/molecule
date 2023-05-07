package molecule.datalog.datomic.setup

import java.util.UUID.randomUUID
import molecule.base.api.SchemaTransaction
import molecule.core.api.Connection
import molecule.core.marshalling.DatomicPeerProxy
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.schema._
import molecule.datalog.datomic.facade.DatomicPeer
import molecule.datalog.datomic.setup.DatomicTestSuiteBase
import moleculeBuildInfo.BuildInfo
import scala.concurrent.duration._
import scala.concurrent.{Await, Future}

trait DatomicTestSuite extends DatomicTestSuiteBase {

  lazy val isJsPlatform = false
  lazy val protocol     = BuildInfo.datomicProtocol
  lazy val useFree      = BuildInfo.datomicUseFree

  def inMem[T](test: Connection => T, schemaTx: SchemaTransaction): T = {
    val dbUri = if (protocol == "mem") "" else {
      println(s"Re-creating live database...")
      "localhost:4334/" + randomUUID().toString
    }
    val proxy = DatomicPeerProxy("mem", "", schemaTx)

    // Block to enable supplying Connection instead of Future[Connection] to tests
    val conn = Await.result(
      DatomicPeer.recreateDbFromEdn(proxy, protocol, dbUri, useFree),
      10.second
    )
    test(conn)
  }

  def types[T](test: Connection => T): T = inMem(test, TypesSchema)
  def refs[T](test: Connection => T): T = inMem(test, RefsSchema)
  def unique[T](test: Connection => T): T = inMem(test, UniqueSchema)
  def validation[T](test: Connection => T): T = inMem(test, ValidationSchema)

  def delay[T](ms: Int)(body: => T): Future[T] = Future {
    Thread.sleep(ms)
    body
  }
}
