package molecule.sql.sqlite.setup

import molecule.base.api.Schema
import molecule.core.marshalling.{JdbcProxy, RpcRequest}
import molecule.core.spi.Conn
import molecule.coreTests.setup.CoreTestSuite
import molecule.sql.core.facade.JdbcConn_JS


trait TestSuite_sqlite extends CoreTestSuite {

  override val platform = "js"
  override val database = "SQlite"

  override def inMem[T](test: Conn => T, schema: Schema): T = {
    val proxy = JdbcProxy(
      "jdbc:sqlite::memory:",
      schema.sqlSchema_sqlite,
      schema.metaSchema,
      schema.nsMap,
      schema.attrMap,
      schema.uniqueAttrs,
      reserved = schema.sqlReserved_sqlite
    )
    test(JdbcConn_JS(proxy, RpcRequest.request))
  }
}
