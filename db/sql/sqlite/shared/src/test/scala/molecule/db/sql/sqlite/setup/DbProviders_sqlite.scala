package molecule.db.sql.sqlite.setup

import molecule.db.compliance.domains.dsl.Refs.metadb.Refs_MetaDb_sqlite
import molecule.db.compliance.domains.dsl.Segments.metadb.Segments_MetaDb_sqlite
import molecule.db.compliance.domains.dsl.Types.metadb.*
import molecule.db.compliance.domains.dsl.Uniques.metadb.Uniques_MetaDb_sqlite
import molecule.db.compliance.domains.dsl.Validation.metadb.Validation_MetaDb_sqlite
import molecule.db.compliance.setup.{DbProviders, Platform}
import molecule.db.core.marshalling.Boopicklers.pickleMetaDb
import molecule.db.core.spi.Conn

trait DbProviders_sqlite extends DbProviders with DbConnection_sqlite with Platform {
  override val database: String = "sqlite"

  override def types(test: Conn => Any): Any = run(test, Types_MetaDb_sqlite())
  override def refs(test: Conn => Any): Any = run(test, Refs_MetaDb_sqlite())
  override def unique(test: Conn => Any): Any = run(test, Uniques_MetaDb_sqlite())
  override def validation(test: Conn => Any): Any = run(test, Validation_MetaDb_sqlite())
  override def segments(test: Conn => Any): Any = run(test, Segments_MetaDb_sqlite())
}