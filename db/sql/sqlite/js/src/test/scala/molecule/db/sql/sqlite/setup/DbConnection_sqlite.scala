package molecule.db.sql.sqlite.setup

import boopickle.Default.*
import molecule.db.compliance.domains.dsl.Refs.metadb.Refs_MetaDb_sqlite
import molecule.db.compliance.domains.dsl.Segments.metadb.Segments_MetaDb_sqlite
import molecule.db.compliance.domains.dsl.Types.metadb.Types_MetaDb_sqlite
import molecule.db.compliance.domains.dsl.Uniques.metadb.Uniques_MetaDb_sqlite
import molecule.db.compliance.domains.dsl.Validation.metadb.Validation_MetaDb_sqlite
import molecule.db.compliance.setup.DbConnection
import molecule.db.core.api.MetaDb_sqlite
import molecule.db.core.marshalling.Boopicklers.*
import molecule.db.core.marshalling.JdbcProxy
import molecule.db.core.spi.Conn
import molecule.db.sql.core.facade.JdbcConn_JS
import zio.{ZIO, ZLayer}

trait DbConnection_sqlite extends DbConnection {

  pickleMetaDb.addConcreteType[Types_MetaDb_sqlite]
  pickleMetaDb.addConcreteType[Refs_MetaDb_sqlite]
  pickleMetaDb.addConcreteType[Uniques_MetaDb_sqlite]
  pickleMetaDb.addConcreteType[Validation_MetaDb_sqlite]
  pickleMetaDb.addConcreteType[Segments_MetaDb_sqlite]

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
