package molecule.db.sql.mariadb.setup

import molecule.db.compliance.domains.schema.*
import molecule.db.compliance.setup.{DbConnection, DbProviders, Platform}
import molecule.db.core.spi.Conn

trait DbProviders_mariadb extends DbProviders with DbConnection with Platform {

  override val database: String = "mariadb"

  private val db = DbConnection_mariadb

  override def types(test: Conn => Any): Any = db.run(test, TypesSchema_mariadb)
  override def refs(test: Conn => Any): Any = db.run(test, RefsSchema_mariadb)
  override def unique(test: Conn => Any): Any = db.run(test, UniquesSchema_mariadb)
  override def validation(test: Conn => Any): Any = db.run(test, ValidationSchema_mariadb)
  override def segments(test: Conn => Any): Any = db.run(test, SegmentsSchema_mariadb)
}