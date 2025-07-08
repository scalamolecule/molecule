package molecule.db.h2.setup

import molecule.db
import molecule.db.common.spi.Conn
import molecule.db.compliance.domains.dsl.Refs.metadb.Refs_MetaDb_h2
import molecule.db.compliance.domains.dsl.Types.metadb.Types_MetaDb_h2
import molecule.db.compliance.domains.dsl.Uniques.metadb.Uniques_MetaDb_h2
import molecule.db.compliance.domains.dsl.Validation.metadb.Validation_MetaDb_h2
import molecule.db.compliance.setup.{DbProviders_zio, Platform}
import molecule.db.h2.setup.DbConnection_h2
import zio.ZLayer

trait DbProviders_h2_zio extends DbProviders_zio with DbConnection_h2 with Platform {
  override val database: String = "h2"

  override def types: ZLayer[Any, Throwable, Conn] = connZLayer(Types_MetaDb_h2())
  override def refs: ZLayer[Any, Throwable, Conn] = connZLayer(Refs_MetaDb_h2())
  override def unique: ZLayer[Any, Throwable, Conn] = connZLayer(Uniques_MetaDb_h2())
  override def validation: ZLayer[Any, Throwable, Conn] = connZLayer(Validation_MetaDb_h2())
}