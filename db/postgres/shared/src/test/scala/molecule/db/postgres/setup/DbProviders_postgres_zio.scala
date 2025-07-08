package molecule.db.postgres.setup

import molecule.db
import molecule.db.common.spi.Conn
import molecule.db.compliance.domains.dsl.Refs.metadb.Refs_MetaDb_postgres
import molecule.db.compliance.domains.dsl.Types.metadb.Types_MetaDb_postgres
import molecule.db.compliance.domains.dsl.Uniques.metadb.Uniques_MetaDb_postgres
import molecule.db.compliance.domains.dsl.Validation.metadb.Validation_MetaDb_postgres
import molecule.db.compliance.setup.{DbConnection, DbProviders_zio, Platform}
import molecule.db.postgres.setup.DbConnection_postgres
import zio.ZLayer

trait DbProviders_postgres_zio extends DbProviders_zio with DbConnection with Platform {
  override val database: String = "mariadb"
  private  val db               = DbConnection_postgres

  override def types: ZLayer[Any, Throwable, Conn] = db.connZLayer(Types_MetaDb_postgres())
  override def refs: ZLayer[Any, Throwable, Conn] = db.connZLayer(Refs_MetaDb_postgres())
  override def unique: ZLayer[Any, Throwable, Conn] = db.connZLayer(Uniques_MetaDb_postgres())
  override def validation: ZLayer[Any, Throwable, Conn] = db.connZLayer(Validation_MetaDb_postgres())
}