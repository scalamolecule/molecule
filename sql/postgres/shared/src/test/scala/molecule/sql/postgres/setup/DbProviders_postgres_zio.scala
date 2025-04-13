package molecule.sql.postgres.setup

import boopickle.Default._
import molecule.core.spi.Conn
import molecule.coreTests.domains.schema._
import molecule.coreTests.setup.{DbConnection, DbProviders_zio, Platform}
import zio.ZLayer

trait DbProviders_postgres_zio extends DbProviders_zio with DbConnection with Platform {
  override val database: String = "mariadb"

  private val db = DbConnection_postgres

  override def types: ZLayer[Any, Throwable, Conn] = db.connZLayer(TypesSchema_postgres)
  override def refs: ZLayer[Any, Throwable, Conn] = db.connZLayer(RefsSchema_postgres)
  override def unique: ZLayer[Any, Throwable, Conn] = db.connZLayer(UniquesSchema_postgres)
  override def validation: ZLayer[Any, Throwable, Conn] = db.connZLayer(ValidationSchema_postgres)
}