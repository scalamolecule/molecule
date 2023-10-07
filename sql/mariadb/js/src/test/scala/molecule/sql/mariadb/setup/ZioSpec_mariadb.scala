package molecule.sql.mariadb.setup

import molecule.base.api.Schema
import molecule.core.marshalling.{JdbcProxy, RpcRequest}
import molecule.core.spi.Conn
import molecule.coreTests.setup.CoreTestZioSpecBase
import molecule.sql.core.facade.JdbcConn_JS
import zio.ZLayer


trait ZioSpec_mariadb extends CoreTestZioSpecBase {

  override val database = "MariaDB"
  override val platform = "js"

  override def inMem[T](schema: Schema): ZLayer[T, Throwable, Conn] = {
    val url   = "jdbc:tc:mariadb:latest:///test?allowMultiQueries=true"
    val proxy = JdbcProxy(
      url,
      schema.sqlSchema_mariadb,
      schema.metaSchema,
      schema.nsMap,
      schema.attrMap,
      schema.uniqueAttrs,
      reserved = schema.sqlReserved_mariadb,
      useTestContainer = true
    )
    ZLayer.succeed(JdbcConn_JS(proxy, RpcRequest.request))
  }
}
