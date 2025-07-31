package molecule.db.postgresql.setup

import molecule.db.common.spi.Conn
import molecule.db.compliance.domains.dsl.Refs.metadb.Refs_postgresql
import molecule.db.compliance.domains.dsl.Types.metadb.Types_postgresql
import molecule.db.compliance.domains.dsl.Uniques.metadb.Uniques_postgresql
import molecule.db.compliance.domains.dsl.Validation.metadb.Validation_postgresql
import molecule.db.compliance.setup.{DbConnection, DbProviders_zio, Platform}
import zio.ZLayer

trait DbProviders_postgresql_zio extends DbProviders_zio with DbConnection with Platform {
  override val database: String = "postgresql"
  private  val db               = DbConnection_postgresql

  override def types: ZLayer[Any, Throwable, Conn] = db.connZLayer(Types_postgresql())
  override def refs: ZLayer[Any, Throwable, Conn] = db.connZLayer(Refs_postgresql())
  override def unique: ZLayer[Any, Throwable, Conn] = db.connZLayer(Uniques_postgresql())
  override def validation: ZLayer[Any, Throwable, Conn] = db.connZLayer(Validation_postgresql())
}