package molecule.db.sql.h2.setup

import molecule.db.compliance.domains.dsl.Refs.metadb.Refs_MetaDb_h2
import molecule.db.compliance.domains.dsl.Segments.metadb.Segments_MetaDb_h2
import molecule.db.compliance.domains.dsl.Types.metadb.Types_MetaDb_h2
import molecule.db.compliance.domains.dsl.Uniques.metadb.Uniques_MetaDb_h2
import molecule.db.compliance.domains.dsl.Validation.metadb.Validation_MetaDb_h2
import molecule.db.compliance.setup.{DbProviders, Platform}
import molecule.db.core.marshalling.Boopicklers.*
import molecule.db.core.spi.Conn

trait DbProviders_h2 extends DbProviders with DbConnection_h2 with Platform {
  override val database: String = "h2"

  override def types(test: Conn => Any): Any = run(test, Types_MetaDb_h2())
  override def refs(test: Conn => Any): Any = run(test, Refs_MetaDb_h2())
  override def unique(test: Conn => Any): Any = run(test, Uniques_MetaDb_h2())
  override def validation(test: Conn => Any): Any = run(test, Validation_MetaDb_h2())
  override def segments(test: Conn => Any): Any = run(test, Segments_MetaDb_h2())
}