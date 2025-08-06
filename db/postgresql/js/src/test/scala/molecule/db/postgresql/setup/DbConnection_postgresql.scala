package molecule.db.postgresql.setup

import scala.util.Random
import boopickle.Default.*
import molecule.db.common.api.MetaDb_postgresql
import molecule.db.common.facade.JdbcConn_JS
import molecule.db.common.marshalling.Boopicklers.*
import molecule.db.common.marshalling.JdbcProxy
import molecule.db.common.spi.Conn
import molecule.db.compliance.domains.dsl.Refs.metadb.Refs_postgresql
import molecule.db.compliance.domains.dsl.Segments.metadb.Segments_postgresql
import molecule.db.compliance.domains.dsl.Types.metadb.Types_postgresql
import molecule.db.compliance.domains.dsl.Uniques.metadb.Uniques_postgresql
import molecule.db.compliance.domains.dsl.Validation.metadb.Validation_postgresql
import zio.{ZIO, ZLayer}

object DbConnection_postgresql {

  pickleMetaDb.addConcreteType[Types_postgresql]
  pickleMetaDb.addConcreteType[Refs_postgresql]
  pickleMetaDb.addConcreteType[Uniques_postgresql]
  pickleMetaDb.addConcreteType[Validation_postgresql]
  pickleMetaDb.addConcreteType[Segments_postgresql]

  def getConnection(metaDb: MetaDb_postgresql): JdbcConn_JS = {
    // Since RPC calls run in parallel we need a new connection for
    // each test when using Docker containers.
    // This makes the test suite run slower compared to sequential runs
    // of jvm tests.
    // todo: Find a way to reuse connections for parallel runs.
    val n   = Random.nextInt().abs
    val url = s"jdbc:tc:postgresql:17://localhost:5432/test$n" +
      s"?preparedStatementCacheQueries=0"

    val proxy = JdbcProxy(url, metaDb)
    JdbcConn_JS(proxy, "localhost", 8080)
  }

  def run(test: Conn ?=> Any, metaDb: MetaDb_postgresql): Any = {
    given Conn = getConnection(metaDb)
    test
  }

  def connZLayer(metaDb: MetaDb_postgresql): ZLayer[Any, Throwable, Conn] = {
    ZLayer.scoped(
      ZIO.attemptBlocking {
        getConnection(metaDb)
      }
    )
  }
}
