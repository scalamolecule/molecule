package molecule.sql.sqlite.setup

import molecule.base.api.Schema_sqlite
import molecule.core.marshalling.JdbcProxy
import molecule.core.spi.Conn
import molecule.coreTests.setup.DbConnection
import molecule.sql.core.facade.JdbcConn_JS
import sttp.client4.UriContext
import zio.{ZIO, ZLayer}

trait DbConnection_sqlite extends DbConnection {

  def getConnection(schema: Schema_sqlite): JdbcConn_JS = {
    val proxy = JdbcProxy("jdbc:sqlite::memory:", schema)
    JdbcConn_JS(proxy, uri"http://localhost:8080")
  }

  def run(test: Conn => Any, schema: Schema_sqlite): Any = {
    test(getConnection(schema))
  }

  def connZLayer(schema: Schema_sqlite): ZLayer[Any, Throwable, Conn] = {
    ZLayer.scoped(
      ZIO.attemptBlocking {
        getConnection(schema)
      }
    )
  }
}
