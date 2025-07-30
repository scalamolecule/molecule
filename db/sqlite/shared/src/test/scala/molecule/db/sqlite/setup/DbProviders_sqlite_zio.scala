package molecule.db.sqlite.setup

import molecule.db
import molecule.db.common.spi.Conn
import molecule.db.compliance.domains.dsl.Refs.metadb.Refs_sqlite
import molecule.db.compliance.domains.dsl.Types.metadb.Types_sqlite
import molecule.db.compliance.domains.dsl.Uniques.metadb.Uniques_sqlite
import molecule.db.compliance.domains.dsl.Validation.metadb.Validation_sqlite
import molecule.db.compliance.setup.{DbProviders_zio, Platform}
import molecule.db.sqlite.setup.DbConnection_sqlite
import zio.ZLayer

trait DbProviders_sqlite_zio extends DbProviders_zio with DbConnection_sqlite with Platform {
  override val database: String = "sqlite"

  override def types: ZLayer[Any, Throwable, Conn] = connZLayer(Types_sqlite())
  override def refs: ZLayer[Any, Throwable, Conn] = connZLayer(Refs_sqlite())
  override def unique: ZLayer[Any, Throwable, Conn] = connZLayer(Uniques_sqlite())
  override def validation: ZLayer[Any, Throwable, Conn] = connZLayer(Validation_sqlite())
}