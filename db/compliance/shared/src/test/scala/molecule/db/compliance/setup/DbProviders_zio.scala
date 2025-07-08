package molecule.db.compliance.setup

import molecule.db.common.spi.Conn
import zio.ZLayer

trait DbProviders_zio { self: DbConnection & Platform =>
  val database: String
  val platform: String

  def types: ZLayer[Any, Throwable, Conn]
  def refs: ZLayer[Any, Throwable, Conn]
  def unique: ZLayer[Any, Throwable, Conn]
  def validation: ZLayer[Any, Throwable, Conn]
}