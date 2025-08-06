package molecule.db.mysql.setup

import scala.util.Random
import boopickle.Default.*
import molecule.db.common.api.MetaDb_mysql
import molecule.db.common.facade.JdbcConn_JS
import molecule.db.common.marshalling.Boopicklers.*
import molecule.db.common.marshalling.JdbcProxy
import molecule.db.common.spi.Conn
import molecule.db.compliance.domains.dsl.Refs.metadb.Refs_mysql
import molecule.db.compliance.domains.dsl.Segments.metadb.Segments_mysql
import molecule.db.compliance.domains.dsl.Types.metadb.Types_mysql
import molecule.db.compliance.domains.dsl.Uniques.metadb.Uniques_mysql
import molecule.db.compliance.domains.dsl.Validation.metadb.Validation_mysql
import zio.{ZIO, ZLayer}

object DbConnection_mysql {

  pickleMetaDb.addConcreteType[Types_mysql]
  pickleMetaDb.addConcreteType[Refs_mysql]
  pickleMetaDb.addConcreteType[Uniques_mysql]
  pickleMetaDb.addConcreteType[Validation_mysql]
  pickleMetaDb.addConcreteType[Segments_mysql]

  def getConnection(metaDb: MetaDb_mysql): JdbcConn_JS = {
    // Since RPC calls run in parallel we need a new connection for
    // each test when using Docker containers.
    // This makes the test suite run slower compared to sequential runs
    // of jvm tests.
    val n   = Random.nextInt().abs
    val url = s"jdbc:tc:mysql:8.1://localhost:3306/test$n" +
      s"?allowMultiQueries=true"

    val proxy = JdbcProxy(url, metaDb)
    JdbcConn_JS(proxy, "localhost", 8080)
  }

  def run(test: Conn ?=> Any, metaDb: MetaDb_mysql): Any = {
    given Conn = getConnection(metaDb)
    test
  }

  def connZLayer(metaDb: MetaDb_mysql): ZLayer[Any, Throwable, Conn] = {
    ZLayer.scoped(
      ZIO.attemptBlocking {
        getConnection(metaDb)
      }
    )
  }
}
