package molecule.sql.postgres.setup

import molecule.core.spi.Conn
import molecule.coreTests.domains.schema._
import molecule.coreTests.setup.DbProviders

trait DbProviders_postgres extends DbProviders with DbConnection_postgres {

  override val database: String = "sqlite"

  override def types(test: Conn => Any): Any = run(test, TypesSchema_postgres)
  override def refs(test: Conn => Any): Any = run(test, RefsSchema_postgres)
  override def unique(test: Conn => Any): Any = run(test, UniquesSchema_postgres)
  override def validation(test: Conn => Any): Any = run(test, ValidationSchema_postgres)
  override def grouped(test: Conn => Any): Any = run(test, GroupsSchema_postgres)
}