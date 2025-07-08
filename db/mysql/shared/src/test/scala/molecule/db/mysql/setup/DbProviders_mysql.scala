package molecule.db.mysql.setup

import molecule.db
import molecule.db.common.spi.Conn
import molecule.db.compliance.domains.dsl.Refs.metadb.Refs_MetaDb_mysql
import molecule.db.compliance.domains.dsl.Segments.metadb.Segments_MetaDb_mysql
import molecule.db.compliance.domains.dsl.Types.metadb.Types_MetaDb_mysql
import molecule.db.compliance.domains.dsl.Uniques.metadb.Uniques_MetaDb_mysql
import molecule.db.compliance.domains.dsl.Validation.metadb.Validation_MetaDb_mysql
import molecule.db.compliance.setup.{DbConnection, DbProviders, Platform}
import molecule.db.mysql.setup.DbConnection_mysql

trait DbProviders_mysql extends DbProviders with DbConnection with Platform {
  override val database: String = "mysql"
  private val db = DbConnection_mysql

  override def types(test: Conn => Any): Any = db.run(test, Types_MetaDb_mysql())
  override def refs(test: Conn => Any): Any = db.run(test, Refs_MetaDb_mysql())
  override def unique(test: Conn => Any): Any = db.run(test, Uniques_MetaDb_mysql())
  override def validation(test: Conn => Any): Any = db.run(test, Validation_MetaDb_mysql())
  override def segments(test: Conn => Any): Any = db.run(test, Segments_MetaDb_mysql())
}