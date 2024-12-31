package molecule.sql.mariadb.setup

import molecule.core.spi.Conn
import molecule.coreTests.domains.schema._
import molecule.coreTests.setup.DbProviders

trait DbProviders_mariadb extends DbProviders with DbConnection_mariadb {

  override val database: String = "mariadb"

  override def types(test: Conn => Any): Any = run(test, TypesSchema_mariadb)
  override def refs(test: Conn => Any): Any = run(test, RefsSchema_mariadb)
  override def unique(test: Conn => Any): Any = run(test, UniquesSchema_mariadb)
  override def validation(test: Conn => Any): Any = run(test, ValidationSchema_mariadb)
  override def grouped(test: Conn => Any): Any = run(test, GroupsSchema_mariadb)
}