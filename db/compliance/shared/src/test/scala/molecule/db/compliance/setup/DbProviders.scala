package molecule.db.compliance.setup

import molecule.db.common.spi.Conn

trait DbProviders { self: DbConnection & Platform =>
  val database: String
  val platform: String

  def types(test: Conn ?=> Any): Any = ???
  def refs(test: Conn ?=> Any): Any = ???
  def joinTable(test: Conn ?=> Any): Any = ???
  def unique(test: Conn ?=> Any): Any = ???
  def validation(test: Conn ?=> Any): Any = ???
  def social0(test: Conn ?=> Any): Any = ???
  def social1(test: Conn ?=> Any): Any = ???
  def social2(test: Conn ?=> Any): Any = ???
  def social3(test: Conn ?=> Any): Any = ???
  def social4(test: Conn ?=> Any): Any = ???
  def rawAccess(test: Conn ?=> Any): Any = ???
  def segments(test: Conn ?=> Any): Any = ???
}