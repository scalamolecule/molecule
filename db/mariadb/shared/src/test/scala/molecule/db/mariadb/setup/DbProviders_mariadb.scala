package molecule.db.mariadb.setup

import molecule.db.common.spi.Conn
import molecule.db.compliance.domains.dsl.JoinTable.metadb.JoinTable_mariadb
import molecule.db.compliance.domains.dsl.Refs.metadb.Refs_mariadb
import molecule.db.compliance.domains.dsl.Segments.metadb.Segments_mariadb
import molecule.db.compliance.domains.dsl.SocialApp1_roles.metadb.SocialApp1_roles_mariadb
import molecule.db.compliance.domains.dsl.SocialApp2_role_actions.metadb.SocialApp2_role_actions_mariadb
import molecule.db.compliance.domains.dsl.SocialApp3_attr_roles.metadb.SocialApp3_attr_roles_mariadb
import molecule.db.compliance.domains.dsl.SocialApp4_attr_update.metadb.SocialApp4_attr_update_mariadb
import molecule.db.compliance.domains.dsl.SocialApp_overview.metadb.SocialApp_overview_mariadb
import molecule.db.compliance.domains.dsl.SocialApp_raw_access.metadb.SocialApp_raw_access_mariadb
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
  override def joinTable(test: Conn ?=> Any): Any = db.run(test, JoinTable_mariadb())
  override def unique(test: Conn ?=> Any): Any = db.run(test, Uniques_mariadb())
  override def validation(test: Conn ?=> Any): Any = db.run(test, Validation_mariadb())
  override def social0(test: Conn ?=> Any): Any = db.run(test, SocialApp_overview_mariadb())
  override def social1(test: Conn ?=> Any): Any = db.run(test, SocialApp1_roles_mariadb())
  override def social2(test: Conn ?=> Any): Any = db.run(test, SocialApp2_role_actions_mariadb())
  override def social3(test: Conn ?=> Any): Any = db.run(test, SocialApp3_attr_roles_mariadb())
  override def social4(test: Conn ?=> Any): Any = db.run(test, SocialApp4_attr_update_mariadb())
  override def rawAccess(test: Conn ?=> Any): Any = db.run(test, SocialApp_raw_access_mariadb())
  override def segments(test: Conn ?=> Any): Any = db.run(test, Segments_mariadb())
}