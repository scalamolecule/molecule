package molecule.sql.mysql.setup

import molecule.core.spi.Conn
import molecule.coreTests.domains.schema.*
import molecule.coreTests.setup.{DbConnection, DbProviders, Platform}

trait DbProviders_mysql extends DbProviders with DbConnection with Platform {

  override val database: String = "mysql"

  private val db = DbConnection_mysql

  override def types(test: Conn => Any): Any = db.run(test, TypesSchema_mysql)
  override def refs(test: Conn => Any): Any = db.run(test, RefsSchema_mysql)
  override def unique(test: Conn => Any): Any = db.run(test, UniquesSchema_mysql)
  override def validation(test: Conn => Any): Any = db.run(test, ValidationSchema_mysql)
  override def segments(test: Conn => Any): Any = db.run(test, SegmentsSchema_mysql)
}