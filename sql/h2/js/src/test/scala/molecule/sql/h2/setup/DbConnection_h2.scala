package molecule.sql.h2.setup

import molecule.base.api.Schema_h2
import molecule.core.marshalling.JdbcProxy
import molecule.core.spi.Conn
import molecule.coreTests.setup.DbConnection
import molecule.sql.core.facade.JdbcConn_JS
import sttp.client4.UriContext
import scala.util.Random

trait DbConnection_h2 extends DbConnection {

  def run(test: Conn => Any, schema: Schema_h2): Any = {
    val url   = s"jdbc:h2:mem:test" + Random.nextInt().abs
    val proxy = JdbcProxy(url, schema)
    val conn  = JdbcConn_JS(proxy, uri"http://localhost:8080")
    test(conn)
  }
}
