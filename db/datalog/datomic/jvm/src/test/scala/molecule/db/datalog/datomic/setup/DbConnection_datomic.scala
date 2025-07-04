package molecule.db.datalog.datomic.setup

import molecule.db.compliance.setup.DbConnection
import molecule.db.core.api.MetaDb_datomic
import molecule.db.core.marshalling.DatomicProxy
import molecule.db.core.spi.Conn
import molecule.db.core.util.Executor.*
import molecule.db.datalog.datomic.facade.{DatomicConn_JVM, DatomicPeer}
import zio.{ZIO, ZLayer}
import scala.concurrent.Await
import scala.concurrent.duration.*

trait DbConnection_datomic extends DbConnection {

  def getConnection(metaDb: MetaDb_datomic): DatomicConn_JVM = {
    val proxy = DatomicProxy("mem", "", metaDb)

    // Block to enable supplying Connection instead of Future[Connection] to tests
    Await.result(
      DatomicPeer.recreateDb(proxy),
      10.second
    )
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
