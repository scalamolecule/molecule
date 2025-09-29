package molecule.db.h2.setup

import scala.util.Random
import boopickle.Default.*
import molecule.db.common.api.*
import molecule.db.common.facade.JdbcConn_JS
import molecule.db.common.marshalling.Boopicklers.*
import molecule.db.common.marshalling.JdbcProxy
import molecule.db.common.spi.Conn
import molecule.db.compliance.marshalling.PickleMetaDbs
import molecule.db.compliance.setup.DbConnection
import zio.{ZIO, ZLayer}

trait DbConnection_h2 extends DbConnection {

  // Add concrete meta database definitions for boopickle
  PickleMetaDbs(pickleMetaDb)

  def run(test: Conn ?=> Any, metaDb: MetaDb_h2): Any = {
    val url   = s"jdbc:h2:mem:test" + Random.nextInt().abs
    val proxy = JdbcProxy(url, metaDb)
    given Conn = JdbcConn_JS(proxy, "localhost", 8080)
    test
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
