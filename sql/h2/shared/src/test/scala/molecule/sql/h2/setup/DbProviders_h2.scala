package molecule.sql.h2.setup

import molecule.core.spi.Conn
import molecule.coreTests.domains.schema._
import molecule.coreTests.setup.{DbProviders, Platform}

trait DbProviders_h2 extends DbProviders with DbConnection_h2 with Platform {

  override val database: String = "h2"

  override def types(test: Conn => Any): Any = run(test, TypesSchema_h2)
  override def refs(test: Conn => Any): Any = run(test, RefsSchema_h2)
  override def unique(test: Conn => Any): Any = run(test, UniquesSchema_h2)
  override def validation(test: Conn => Any): Any = run(test, ValidationSchema_h2)
  override def segments(test: Conn => Any): Any = run(test, SegmentsSchema_h2)
}