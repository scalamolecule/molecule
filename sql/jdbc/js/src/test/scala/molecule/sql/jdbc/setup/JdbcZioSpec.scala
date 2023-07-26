package molecule.sql.jdbc.setup

import molecule.base.api.Schema
import molecule.core.marshalling.{JdbcProxy, RpcRequest}
import molecule.core.spi.Conn
import molecule.coreTests.setup.CoreTestZioSpecBase
import molecule.sql.jdbc.facade.JdbcConn_js
import zio.ZLayer
import scala.util.Random


trait JdbcZioSpec extends CoreTestZioSpecBase {

  override val platform = "Jdbc js"

  override def inMem[T](schema: Schema): ZLayer[T, Throwable, Conn] = {
    val url   = s"jdbc:h2:mem:test_database_" + Random.nextInt()
    val proxy = JdbcProxy(
      url,
      schema.sqlSchema("h2"),
      schema.metaSchema,
      schema.nsMap,
      schema.attrMap,
      schema.uniqueAttrs
    )
    ZLayer.succeed(JdbcConn_js(proxy, RpcRequest.request))
  }
}
