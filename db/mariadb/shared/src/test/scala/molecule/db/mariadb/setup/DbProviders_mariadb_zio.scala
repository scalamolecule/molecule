package molecule.db.mariadb.setup

import boopickle.Default.*
import molecule.db
import molecule.db.common.spi.Conn
import molecule.db.compliance.domains.dsl.Refs.metadb.Refs_mariadb
import molecule.db.compliance.domains.dsl.Types.metadb.Types_mariadb
import molecule.db.compliance.domains.dsl.Uniques.metadb.Uniques_mariadb
import molecule.db.compliance.domains.dsl.Validation.metadb.Validation_mariadb
import molecule.db.compliance.setup.{DbConnection, DbProviders_zio, Platform}
import molecule.db.mariadb.setup.DbConnection_mariadb
import zio.ZLayer

trait DbProviders_mariadb_zio extends DbProviders_zio with DbConnection with Platform {
  override val database: String = "mariadb"
  private  val db               = DbConnection_mariadb

  override def types: ZLayer[Any, Throwable, Conn] = db.connZLayer(Types_mariadb())
  override def refs: ZLayer[Any, Throwable, Conn] = db.connZLayer(Refs_mariadb())
  override def unique: ZLayer[Any, Throwable, Conn] = db.connZLayer(Uniques_mariadb())
  override def validation: ZLayer[Any, Throwable, Conn] = db.connZLayer(Validation_mariadb())
}