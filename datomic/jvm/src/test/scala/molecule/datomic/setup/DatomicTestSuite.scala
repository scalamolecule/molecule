package molecule.datomic.setup

import java.util.UUID.randomUUID
import molecule.base.api.SchemaTransaction
import molecule.core.api.Connection
import molecule.core.marshalling.DatomicPeerProxy
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.schema._
import molecule.datomic.facade.DatomicPeer
import molecule.datomic.setup.DatomicTestSuiteBase
import molecule.datomic.util.DatomicApiLoader
import moleculeBuildInfo.BuildInfo
import scala.concurrent.Await
import scala.concurrent.duration._

trait DatomicTestSuite extends DatomicTestSuiteBase with DatomicApiLoader {

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
      2.seconds
    )
    test(conn)
  }

  def types[T](test: Connection => T): T = inMem(test, TypesSchema)
  def refs[T](test: Connection => T): T = inMem(test, RefsSchema)
  def unique[T](test: Connection => T): T = inMem(test, UniqueSchema)
}
