package molecule.db.sql.mariadb.setup

import boopickle.Default.*
import molecule.db.compliance.domains.schema.*
import molecule.db.compliance.setup.{DbConnection, DbProviders_zio, Platform}
import molecule.db.core.spi.Conn
import molecule.db.sql.mariadb
import molecule.db.sql.mariadb.setup.DbConnection_mariadb
import zio.ZLayer

trait DbProviders_mariadb_zio extends DbProviders_zio with DbConnection with Platform {
  override val database: String = "mariadb"

  private val db = DbConnection_mariadb

  override def types: ZLayer[Any, Throwable, Conn] = db.connZLayer(TypesSchema_mariadb)
  override def refs: ZLayer[Any, Throwable, Conn] = db.connZLayer(RefsSchema_mariadb)
  override def unique: ZLayer[Any, Throwable, Conn] = db.connZLayer(UniquesSchema_mariadb)
  override def validation: ZLayer[Any, Throwable, Conn] = db.connZLayer(ValidationSchema_mariadb)
}