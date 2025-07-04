package molecule.db.sql.postgres.setup

import molecule.db.compliance.domains.dsl.Refs.metadb.Refs_MetaDb_postgres
import molecule.db.compliance.domains.dsl.Segments.metadb.Segments_MetaDb_postgres
import molecule.db.compliance.domains.dsl.Types.metadb.Types_MetaDb_postgres
import molecule.db.compliance.domains.dsl.Uniques.metadb.Uniques_MetaDb_postgres
import molecule.db.compliance.domains.dsl.Validation.metadb.Validation_MetaDb_postgres
import molecule.db.compliance.setup.{DbConnection, DbProviders, Platform}
import molecule.db.core.spi.Conn

trait DbProviders_postgres extends DbProviders with DbConnection with Platform {

  override val database: String = "postgres"

  private val db = DbConnection_postgres

  override def types(test: Conn => Any): Any = db.run(test, Types_MetaDb_postgres)
  override def refs(test: Conn => Any): Any = db.run(test, Refs_MetaDb_postgres)
  override def unique(test: Conn => Any): Any = db.run(test, Uniques_MetaDb_postgres)
  override def validation(test: Conn => Any): Any = db.run(test, Validation_MetaDb_postgres)
  override def segments(test: Conn => Any): Any = db.run(test, Segments_MetaDb_postgres)
}