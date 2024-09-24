package molecule.sql.h2.setup

import molecule.base.api.Schema
import molecule.core.marshalling.{JdbcProxy, RpcRequest}
import molecule.core.spi.Conn
import molecule.coreTests.setup.CoreTestSuiteBase_zio
import molecule.sql.core.facade.JdbcConn_JS
import zio.ZLayer
import scala.util.Random


trait TestSuite_h2_zio extends CoreTestSuiteBase_zio {

  override val platform = "js"
  override val database = "H2"

  override def inMem[T](schema: Schema): ZLayer[T, Throwable, Conn] = {
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
    ZLayer.succeed(JdbcConn_JS(proxy, RpcRequest.request))
  }
}
