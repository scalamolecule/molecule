package molecule.datalog.datomic.setup

import molecule.core.spi.Conn
import molecule.coreTests.domains.schema._
import molecule.coreTests.setup.{DbProviders, Platform}

trait DbProviders_datomic extends DbProviders with DbConnection_datomic with Platform {

  override val database: String = "datomic"

  override def types(test: Conn => Any): Any = run(test, TypesSchema_datomic)
  override def refs(test: Conn => Any): Any = run(test, RefsSchema_datomic)
  override def unique(test: Conn => Any): Any = run(test, UniquesSchema_datomic)
  override def validation(test: Conn => Any): Any = run(test, ValidationSchema_datomic)
  override def grouped(test: Conn => Any): Any = run(test, GroupsSchema_datomic)
}