package molecule.db.datalog.datomic.setup

import molecule.db.compliance.domains.dsl.Refs.metadb.Refs_MetaDb_datomic
import molecule.db.compliance.domains.dsl.Segments.metadb.Segments_MetaDb_datomic
import molecule.db.compliance.domains.dsl.Types.metadb.Types_MetaDb_datomic
import molecule.db.compliance.domains.dsl.Uniques.metadb.Uniques_MetaDb_datomic
import molecule.db.compliance.domains.dsl.Validation.metadb.Validation_MetaDb_datomic
import molecule.db.compliance.setup.DbConnection
import molecule.db.core.api.MetaDb_datomic
import molecule.db.core.marshalling.Boopicklers.pickleMetaDb
import molecule.db.core.marshalling.DatomicProxy
import molecule.db.core.spi.Conn
import molecule.db.datalog.datomic.facade.DatomicConn_JS
import zio.{ZIO, ZLayer}

trait DbConnection_datomic extends DbConnection {

  pickleMetaDb.addConcreteType[Types_MetaDb_datomic]
  pickleMetaDb.addConcreteType[Refs_MetaDb_datomic]
  pickleMetaDb.addConcreteType[Uniques_MetaDb_datomic]
  pickleMetaDb.addConcreteType[Validation_MetaDb_datomic]
  pickleMetaDb.addConcreteType[Segments_MetaDb_datomic]

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
