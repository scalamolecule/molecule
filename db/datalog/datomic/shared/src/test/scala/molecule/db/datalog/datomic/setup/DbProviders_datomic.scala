package molecule.db.datalog.datomic.setup

import molecule.db.compliance.domains.dsl.Refs.metadb.Refs_MetaDb_datomic
import molecule.db.compliance.domains.dsl.Segments.metadb.Segments_MetaDb_datomic
import molecule.db.compliance.domains.dsl.Types.metadb.Types_MetaDb_datomic
import molecule.db.compliance.domains.dsl.Uniques.metadb.Uniques_MetaDb_datomic
import molecule.db.compliance.domains.dsl.Validation.metadb.Validation_MetaDb_datomic
import molecule.db.compliance.setup.{DbProviders, Platform}
import molecule.db.core.spi.Conn

trait DbProviders_datomic extends DbProviders with DbConnection_datomic with Platform {

  override val database: String = "datomic"

  override def types(test: Conn => Any): Any = run(test, Types_MetaDb_datomic)
  override def refs(test: Conn => Any): Any = run(test, Refs_MetaDb_datomic)
  override def unique(test: Conn => Any): Any = run(test, Uniques_MetaDb_datomic)
  override def validation(test: Conn => Any): Any = run(test, Validation_MetaDb_datomic)
  override def segments(test: Conn => Any): Any = run(test, Segments_MetaDb_datomic)
}