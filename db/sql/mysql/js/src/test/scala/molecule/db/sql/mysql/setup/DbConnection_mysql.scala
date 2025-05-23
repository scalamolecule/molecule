package molecule.db.sql.mysql.setup

import molecule.db.core.api.Schema_mysql
import molecule.db.core.marshalling.JdbcProxy
import molecule.db.core.spi.Conn
import molecule.db.sql.core.facade.JdbcConn_JS
import zio.{ZIO, ZLayer}
import scala.util.Random

object DbConnection_mysql {

  def getConnection(schema: Schema_mysql): JdbcConn_JS = {
    // Since RPC calls run in parallel we need a new connection for
    // each test when using Docker containers.
    // This makes the test suite run slower compared to sequential runs
    // of jvm tests.
    val n   = Random.nextInt().abs
    val url = s"jdbc:tc:mysql:8.1://localhost:3306/test$n" +
      s"?allowMultiQueries=true"

    val proxy = JdbcProxy(url, schema)
    JdbcConn_JS(proxy, "localhost", 8080)
  }

  def run(test: Conn => Any, schema: Schema_mysql): Any = {
    test(getConnection(schema))
  }

  def connZLayer(schema: Schema_mysql): ZLayer[Any, Throwable, Conn] = {
    ZLayer.scoped(
      ZIO.attemptBlocking {
        getConnection(schema)
      }
    )
  }
}
