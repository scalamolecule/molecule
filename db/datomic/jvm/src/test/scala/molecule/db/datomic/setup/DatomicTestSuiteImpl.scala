package molecule.db.datomic.setup

import java.util.UUID.randomUUID
import molecule.base.api.SchemaTransaction
import molecule.core.api.Connection
import molecule.core.marshalling.DatomicPeerProxy
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.schema._
import molecule.db.datomic.facade.{DatomicConn_JVM, DatomicPeer}
import molecule.db.datomic.util.DatomicApiLoader
import moleculeBuildInfo.BuildInfo
import scribe.Logging
import scala.concurrent.Await
import scala.concurrent.duration._

trait DatomicTestSuiteImpl extends DatomicApiLoader with Logging { self: DatomicTestSuite =>

  lazy val isJsPlatform_ = false
  lazy val protocol_     = BuildInfo.datomicProtocol
  lazy val useFree_      = BuildInfo.datomicUseFree

  def inMem[T](
    test: Connection => T,
    schemaTx: SchemaTransaction,
  ): T = {
    val dbUri                    = if (protocol_ == "mem") "" else {
      logger.info(s"Re-creating live database...")
      "localhost:4334/" + randomUUID().toString
    }
    val (schema, nsMap, attrMap, uniqueAttrs) = (
      Seq(
        schemaTx.datomicPartitions,
        schemaTx.datomicSchema,
        schemaTx.datomicAliases
      ),
      schemaTx.nsMap,
      schemaTx.attrMap,
      schemaTx.uniqueAttrs,
    )

    val proxy = DatomicPeerProxy("mem", "", schema, nsMap, attrMap, uniqueAttrs)

    // Block to enable supplying Connection instead of Future[Connection] to tests
    val conn = Await.result(
      DatomicPeer.recreateDbFromEdn(proxy, protocol_, dbUri, useFree_),
      2.seconds
    )
    test(conn)
  }

  def typesImpl[T](test: Connection => T): T = inMem(test, TypesSchema)
  def refsImpl[T](test: Connection => T): T = inMem(test, RefsSchema)
  def uniqueImpl[T](test: Connection => T): T = inMem(test, UniqueSchema)
}
