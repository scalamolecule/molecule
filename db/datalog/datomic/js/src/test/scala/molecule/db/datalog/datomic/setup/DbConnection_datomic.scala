package molecule.db.datalog.datomic.setup

import molecule.db.compliance.setup.DbConnection
import molecule.db.core.api.MetaDb_datomic
import molecule.db.core.marshalling.DatomicProxy
import molecule.db.core.spi.Conn
import molecule.db.datalog.datomic.facade.DatomicConn_JS
import zio.{ZIO, ZLayer}

trait DbConnection_datomic extends DbConnection {

  def getConnection(metaDb: MetaDb_datomic): DatomicConn_JS = {
    val proxy = DatomicProxy("mem", "", metaDb)
    DatomicConn_JS(proxy, "localhost", 8080)
  }

  def run(test: Conn => Any, metaDb: MetaDb_datomic): Any = {
    test(getConnection(metaDb))
  }

  def connZLayer(metaDb: MetaDb_datomic): ZLayer[Any, Throwable, Conn] = {
    ZLayer.scoped(
      ZIO.attemptBlocking {
        getConnection(metaDb)
      }
    )
  }
}
