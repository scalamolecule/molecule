package molecule.sql.h2.setup

import molecule.base.api.Schema
import molecule.core.marshalling.{JdbcProxy, RpcRequest}
import molecule.core.spi.Conn
import molecule.coreTests.setup.{CoreTestSuite, CoreTestSuiteBase}
import molecule.sql.core.facade.JdbcConn_JS
import scala.util.Random


trait TestSuiteArray_h2 extends CoreTestSuiteBase {

  override val platform              = "js"
  override val database              = "H2"
  override val isJsPlatform: Boolean = true

  override def inMem[T](test: Conn => T, schema: Schema): T = {
    val url   = s"jdbc:h2:mem:test_database_" + Random.nextInt()
    val proxy = JdbcProxy(
      url,
      schema.sqlSchema_h2,
      schema.metaSchema,
      schema.nsMap,
      schema.attrMap,
      schema.uniqueAttrs,
      reserved = schema.sqlReserved_h2
    )
    test(JdbcConn_JS(proxy, RpcRequest.request))
  }
}
