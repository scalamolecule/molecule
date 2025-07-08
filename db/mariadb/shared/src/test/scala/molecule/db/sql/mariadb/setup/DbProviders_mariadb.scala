package molecule.db.sql.mariadb.setup

import molecule.db.common.spi.Conn
import molecule.db.compliance.domains.dsl.Refs.metadb.Refs_MetaDb_mariadb
import molecule.db.compliance.domains.dsl.Segments.metadb.Segments_MetaDb_mariadb
import molecule.db.compliance.domains.dsl.Types.metadb.Types_MetaDb_mariadb
import molecule.db.compliance.domains.dsl.Uniques.metadb.Uniques_MetaDb_mariadb
import molecule.db.compliance.domains.dsl.Validation.metadb.Validation_MetaDb_mariadb
import molecule.db.compliance.setup.{DbConnection, DbProviders, Platform}

trait DbProviders_mariadb extends DbProviders with DbConnection with Platform {
  override val database: String = "mariadb"
  private val db = DbConnection_mariadb

  override def types(test: Conn => Any): Any = db.run(test, Types_MetaDb_mariadb())
  override def refs(test: Conn => Any): Any = db.run(test, Refs_MetaDb_mariadb())
  override def unique(test: Conn => Any): Any = db.run(test, Uniques_MetaDb_mariadb())
  override def validation(test: Conn => Any): Any = db.run(test, Validation_MetaDb_mariadb())
  override def segments(test: Conn => Any): Any = db.run(test, Segments_MetaDb_mariadb())
}