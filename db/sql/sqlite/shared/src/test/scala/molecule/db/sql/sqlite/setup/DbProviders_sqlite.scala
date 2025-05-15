package molecule.db.sql.sqlite.setup

import molecule.db.compliance.domains.schema.*
import molecule.db.compliance.setup.{DbProviders, Platform}
import molecule.db.core.spi.Conn

trait DbProviders_sqlite extends DbProviders with DbConnection_sqlite with Platform {

  override val database: String = "sqlite"

  override def types(test: Conn => Any): Any = run(test, TypesSchema_sqlite)
  override def refs(test: Conn => Any): Any = run(test, RefsSchema_sqlite)
  override def unique(test: Conn => Any): Any = run(test, UniquesSchema_sqlite)
  override def validation(test: Conn => Any): Any = run(test, ValidationSchema_sqlite)
  override def segments(test: Conn => Any): Any = run(test, SegmentsSchema_sqlite)
}