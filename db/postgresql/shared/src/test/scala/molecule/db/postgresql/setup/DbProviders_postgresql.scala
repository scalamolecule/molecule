package molecule.db.postgresql.setup

import molecule.db.common.spi.Conn
import molecule.db.compliance.domains.dsl.Refs.metadb.Refs_MetaDb_postgresql
import molecule.db.compliance.domains.dsl.Segments.metadb.Segments_MetaDb_postgresql
import molecule.db.compliance.domains.dsl.Types.metadb.Types_MetaDb_postgresql
import molecule.db.compliance.domains.dsl.Uniques.metadb.Uniques_MetaDb_postgresql
import molecule.db.compliance.domains.dsl.Validation.metadb.Validation_MetaDb_postgresql
import molecule.db.compliance.setup.{DbConnection, DbProviders, Platform}

trait DbProviders_postgresql extends DbProviders with DbConnection with Platform {
  override val database: String = "postgres"
  private val db = DbConnection_postgresql

  override def types(test: Conn ?=> Any): Any = db.run(test, Types_MetaDb_postgresql())
  override def refs(test: Conn ?=> Any): Any = db.run(test, Refs_MetaDb_postgresql())
  override def unique(test: Conn ?=> Any): Any = db.run(test, Uniques_MetaDb_postgresql())
  override def validation(test: Conn ?=> Any): Any = db.run(test, Validation_MetaDb_postgresql())
  override def segments(test: Conn ?=> Any): Any = db.run(test, Segments_MetaDb_postgresql())
}