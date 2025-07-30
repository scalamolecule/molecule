package molecule.db.sqlite.setup

import boopickle.Default.*
import molecule.db.common.api.MetaDb_sqlite
import molecule.db.common.facade.JdbcConn_JS
import molecule.db.common.marshalling.Boopicklers.*
import molecule.db.common.marshalling.JdbcProxy
import molecule.db.common.spi.Conn
import molecule.db.compliance.domains.dsl.Refs.metadb.Refs_sqlite
import molecule.db.compliance.domains.dsl.Segments.metadb.Segments_sqlite
import molecule.db.compliance.domains.dsl.Types.metadb.Types_sqlite
import molecule.db.compliance.domains.dsl.Uniques.metadb.Uniques_sqlite
import molecule.db.compliance.domains.dsl.Validation.metadb.Validation_sqlite
import molecule.db.compliance.setup.DbConnection
import zio.{ZIO, ZLayer}

trait DbConnection_sqlite extends DbConnection {

  pickleMetaDb.addConcreteType[Types_sqlite]
  pickleMetaDb.addConcreteType[Refs_sqlite]
  pickleMetaDb.addConcreteType[Uniques_sqlite]
  pickleMetaDb.addConcreteType[Validation_sqlite]
  pickleMetaDb.addConcreteType[Segments_sqlite]

  def getConnection(metaDb: MetaDb_sqlite): JdbcConn_JS = {
    val proxy = JdbcProxy("jdbc:sqlite::memory:", metaDb)
    JdbcConn_JS(proxy, "localhost", 8080)
  }

  def run(test: Conn ?=> Any, metaDb: MetaDb_sqlite): Any = {
    given Conn = getConnection(metaDb)
    test
  }

  def run4(test: Conn ?=> Any, metaDb: MetaDb_sqlite): Any = ???


  def connZLayer(metaDb: MetaDb_sqlite): ZLayer[Any, Throwable, Conn] = {
    ZLayer.scoped(
      ZIO.attemptBlocking {
        getConnection(metaDb)
      }
    )
  }
}
