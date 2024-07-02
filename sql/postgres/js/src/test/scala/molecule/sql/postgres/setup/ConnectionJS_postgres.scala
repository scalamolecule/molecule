package molecule.sql.postgres.setup

import molecule.base.api.Schema
import molecule.core.marshalling.{JdbcProxy, RpcRequest}
import molecule.sql.core.facade.JdbcConn_JS
import scala.util.Random

trait ConnectionJS_postgres {

  def getConnection(schema: Schema): JdbcConn_JS = {
    // Since RPC calls run in parallel we need a new connection for
    // each test when using Docker containers.
    // This makes the test suite run slower compared to sequential runs
    // of jvm tests.
    val n     = Random.nextInt().abs
    val url   = s"jdbc:tc:postgresql:16://localhost:5432/test$n" +
      s"?preparedStatementCacheQueries=0"
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
    JdbcConn_JS(proxy, RpcRequest.request)
  }
}
