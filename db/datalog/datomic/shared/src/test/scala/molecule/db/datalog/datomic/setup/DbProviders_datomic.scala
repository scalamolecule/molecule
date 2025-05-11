package molecule.db.datalog.datomic.setup

import molecule.db.compliance.domains.schema.*
import molecule.db.compliance.setup.{DbProviders, Platform}
import molecule.db.core.spi.Conn
import molecule.db.datalog.datomic

trait DbProviders_datomic extends DbProviders with DbConnection_datomic with Platform {

  override val database: String = "datomic"

  override def types(test: Conn => Any): Any = run(test, TypesSchema_datomic)
  override def refs(test: Conn => Any): Any = run(test, RefsSchema_datomic)
  override def unique(test: Conn => Any): Any = run(test, UniquesSchema_datomic)
  override def validation(test: Conn => Any): Any = run(test, ValidationSchema_datomic)
  override def segments(test: Conn => Any): Any = run(test, SegmentsSchema_datomic)
}