package molecule.db.mariadb.setup

import boopickle.Default.*
import molecule.db.common.api.{MetaDb, MetaDb_mariadb}
import molecule.db.common.facade.JdbcConn_JS
import molecule.db.common.marshalling.Boopicklers.*
import molecule.db.common.marshalling.JdbcProxy
import molecule.db.common.spi.Conn
import molecule.db.compliance.domains.dsl.Refs.metadb.Refs_mariadb
import molecule.db.compliance.domains.dsl.Segments.metadb.Segments_mariadb
import molecule.db.compliance.domains.dsl.Types.metadb.Types_mariadb
import molecule.db.compliance.domains.dsl.Uniques.metadb.Uniques_mariadb
import molecule.db.compliance.domains.dsl.Validation.metadb.Validation_mariadb
import zio.{ZIO, ZLayer}
import scala.util.Random

object DbConnection_mariadb {

  pickleMetaDb.addConcreteType[Types_mariadb]
  pickleMetaDb.addConcreteType[Refs_mariadb]
  pickleMetaDb.addConcreteType[Uniques_mariadb]
  pickleMetaDb.addConcreteType[Validation_mariadb]
  pickleMetaDb.addConcreteType[Segments_mariadb]

  def run(test: Conn ?=> Any, metaDb: MetaDb_mariadb): Any = {
    // Since RPC calls run in parallel we need a new connection for
    // each test when using Docker containers.
    // This makes the test suite run slower compared to sequential runs
    // of jvm tests.
    val n   = Random.nextInt().abs
    val url = s"jdbc:tc:mariadb:latest:///test$n" +
      s"?allowMultiQueries=true" +
      s"&autoReconnect=true" +
      s"&user=root" +
      s"&password="

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
