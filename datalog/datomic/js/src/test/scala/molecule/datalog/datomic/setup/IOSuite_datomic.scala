package molecule.datalog.datomic.setup

import molecule.base.api.Schema
import molecule.core.marshalling.{DatomicProxy, RpcRequest}
import molecule.core.spi.Conn
import molecule.coreTests.setup.CoreTestIOSuiteBase
import molecule.datalog.datomic.facade.DatomicConn_JS


trait IOSuite_datomic extends CoreTestIOSuiteBase {

  override val platform = "js"
  override val database = "Datomic"

  override def inMem[T](test: Conn => T, schema: Schema): T = {
    val proxy = DatomicProxy(
      "mem", "",
      schema.datomicPartitions,
      schema.datomicSchema,
      schema.datomicAliases,
      schema.metaSchema,
      schema.nsMap,
      schema.attrMap,
      schema.uniqueAttrs,
    )

    test(DatomicConn_JS(proxy, RpcRequest.request))

  }
}
