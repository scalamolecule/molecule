package molecule.db.datalog.datomic.setup

import molecule.db.compliance.domains.dsl.Refs.metadb.Refs_MetaDb_datomic
import molecule.db.compliance.domains.dsl.Types.metadb.Types_MetaDb_datomic
import molecule.db.compliance.domains.dsl.Uniques.metadb.Uniques_MetaDb_datomic
import molecule.db.compliance.domains.dsl.Validation.metadb.Validation_MetaDb_datomic
import molecule.db.compliance.setup.{DbProviders_zio, Platform}
import molecule.db.core.spi.Conn
import zio.ZLayer

trait DbProviders_datomic_zio extends DbProviders_zio with DbConnection_datomic with Platform {
  override val database: String = "datomic"

  override def types: ZLayer[Any, Throwable, Conn] = connZLayer(Types_MetaDb_datomic())
  override def refs: ZLayer[Any, Throwable, Conn] = connZLayer(Refs_MetaDb_datomic())
  override def unique: ZLayer[Any, Throwable, Conn] = connZLayer(Uniques_MetaDb_datomic())
  override def validation: ZLayer[Any, Throwable, Conn] = connZLayer(Validation_MetaDb_datomic())
}