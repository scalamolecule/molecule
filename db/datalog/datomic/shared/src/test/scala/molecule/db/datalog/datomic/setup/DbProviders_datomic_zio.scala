package molecule.db.datalog.datomic.setup

import boopickle.Default.*
import molecule.db.compliance.domains.schema.*
import molecule.db.compliance.setup.{DbProviders_zio, Platform}
import molecule.db.core.spi.Conn
import molecule.db.datalog.datomic
import zio.ZLayer

trait DbProviders_datomic_zio extends DbProviders_zio with DbConnection_datomic with Platform {
  override val database: String = "datomic"

  override def types: ZLayer[Any, Throwable, Conn] = connZLayer(TypesSchema_datomic)
  override def refs: ZLayer[Any, Throwable, Conn] = connZLayer(RefsSchema_datomic)
  override def unique: ZLayer[Any, Throwable, Conn] = connZLayer(UniquesSchema_datomic)
  override def validation: ZLayer[Any, Throwable, Conn] = connZLayer(ValidationSchema_datomic)
}