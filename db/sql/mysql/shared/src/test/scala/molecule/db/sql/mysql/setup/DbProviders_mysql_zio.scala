package molecule.db.sql.mysql.setup

import boopickle.Default.*
import molecule.core.spi.Conn
import molecule.coreTests.domains.schema.*
import molecule.coreTests.setup.{DbConnection, DbProviders_zio, Platform}
import molecule.db.sql.mysql
import zio.ZLayer

trait DbProviders_mysql_zio extends DbProviders_zio with DbConnection with Platform {
  override val database: String = "mysql"

  private val db = DbConnection_mysql

  override def types: ZLayer[Any, Throwable, Conn] = db.connZLayer(TypesSchema_mysql)
  override def refs: ZLayer[Any, Throwable, Conn] = db.connZLayer(RefsSchema_mysql)
  override def unique: ZLayer[Any, Throwable, Conn] = db.connZLayer(UniquesSchema_mysql)
  override def validation: ZLayer[Any, Throwable, Conn] = db.connZLayer(ValidationSchema_mysql)
}