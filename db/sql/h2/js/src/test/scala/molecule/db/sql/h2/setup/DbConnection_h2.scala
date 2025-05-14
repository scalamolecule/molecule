package molecule.db.sql.h2.setup

import molecule.db.core.api.{Schema, Schema_h2}
import molecule.db.compliance.setup.DbConnection
import molecule.db.core.marshalling.JdbcProxy
import molecule.db.core.spi.Conn
import molecule.db.sql.core.facade.JdbcConn_JS
import molecule.db.sql.h2
import sttp.client4.UriContext
import zio.{ZIO, ZLayer}
import scala.util.Random

trait DbConnection_h2 extends DbConnection {

  def run(test: Conn => Any, schema: Schema_h2): Any = {
    val url   = s"jdbc:h2:mem:test" + Random.nextInt().abs
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
