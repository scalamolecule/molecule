package molecule.sql.sqlite.setup

import molecule.base.api.Schema_sqlite
import molecule.core.marshalling.JdbcProxy
import molecule.core.spi.Conn
import molecule.coreTests.setup.DbConnection
import molecule.sql.core.facade.JdbcConn_JS
import sttp.client4.UriContext

trait DbConnection_sqlite extends DbConnection {

  def run(test: Conn => Any, schema: Schema_sqlite): Any = {
    val proxy = JdbcProxy("jdbc:sqlite::memory:", schema)
    test(JdbcConn_JS(proxy, uri"http://localhost:8080"))
  }
}
