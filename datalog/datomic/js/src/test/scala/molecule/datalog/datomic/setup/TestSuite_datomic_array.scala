package molecule.datalog.datomic.setup

import molecule.base.api.Schema
import molecule.core.marshalling.{DatomicProxy, RpcRequest}
import molecule.core.spi.Conn
import molecule.coreTests.setup.{CoreTestSuite, CoreTestSuiteBase}
import molecule.datalog.datomic.facade.DatomicConn_JS
import scala.concurrent.Future


trait TestSuite_datomic_array extends CoreTestSuiteBase {

  override val platform              = "js"
  override val database              = "Datomic"
  override val isJsPlatform: Boolean = true

  override def delay[T](ms: Int)(body: => T): Future[T] = ???

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
