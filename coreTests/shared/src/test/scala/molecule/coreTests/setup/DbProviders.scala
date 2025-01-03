package molecule.coreTests.setup

import molecule.core.spi.Conn

trait DbProviders { self: DbConnection with Platform =>
  val database: String
  val platform: String

  def types(test: Conn => Any): Any
  def refs(test: Conn => Any): Any = ???
  def unique(test: Conn => Any): Any = ???
  def validation(test: Conn => Any): Any = ???
  def segments(test: Conn => Any): Any = ???
}