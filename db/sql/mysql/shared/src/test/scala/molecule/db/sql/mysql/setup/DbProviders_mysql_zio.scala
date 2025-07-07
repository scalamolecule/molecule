package molecule.db.sql.mysql.setup

import boopickle.Default.*
import molecule.db.compliance.domains.dsl.Refs.metadb.Refs_MetaDb_mysql
import molecule.db.compliance.domains.dsl.Types.metadb.Types_MetaDb_mysql
import molecule.db.compliance.domains.dsl.Uniques.metadb.Uniques_MetaDb_mysql
import molecule.db.compliance.domains.dsl.Validation.metadb.Validation_MetaDb_mysql
import molecule.db.compliance.setup.{DbConnection, DbProviders_zio, Platform}
import molecule.db.core.spi.Conn
import zio.ZLayer

trait DbProviders_mysql_zio extends DbProviders_zio with DbConnection with Platform {
  override val database: String = "mysql"
  private  val db               = DbConnection_mysql

  override def types: ZLayer[Any, Throwable, Conn] = db.connZLayer(Types_MetaDb_mysql())
  override def refs: ZLayer[Any, Throwable, Conn] = db.connZLayer(Refs_MetaDb_mysql())
  override def unique: ZLayer[Any, Throwable, Conn] = db.connZLayer(Uniques_MetaDb_mysql())
  override def validation: ZLayer[Any, Throwable, Conn] = db.connZLayer(Validation_MetaDb_mysql())
}