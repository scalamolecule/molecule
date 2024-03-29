package molecule.sql.postgres.setup

import molecule.base.api.Schema
import molecule.core.marshalling.{JdbcProxy, RpcRequest}
import molecule.core.spi.Conn
import molecule.coreTests.setup.CoreTestZioSpecBase
import molecule.sql.core.facade.JdbcConn_JS
import zio.ZLayer
import scala.util.Random


trait ZioSpec_postgres extends CoreTestZioSpecBase {

  override val platform = "js"
  override val database = "Postgres"

  override def inMem[T](schema: Schema): ZLayer[T, Throwable, Conn] = {
    val n     = Random.nextInt().abs
    val url   = s"jdbc:tc:postgresql:15://localhost:5432/test$n?preparedStatementCacheQueries=0"
    val proxy = JdbcProxy(
      url,
      schema.sqlSchema_postgres,
      schema.metaSchema,
      schema.nsMap,
      schema.attrMap,
      schema.uniqueAttrs,
      reserved = schema.sqlReserved_postgres,
      useTestContainer = true
    )
    ZLayer.succeed(JdbcConn_JS(proxy, RpcRequest.request))
  }
}
