package molecule.sql.mariadb.setup

import boopickle.Default._
import molecule.core.spi.Conn
import molecule.coreTests.domains.schema._
import molecule.coreTests.setup.{DbConnection, DbProviders_zio, Platform}
import zio.ZLayer

trait DbProviders_mariadb_zio extends DbProviders_zio with DbConnection with Platform {
  override val database: String = "mariadb"

  private val db = DbConnection_mariadb

  override def types: ZLayer[Any, Throwable, Conn] = db.connZLayer(TypesSchema_mariadb)
  override def refs: ZLayer[Any, Throwable, Conn] = db.connZLayer(RefsSchema_mariadb)
  override def unique: ZLayer[Any, Throwable, Conn] = db.connZLayer(UniquesSchema_mariadb)
  override def validation: ZLayer[Any, Throwable, Conn] = db.connZLayer(ValidationSchema_mariadb)
}