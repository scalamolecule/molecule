package molecule.sql.mysql.setup

import molecule.core.spi.Conn
import molecule.coreTests.domains.schema._
import molecule.coreTests.setup.DbProviders

trait DbProviders_mysql extends DbProviders with DbConnection_mysql {

  override val database: String = "sqlite"

  override def types(test: Conn => Any): Any = run(test, TypesSchema_mysql)
  override def refs(test: Conn => Any): Any = run(test, RefsSchema_mysql)
  override def unique(test: Conn => Any): Any = run(test, UniquesSchema_mysql)
  override def validation(test: Conn => Any): Any = run(test, ValidationSchema_mysql)
  override def grouped(test: Conn => Any): Any = run(test, GroupsSchema_mysql)
}