package molecule.db.sql.postgres.setup

import boopickle.Default.*
import molecule.db.compliance.domains.schema.*
import molecule.db.compliance.setup.{DbConnection, DbProviders_zio, Platform}
import molecule.db.core.spi.Conn
import zio.ZLayer

trait DbProviders_postgres_zio extends DbProviders_zio with DbConnection with Platform {
  override val database: String = "mariadb"

  private val db = DbConnection_postgres

  override def types: ZLayer[Any, Throwable, Conn] = db.connZLayer(TypesSchema_postgres)
  override def refs: ZLayer[Any, Throwable, Conn] = db.connZLayer(RefsSchema_postgres)
  override def unique: ZLayer[Any, Throwable, Conn] = db.connZLayer(UniquesSchema_postgres)
  override def validation: ZLayer[Any, Throwable, Conn] = db.connZLayer(ValidationSchema_postgres)
}