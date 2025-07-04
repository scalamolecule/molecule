package molecule.db.sql.mariadb.setup

import molecule.db.core.api.{MetaDb, MetaDb_mariadb}
import molecule.db.core.marshalling.JdbcProxy
import molecule.db.core.spi.Conn
import molecule.db.sql.core.facade.JdbcConn_JS
import zio.{ZIO, ZLayer}
import scala.util.Random

object DbConnection_mariadb {

  def run(test: Conn => Any, metaDb: MetaDb_mariadb): Any = {
    // Since RPC calls run in parallel we need a new connection for
    // each test when using Docker containers.
    // This makes the test suite run slower compared to sequential runs
    // of jvm tests.
    val n   = Random.nextInt().abs
    val url = s"jdbc:tc:mariadb:latest:///test$n" +
      s"?allowMultiQueries=true" +
      s"&autoReconnect=true" +
      s"&user=root" +
      s"&password="

    val proxy = JdbcProxy(url, metaDb)
    val conn  = JdbcConn_JS(proxy, "localhost", 8080)
    test(conn)
  }

  def connZLayer(metaDb: MetaDb): ZLayer[Any, Throwable, Conn] = {
    val url = "jdbc:h2:mem:test" + Random.nextInt().abs
    ZLayer.scoped(
      ZIO.attemptBlocking {
        val proxy = JdbcProxy(url, metaDb)
        JdbcConn_JS(proxy, "localhost", 8080)
      }
    )
  }
}
