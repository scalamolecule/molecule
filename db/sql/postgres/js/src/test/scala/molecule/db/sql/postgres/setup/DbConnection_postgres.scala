package molecule.db.sql.postgres.setup

import java.util.UUID
import molecule.base.api.Schema_postgres
import molecule.core.marshalling.JdbcProxy
import molecule.core.spi.Conn
import molecule.db.sql.core.facade.JdbcConn_JS
import molecule.db.sql.postgres
import sttp.client4.UriContext
import zio.{ZIO, ZLayer}
import scala.util.Random
import scala.util.Using.Manager

object DbConnection_postgres {

  def getConnection(schema: Schema_postgres): JdbcConn_JS = {
    // Since RPC calls run in parallel we need a new connection for
    // each test when using Docker containers.
    // This makes the test suite run slower compared to sequential runs
    // of jvm tests.
    // todo: Find a way to reuse connections for parallel runs.
    val n   = Random.nextInt().abs
    val url = s"jdbc:tc:postgresql:17://localhost:5432/test$n" +
      s"?preparedStatementCacheQueries=0"

    val proxy = JdbcProxy(url, schema)
    JdbcConn_JS(proxy, "localhost", 8080)
  }

  def run(test: Conn => Any, schema: Schema_postgres): Any = {
    test(getConnection(schema))
  }

  def connZLayer(schema: Schema_postgres): ZLayer[Any, Throwable, Conn] = {
    ZLayer.scoped(
      ZIO.attemptBlocking {
        getConnection(schema)
      }
    )
  }
}
