package molecule.db.sql.mariadb.setup

import molecule.db.base.api.{Schema, Schema_mariadb}
import molecule.db.compliance.setup.DbConnection
import molecule.db.core.marshalling.JdbcProxy
import molecule.db.core.spi.Conn
import molecule.db.sql.core.facade.JdbcConn_JS
import molecule.db.sql.mariadb
import sttp.client4.UriContext
import zio.{ZIO, ZLayer}
import scala.util.Random

//trait DbConnection_mariadb extends DbConnection {
object DbConnection_mariadb {

  def run(test: Conn => Any, schema: Schema_mariadb): Any = {
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

    val proxy = JdbcProxy(url, schema)
    val conn  = JdbcConn_JS(proxy, "localhost", 8080)
    test(conn)
  }

  def connZLayer(schema: Schema): ZLayer[Any, Throwable, Conn] = {
    val url = "jdbc:h2:mem:test" + Random.nextInt().abs
    ZLayer.scoped(
      ZIO.attemptBlocking {
        val proxy = JdbcProxy(url, schema)
        JdbcConn_JS(proxy, "localhost", 8080)
      }
    )
  }
}
