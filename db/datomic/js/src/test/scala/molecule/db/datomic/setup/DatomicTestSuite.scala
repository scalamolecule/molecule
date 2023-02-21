package molecule.db.datomic.setup

import molecule.base.api.SchemaTransaction
import molecule.core.api.Connection
import molecule.core.marshalling.DatomicPeerProxy
import molecule.coreTests.dataModels.core.schema._
import molecule.db.datomic.facade.DatomicConn_JS
import moleculeBuildInfo.BuildInfo


trait DatomicTestSuite extends DatomicTestSuiteBase {

  lazy val isJsPlatform = true
  lazy val protocol     = BuildInfo.datomicProtocol
  lazy val useFree      = BuildInfo.datomicUseFree

  def inMem[T](test: Connection => T, schemaTx: SchemaTransaction): T = {
    val proxy = DatomicPeerProxy("mem", "", schemaTx)
    test(DatomicConn_JS(proxy, DatomicRpcRequest.moleculeRpcRequest))
  }

  def types[T](test: Connection => T): T = inMem(test, TypesSchema)
  def refs[T](test: Connection => T): T = inMem(test, RefsSchema)
  def unique[T](test: Connection => T): T = inMem(test, UniqueSchema)
}
