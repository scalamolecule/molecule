package molecule.db.postgresql.setup

import molecule.db.common.spi.Conn
import molecule.db.compliance.domains.dsl.Refs.metadb.Refs_postgresql
import molecule.db.compliance.domains.dsl.Segments.metadb.Segments_postgresql
import molecule.db.compliance.domains.dsl.Types.metadb.Types_postgresql
import molecule.db.compliance.domains.dsl.Uniques.metadb.Uniques_postgresql
import molecule.db.compliance.domains.dsl.Validation.metadb.Validation_postgresql
import molecule.db.compliance.setup.{DbConnection, DbProviders, Platform}

trait DbProviders_postgresql extends DbProviders with DbConnection with Platform {
  override val database: String = "postgres"
  private val db = DbConnection_postgresql

  override def types(test: Conn ?=> Any): Any = db.run(test, Types_postgresql())
  override def refs(test: Conn ?=> Any): Any = db.run(test, Refs_postgresql())
  override def unique(test: Conn ?=> Any): Any = db.run(test, Uniques_postgresql())
  override def validation(test: Conn ?=> Any): Any = db.run(test, Validation_postgresql())
  override def segments(test: Conn ?=> Any): Any = db.run(test, Segments_postgresql())
}