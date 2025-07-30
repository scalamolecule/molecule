package molecule.db.mysql.setup

import molecule.db
import molecule.db.common.spi.Conn
import molecule.db.compliance.domains.dsl.Refs.metadb.Refs_mysql
import molecule.db.compliance.domains.dsl.Types.metadb.Types_mysql
import molecule.db.compliance.domains.dsl.Uniques.metadb.Uniques_mysql
import molecule.db.compliance.domains.dsl.Validation.metadb.Validation_mysql
import molecule.db.compliance.setup.{DbConnection, DbProviders_zio, Platform}
import molecule.db.mysql.setup.DbConnection_mysql
import zio.ZLayer

trait DbProviders_mysql_zio extends DbProviders_zio with DbConnection with Platform {
  override val database: String = "mysql"
  private  val db               = DbConnection_mysql

  override def types: ZLayer[Any, Throwable, Conn] = db.connZLayer(Types_mysql())
  override def refs: ZLayer[Any, Throwable, Conn] = db.connZLayer(Refs_mysql())
  override def unique: ZLayer[Any, Throwable, Conn] = db.connZLayer(Uniques_mysql())
  override def validation: ZLayer[Any, Throwable, Conn] = db.connZLayer(Validation_mysql())
}