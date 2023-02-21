package molecule.db.datomic.setup

import java.util.UUID.randomUUID
import molecule.base.api.SchemaTransaction
import molecule.core.api.Connection
import molecule.core.marshalling.DatomicPeerProxy
import molecule.coreTests.dataModels.core.schema._
import molecule.coreTests.util.TestData
import moleculeBuildInfo.BuildInfo
import zio.test.ZIOSpecDefault
import zio.{ZIO, ZLayer}

trait DatomicZioSpec extends ZIOSpecDefault with TestData {

  lazy val isJsPlatform = false
  lazy val protocol     = BuildInfo.datomicProtocol
  lazy val useFree      = BuildInfo.datomicUseFree

  //  def inMem(schemaTx: SchemaTransaction): ZLayer[Any, Throwable, DatomicConnectionPool] = {
  def inMem(schemaTx: SchemaTransaction): ZLayer[Any, Throwable, Connection] = {
    val dbIdentifier                          = if (protocol == "mem") "" else {
      println(s"Re-creating live database...")
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

    //    DatomicConnectionPool.inMem(proxy, protocol, dbIdentifier, useFree).orDie

//    ZLayer.scoped(
//      ZIO.fromFuture(
//        _ => DatomicPeer.recreateDbFromEdn(proxy, protocol, dbIdentifier, useFree)
//      )
//    )
    ???
  }

  def types = inMem(TypesSchema)
  //  def refsImpl[T](test: Connection => T): T = inMem(test, RefsSchema)
  //  def uniqueImpl[T](test: Connection => T): T = inMem(test, UniqueSchema)
}
