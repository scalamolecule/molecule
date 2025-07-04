package molecule.db.sql.sqlite.setup

import molecule.db.compliance.setup.DbConnection
import molecule.db.core.api.MetaDb_sqlite
import molecule.db.core.marshalling.JdbcProxy
import molecule.db.core.spi.Conn
import molecule.db.sql.core.facade.JdbcConn_JS
import zio.{ZIO, ZLayer}

trait DbConnection_sqlite extends DbConnection {

  def getConnection(metaDb: MetaDb_sqlite): JdbcConn_JS = {
    val proxy = JdbcProxy("jdbc:sqlite::memory:", metaDb)
    JdbcConn_JS(proxy, "localhost", 8080)
  }

  def run(test: Conn => Any, metaDb: MetaDb_sqlite): Any = {
    test(getConnection(metaDb))
  }

  def connZLayer(metaDb: MetaDb_sqlite): ZLayer[Any, Throwable, Conn] = {
    ZLayer.scoped(
      ZIO.attemptBlocking {
        getConnection(metaDb)
      }
    )
  }
}
