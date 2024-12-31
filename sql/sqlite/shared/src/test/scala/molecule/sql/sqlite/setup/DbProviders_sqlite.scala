package molecule.sql.sqlite.setup

import molecule.core.spi.Conn
import molecule.coreTests.domains.schema._
import molecule.coreTests.setup.DbProviders

trait DbProviders_sqlite extends DbProviders with DbConnection_sqlite {

  override val database: String = "sqlite"

  override def types(test: Conn => Any): Any = run(test, TypesSchema_sqlite)
  override def refs(test: Conn => Any): Any = run(test, RefsSchema_sqlite)
  override def unique(test: Conn => Any): Any = run(test, UniquesSchema_sqlite)
  override def validation(test: Conn => Any): Any = run(test, ValidationSchema_sqlite)
  override def grouped(test: Conn => Any): Any = run(test, GroupsSchema_sqlite)
}