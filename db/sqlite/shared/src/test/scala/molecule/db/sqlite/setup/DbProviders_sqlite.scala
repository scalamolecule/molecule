package molecule.db.sqlite.setup

import molecule.db.common.spi.Conn
import molecule.db.compliance.domains.dsl.Refs.metadb.Refs_sqlite
import molecule.db.compliance.domains.dsl.Segments.metadb.Segments_sqlite
import molecule.db.compliance.domains.dsl.Types.metadb.*
import molecule.db.compliance.domains.dsl.Uniques.metadb.Uniques_sqlite
import molecule.db.compliance.domains.dsl.Validation.metadb.Validation_sqlite
import molecule.db.compliance.setup.{DbProviders, Platform}
import molecule.db.sqlite.setup.DbConnection_sqlite


trait DbProviders_sqlite extends DbProviders with DbConnection_sqlite with Platform {
  override val database: String = "sqlite"

  override def types(test: Conn ?=> Any): Any = run(test, Types_sqlite())
  override def refs(test: Conn ?=> Any): Any = run(test, Refs_sqlite())
  override def unique(test: Conn ?=> Any): Any = run(test, Uniques_sqlite())
  override def validation(test: Conn ?=> Any): Any = run(test, Validation_sqlite())
  override def segments(test: Conn ?=> Any): Any = run(test, Segments_sqlite())
}