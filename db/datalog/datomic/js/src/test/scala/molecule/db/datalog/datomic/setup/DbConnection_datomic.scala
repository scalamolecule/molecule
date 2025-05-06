package molecule.db.datalog.datomic.setup

import molecule.base.api.Schema_datomic
import molecule.core.marshalling.DatomicProxy
import molecule.core.spi.Conn
import molecule.coreTests.setup.DbConnection
import molecule.db.datalog.datomic.facade.DatomicConn_JS
import zio.{ZIO, ZLayer}

trait DbConnection_datomic extends DbConnection {

  def getConnection(schema: Schema_datomic): DatomicConn_JS = {
    val proxy = DatomicProxy("mem", "", schema)
    DatomicConn_JS(proxy, "localhost", 8080)
  }

  def run(test: Conn => Any, schema: Schema_datomic): Any = {
    test(getConnection(schema))
  }

  def connZLayer(schema: Schema_datomic): ZLayer[Any, Throwable, Conn] = {
    ZLayer.scoped(
      ZIO.attemptBlocking {
        getConnection(schema)
      }
    )
  }
}
