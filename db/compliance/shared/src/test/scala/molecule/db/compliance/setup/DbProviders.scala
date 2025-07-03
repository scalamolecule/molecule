package molecule.db.compliance.setup

import molecule.db.core.spi.Conn

trait DbProviders { self: DbConnection & Platform =>
  val database: String
  val platform: String

  def community(test: Conn => Any): Any = ???
  def types(test: Conn => Any): Any = ???
  def refs(test: Conn => Any): Any = ???
  def unique(test: Conn => Any): Any = ???
  def validation(test: Conn => Any): Any = ???
  def segments(test: Conn => Any): Any = ???
}