package molecule.db.mariadb.setup

import molecule.db
import molecule.db.common.spi.Conn
import molecule.db.compliance.domains.dsl.Refs.metadb.Refs_mariadb
import molecule.db.compliance.domains.dsl.Segments.metadb.Segments_mariadb
import molecule.db.compliance.domains.dsl.Types.metadb.Types_mariadb
import molecule.db.compliance.domains.dsl.Uniques.metadb.Uniques_mariadb
import molecule.db.compliance.domains.dsl.Validation.metadb.Validation_mariadb
import molecule.db.compliance.setup.{DbConnection, DbProviders, Platform}
import molecule.db.mariadb.setup.DbConnection_mariadb

trait DbProviders_mariadb extends DbProviders with DbConnection with Platform {
  override val database: String = "mariadb"
  private val db = DbConnection_mariadb

  override def types(test: Conn ?=> Any): Any = db.run(test, Types_mariadb())
  override def refs(test: Conn ?=> Any): Any = db.run(test, Refs_mariadb())
  override def unique(test: Conn ?=> Any): Any = db.run(test, Uniques_mariadb())
  override def validation(test: Conn ?=> Any): Any = db.run(test, Validation_mariadb())
  override def segments(test: Conn ?=> Any): Any = db.run(test, Segments_mariadb())
}