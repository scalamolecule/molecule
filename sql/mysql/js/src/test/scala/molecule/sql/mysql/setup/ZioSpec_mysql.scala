package molecule.sql.mysql.setup

import molecule.base.api.Schema
import molecule.core.marshalling.{JdbcProxy, RpcRequest}
import molecule.core.spi.Conn
import molecule.coreTests.setup.CoreTestZioSpecBase
import molecule.sql.core.facade.JdbcConn_JS
import zio.ZLayer


trait ZioSpec_mysql extends CoreTestZioSpecBase {

  override val platform = "Mysql js"

  override def inMem[T](schema: Schema): ZLayer[T, Throwable, Conn] = {
    val url   = "jdbc:tc:mysql:8.1://localhost:3306/test?allowMultiQueries=true"
    val proxy = JdbcProxy(
      url,
      schema.sqlSchema_mysql,
      schema.metaSchema,
      schema.nsMap,
      schema.attrMap,
      schema.uniqueAttrs,
      reserved = schema.sqlReserved_mysql,
      useTestContainer = true
    )
    ZLayer.succeed(JdbcConn_JS(proxy, RpcRequest.request))
  }
}
