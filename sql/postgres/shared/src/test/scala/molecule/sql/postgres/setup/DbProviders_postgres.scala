package molecule.sql.postgres.setup

import molecule.core.spi.Conn
import molecule.coreTests.domains.schema._
import molecule.coreTests.setup.{DbConnection, DbProviders, Platform}

trait DbProviders_postgres extends DbProviders with DbConnection with Platform {

  override val database: String = "postgres"

  private val db = DbConnection_postgres

  override def types(test: Conn => Any): Any = db.run(test, TypesSchema_postgres)
  override def refs(test: Conn => Any): Any = db.run(test, RefsSchema_postgres)
  override def unique(test: Conn => Any): Any = db.run(test, UniquesSchema_postgres)
  override def validation(test: Conn => Any): Any = db.run(test, ValidationSchema_postgres)
  override def segments(test: Conn => Any): Any = db.run(test, SegmentsSchema_postgres)
}