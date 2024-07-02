package molecule.sql.mysql.setup

import molecule.base.api.Schema
import molecule.core.marshalling.{JdbcProxy, RpcRequest}
import molecule.sql.core.facade.JdbcConn_JS
import scala.util.Random

trait ConnectionJS_mysql {

  def getConnection(schema: Schema): JdbcConn_JS = {
    // Since RPC calls run in parallel we need a new connection for
    // each test when using Docker containers.
    // This makes the test suite run slower compared to sequential runs
    // of jvm tests.
    val n     = Random.nextInt().abs
    val url   = s"jdbc:tc:mysql:8.1://localhost:3306/test$n" +
      s"?allowMultiQueries=true"
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
    JdbcConn_JS(proxy, RpcRequest.request)
  }
}
