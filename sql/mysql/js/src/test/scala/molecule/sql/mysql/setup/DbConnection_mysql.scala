package molecule.sql.mysql.setup

import molecule.base.api.Schema_mysql
import molecule.core.marshalling.{JdbcProxy_mysql, RpcRequest}
import molecule.core.spi.Conn
import molecule.coreTests.setup.DbConnection
import molecule.sql.core.facade.JdbcConn_JS
import scala.util.Random

//trait DbConnection_mysql extends DbConnection {
object DbConnection_mysql {

  def getConnection(schema: Schema_mysql): JdbcConn_JS = {
    // Since RPC calls run in parallel we need a new connection for
    // each test when using Docker containers.
    // This makes the test suite run slower compared to sequential runs
    // of jvm tests.
    val n   = Random.nextInt().abs
    val url = s"jdbc:tc:mysql:8.1://localhost:3306/test$n" +
      s"?allowMultiQueries=true"

    val proxy = JdbcProxy_mysql(url, schema)
    JdbcConn_JS(proxy, RpcRequest.request)
  }

  def run(test: Conn => Any, schema: Schema_mysql): Any = {
    test(getConnection(schema))
  }
}
