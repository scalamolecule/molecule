package molecule.sql.sqlite.setup

import molecule.base.api.Schema
import molecule.core.marshalling.{JdbcProxy, RpcRequest}
import molecule.core.spi.Conn
import molecule.coreTests.setup.CoreTestZioSpecBase
import molecule.sql.core.facade.JdbcConn_JS
import zio.ZLayer
import scala.util.Random


trait ZioSpec_sqlite extends CoreTestZioSpecBase {

  override val platform = "js"
  override val database = "Sqlite"

  override def inMem[T](schema: Schema): ZLayer[T, Throwable, Conn] = {
    val url   = s"jdbc:sqlite:mem:test_database_" + Random.nextInt()
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
