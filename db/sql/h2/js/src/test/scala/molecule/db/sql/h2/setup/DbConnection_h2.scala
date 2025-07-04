package molecule.db.sql.h2.setup

import molecule.db.compliance.setup.DbConnection
import molecule.db.core.api.*
import molecule.db.core.marshalling.JdbcProxy
import molecule.db.core.spi.Conn
import molecule.db.sql.core.facade.JdbcConn_JS
import zio.{ZIO, ZLayer}
import scala.util.Random

trait DbConnection_h2 extends DbConnection {

  def run(test: Conn => Any, metaDb: MetaDb_h2): Any = {
    val url   = s"jdbc:h2:mem:test" + Random.nextInt().abs
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
