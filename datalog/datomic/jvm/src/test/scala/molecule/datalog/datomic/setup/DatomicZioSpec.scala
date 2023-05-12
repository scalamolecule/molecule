package molecule.datalog.datomic.setup

import java.util.UUID.randomUUID
import molecule.base.api.Schema
import molecule.core.api.Connection
import molecule.core.marshalling.DatomicPeerProxy
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.schema._
import molecule.coreTests.util.TestData
import molecule.datalog.datomic.facade.DatomicPeer
import moleculeBuildInfo.BuildInfo
import zio.test.ZIOSpecDefault
import zio.{ZIO, ZLayer}

trait DatomicZioSpec extends ZIOSpecDefault with TestData {

  lazy val isJsPlatform = false
  lazy val protocol     = BuildInfo.datomicProtocol
  lazy val useFree      = BuildInfo.datomicUseFree

  def inMem(schemaTx: Schema): ZLayer[Any, Throwable, Connection] = {
    val dbIdentifier = if (protocol == "mem") "" else {
      println(s"Re-creating live database...")
      "localhost:4334/" + randomUUID().toString
    }
    val proxy        = DatomicPeerProxy("mem", "", schemaTx)
    ZLayer.scoped(
      ZIO.fromFuture(
        _ => DatomicPeer.recreateDbFromEdn(proxy, protocol, dbIdentifier, useFree)
      )
    )
  }

  def types = inMem(TypesSchema)
  def refs = inMem(RefsSchema)
  def unique = inMem(UniqueSchema)
  def validation = inMem(ValidationSchema)


  def delay[T](ms: Int)(body: => T): ZIO[Any, Nothing, T] = ZIO.succeed {
    Thread.sleep(ms)
    body
  }
}
