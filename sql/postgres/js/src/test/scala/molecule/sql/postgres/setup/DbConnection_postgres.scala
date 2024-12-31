package molecule.sql.postgres.setup

import molecule.base.api.Schema_postgres
import molecule.core.marshalling.{JdbcProxy_postgres, RpcRequest}
import molecule.core.spi.Conn
import molecule.coreTests.setup.DbConnection
import molecule.sql.core.facade.JdbcConn_JS
import scala.util.Random

trait DbConnection_postgres extends DbConnection {

  override val platform = "js"


  def getConnection(schema: Schema_postgres): JdbcConn_JS = {
    // Since RPC calls run in parallel we need a new connection for
    // each test when using Docker containers.
    // This makes the test suite run slower compared to sequential runs
    // of jvm tests.
    val n   = Random.nextInt().abs
    val url = s"jdbc:tc:postgresql:16://localhost:5432/test$n" +
      s"?preparedStatementCacheQueries=0"

    val proxy = JdbcProxy_postgres(url, schema)
    JdbcConn_JS(proxy, RpcRequest.request)
  }

  def run(test: Conn => Any, schema: Schema_postgres): Any = {
    test(getConnection(schema))
  }
}
