package molecule.db.sql.postgres.setup

import molecule.db.compliance.domains.schema.*
import molecule.db.compliance.setup.{DbConnection, DbProviders, Platform}
import molecule.db.core.spi.Conn
import molecule.db.sql.postgres
import molecule.db.sql.postgres.setup.DbConnection_postgres

trait DbProviders_postgres extends DbProviders with DbConnection with Platform {

  override val database: String = "postgres"

  private val db = DbConnection_postgres

  override def types(test: Conn => Any): Any = db.run(test, TypesSchema_postgres)
  override def refs(test: Conn => Any): Any = db.run(test, RefsSchema_postgres)
  override def unique(test: Conn => Any): Any = db.run(test, UniquesSchema_postgres)
  override def validation(test: Conn => Any): Any = db.run(test, ValidationSchema_postgres)
  override def segments(test: Conn => Any): Any = db.run(test, SegmentsSchema_postgres)
}