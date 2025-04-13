package molecule.sql.h2.setup

import boopickle.Default._
import molecule.core.spi.Conn
import molecule.coreTests.domains.schema._
import molecule.coreTests.setup.{DbProviders_zio, Platform}
import zio.ZLayer

trait DbProviders_h2_zio extends DbProviders_zio with DbConnection_h2 with Platform {
  override val database: String = "h2"

  override def types: ZLayer[Any, Throwable, Conn] = connZLayer(TypesSchema_h2)
  override def refs: ZLayer[Any, Throwable, Conn] = connZLayer(RefsSchema_h2)
  override def unique: ZLayer[Any, Throwable, Conn] = connZLayer(UniquesSchema_h2)
  override def validation: ZLayer[Any, Throwable, Conn] = connZLayer(ValidationSchema_h2)
}