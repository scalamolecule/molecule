package molecule.db.sql.postgres.setup

import molecule.db.core.api.MetaDb_postgres
import molecule.db.core.marshalling.JdbcProxy
import molecule.db.core.spi.Conn
import molecule.db.sql.core.facade.JdbcConn_JS
import zio.{ZIO, ZLayer}
import scala.util.Random

object DbConnection_postgres {

  def getConnection(metaDb: MetaDb_postgres): JdbcConn_JS = {
    // Since RPC calls run in parallel we need a new connection for
    // each test when using Docker containers.
    // This makes the test suite run slower compared to sequential runs
    // of jvm tests.
    // todo: Find a way to reuse connections for parallel runs.
    val n   = Random.nextInt().abs
    val url = s"jdbc:tc:postgresql:17://localhost:5432/test$n" +
      s"?preparedStatementCacheQueries=0"

    val proxy = JdbcProxy(url, metaDb)
    JdbcConn_JS(proxy, "localhost", 8080)
  }

  def run(test: Conn => Any, metaDb: MetaDb_postgres): Any = {
    test(getConnection(metaDb))
  }

  def connZLayer(metaDb: MetaDb_postgres): ZLayer[Any, Throwable, Conn] = {
    ZLayer.scoped(
      ZIO.attemptBlocking {
        getConnection(metaDb)
      }
    )
  }
}
