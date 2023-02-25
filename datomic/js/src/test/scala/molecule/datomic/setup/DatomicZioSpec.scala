package molecule.datomic.setup

import molecule.base.api.SchemaTransaction
import molecule.core.api.Connection
import molecule.core.marshalling.DatomicPeerProxy
import molecule.coreTests.dataModels.core.schema._
import molecule.coreTests.util.TestData
import molecule.datomic.facade.DatomicConn_JS
import moleculeBuildInfo.BuildInfo
import zio.ZLayer
import zio.test.ZIOSpecDefault

trait DatomicZioSpec extends ZIOSpecDefault with TestData {

  lazy val isJsPlatform = false
  lazy val protocol     = BuildInfo.datomicProtocol
  lazy val useFree      = BuildInfo.datomicUseFree

  def inMem(schemaTx: SchemaTransaction): ZLayer[Any, Throwable, Connection] = {
    val proxy = DatomicPeerProxy("mem", "", schemaTx)
    ZLayer.succeed(DatomicConn_JS(proxy, DatomicRpcRequest.moleculeRpcRequest))
  }

  def types = inMem(TypesSchema)
  def refs = inMem(RefsSchema)
  def unique = inMem(UniqueSchema)
}
