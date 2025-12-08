package molecule.db.postgresql.setup

import molecule.db.common.spi.Conn
import molecule.db.compliance.domains.dsl.JoinTable.metadb.JoinTable_postgresql
import molecule.db.compliance.domains.dsl.Refs.metadb.Refs_postgresql
import molecule.db.compliance.domains.dsl.Segments.metadb.Segments_postgresql
import molecule.db.compliance.domains.dsl.SocialApp1_roles.metadb.SocialApp1_roles_postgresql
import molecule.db.compliance.domains.dsl.SocialApp2_role_actions.metadb.SocialApp2_role_actions_postgresql
import molecule.db.compliance.domains.dsl.SocialApp3_attr_roles.metadb.SocialApp3_attr_roles_postgresql
import molecule.db.compliance.domains.dsl.SocialApp4_attr_update.metadb.SocialApp4_attr_update_postgresql
import molecule.db.compliance.domains.dsl.SocialApp_overview.metadb.SocialApp_overview_postgresql
import molecule.db.compliance.domains.dsl.SocialApp_raw_access.metadb.SocialApp_raw_access_postgresql
import molecule.db.compliance.domains.dsl.Types.metadb.Types_postgresql
import molecule.db.compliance.domains.dsl.Uniques.metadb.Uniques_postgresql
import molecule.db.compliance.domains.dsl.Validation.metadb.Validation_postgresql
import molecule.db.compliance.setup.{DbConnection, DbProviders, Platform}

trait DbProviders_postgresql extends DbProviders with DbConnection with Platform {
  override val database: String = "postgresql"
  private val db = DbConnection_postgresql

  override def types(test: Conn ?=> Any): Any = db.run(test, Types_postgresql())
  override def refs(test: Conn ?=> Any): Any = db.run(test, Refs_postgresql())
  override def joinTable(test: Conn ?=> Any): Any = db.run(test, JoinTable_postgresql())
  override def unique(test: Conn ?=> Any): Any = db.run(test, Uniques_postgresql())
  override def validation(test: Conn ?=> Any): Any = db.run(test, Validation_postgresql())
  override def social0(test: Conn ?=> Any): Any = db.run(test, SocialApp_overview_postgresql())
  override def social1(test: Conn ?=> Any): Any = db.run(test, SocialApp1_roles_postgresql())
  override def social2(test: Conn ?=> Any): Any = db.run(test, SocialApp2_role_actions_postgresql())
  override def social3(test: Conn ?=> Any): Any = db.run(test, SocialApp3_attr_roles_postgresql())
  override def social4(test: Conn ?=> Any): Any = db.run(test, SocialApp4_attr_update_postgresql())
  override def rawAccess(test: Conn ?=> Any): Any = db.run(test, SocialApp_raw_access_postgresql())
  override def segments(test: Conn ?=> Any): Any = db.run(test, Segments_postgresql())
}