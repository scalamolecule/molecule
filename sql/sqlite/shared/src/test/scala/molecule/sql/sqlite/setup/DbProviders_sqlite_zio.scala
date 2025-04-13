package molecule.sql.sqlite.setup

import boopickle.Default._
import molecule.core.spi.Conn
import molecule.coreTests.domains.schema._
import molecule.coreTests.setup.{DbProviders_zio, Platform}
import zio.ZLayer

trait DbProviders_sqlite_zio extends DbProviders_zio with DbConnection_sqlite with Platform {
  override val database: String = "sqlite"

  override def types: ZLayer[Any, Throwable, Conn] = connZLayer(TypesSchema_sqlite)
  override def refs: ZLayer[Any, Throwable, Conn] = connZLayer(RefsSchema_sqlite)
  override def unique: ZLayer[Any, Throwable, Conn] = connZLayer(UniquesSchema_sqlite)
  override def validation: ZLayer[Any, Throwable, Conn] = connZLayer(ValidationSchema_sqlite)
}