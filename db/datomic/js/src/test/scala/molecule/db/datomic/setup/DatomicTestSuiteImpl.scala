package molecule.db.datomic.setup

import molecule.base.api.SchemaTransaction
import molecule.core.api.Connection
import molecule.core.marshalling.DatomicPeerProxy
import molecule.coreTests.dataModels.core.schema._
import molecule.db.datomic.facade.DatomicConn_JS
import moleculeBuildInfo.BuildInfo


trait DatomicTestSuiteImpl { self: DatomicTestSuite =>

  lazy val isJsPlatform_ = true
  lazy val protocol_     = BuildInfo.datomicProtocol
  lazy val useFree_      = BuildInfo.datomicUseFree

  def inMem[T](
    test: Connection => T,
    schemaTx: SchemaTransaction
  ): T = {
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

    val proxy = DatomicPeerProxy(
      "mem", "", schema, nsMap, attrMap, uniqueAttrs, isFreeVersion = useFree_
    )
    test(DatomicConn_JS(proxy, DatomicRpcRequest.moleculeRpcRequest))
  }

  def typesImpl[T](test: Connection => T): T = inMem(test, TypesSchema)
  def refsImpl[T](test: Connection => T): T = inMem(test, RefsSchema)
  def uniqueImpl[T](test: Connection => T): T = inMem(test, UniqueSchema)
}
