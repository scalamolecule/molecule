package molecule.db.mysql.setup

import molecule.db.common.spi.Conn
import molecule.db.compliance.domains.dsl.JoinTable.metadb.JoinTable_mysql
import molecule.db.compliance.domains.dsl.Refs.metadb.Refs_mysql
import molecule.db.compliance.domains.dsl.Segments.metadb.Segments_mysql
import molecule.db.compliance.domains.dsl.SocialApp1_roles.metadb.SocialApp1_roles_mysql
import molecule.db.compliance.domains.dsl.SocialApp2_role_actions.metadb.SocialApp2_role_actions_mysql
import molecule.db.compliance.domains.dsl.SocialApp3_attr_roles.metadb.SocialApp3_attr_roles_mysql
import molecule.db.compliance.domains.dsl.SocialApp4_attr_update.metadb.SocialApp4_attr_update_mysql
import molecule.db.compliance.domains.dsl.SocialApp_overview.metadb.SocialApp_overview_mysql
import molecule.db.compliance.domains.dsl.SocialApp_raw_access.metadb.SocialApp_raw_access_mysql
import molecule.db.compliance.domains.dsl.Types.metadb.Types_mysql
import molecule.db.compliance.domains.dsl.Uniques.metadb.Uniques_mysql
import molecule.db.compliance.domains.dsl.Validation.metadb.Validation_mysql
import molecule.db.compliance.setup.{DbConnection, DbProviders, Platform}
import molecule.db.mysql.setup.DbConnection_mysql

trait DbProviders_mysql extends DbProviders with DbConnection with Platform {
  override val database: String = "mysql"
  private val db = DbConnection_mysql

  override def types(test: Conn ?=> Any): Any = db.run(test, Types_mysql())
  override def refs(test: Conn ?=> Any): Any = db.run(test, Refs_mysql())
  override def joinTable(test: Conn ?=> Any): Any = db.run(test, JoinTable_mysql())
  override def unique(test: Conn ?=> Any): Any = db.run(test, Uniques_mysql())
  override def validation(test: Conn ?=> Any): Any = db.run(test, Validation_mysql())
  override def social0(test: Conn ?=> Any): Any = db.run(test, SocialApp_overview_mysql())
  override def social1(test: Conn ?=> Any): Any = db.run(test, SocialApp1_roles_mysql())
  override def social2(test: Conn ?=> Any): Any = db.run(test, SocialApp2_role_actions_mysql())
  override def social3(test: Conn ?=> Any): Any = db.run(test, SocialApp3_attr_roles_mysql())
  override def social4(test: Conn ?=> Any): Any = db.run(test, SocialApp4_attr_update_mysql())
  override def rawAccess(test: Conn ?=> Any): Any = db.run(test, SocialApp_raw_access_mysql())
  override def segments(test: Conn ?=> Any): Any = db.run(test, Segments_mysql())
}