package molecule.sql.sqlite.setup

import molecule.base.api.Schema
import molecule.core.marshalling.{JdbcProxy, RpcRequest}
import molecule.core.spi.Conn
import molecule.coreTests.setup.CoreTestSuiteBase_zio
import molecule.sql.core.facade.JdbcConn_JS
import zio.ZLayer
import scala.util.Random


trait TestSuite_sqlite_zio extends CoreTestSuiteBase_zio {

  override val platform = "js"
  override val database = "SQlite"

  override def inMem[T](schema: Schema): ZLayer[T, Throwable, Conn] = {
    val url   = "jdbc:sqlite::memory:"
    val proxy = JdbcProxy(
      url,
      schema.sqlSchema_sqlite,
      schema.metaSchema,
      schema.nsMap,
      schema.attrMap,
      schema.uniqueAttrs,
      reserved = schema.sqlReserved_sqlite
    )
    ZLayer.succeed(JdbcConn_JS(proxy, RpcRequest.request))
  }
}
