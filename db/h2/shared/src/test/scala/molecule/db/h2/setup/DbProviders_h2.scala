package molecule.db.h2.setup

import molecule.db.common.spi.Conn
import molecule.db.compliance.domains.dsl.JoinTable.metadb.JoinTable_h2
import molecule.db.compliance.domains.dsl.Refs.metadb.Refs_h2
import molecule.db.compliance.domains.dsl.Segments.metadb.Segments_h2
import molecule.db.compliance.domains.dsl.SocialApp_overview.metadb.SocialApp_overview_h2
import molecule.db.compliance.domains.dsl.SocialApp1_roles.metadb.SocialApp1_roles_h2
import molecule.db.compliance.domains.dsl.SocialApp2_role_actions.metadb.SocialApp2_role_actions_h2
import molecule.db.compliance.domains.dsl.SocialApp3_attr_roles.metadb.SocialApp3_attr_roles_h2
import molecule.db.compliance.domains.dsl.SocialApp4_attr_update.metadb.SocialApp4_attr_update_h2
import molecule.db.compliance.domains.dsl.SocialApp_raw_access.metadb.SocialApp_raw_access_h2
import molecule.db.compliance.domains.dsl.Types.metadb.Types_h2
import molecule.db.compliance.domains.dsl.Uniques.metadb.Uniques_h2
import molecule.db.compliance.domains.dsl.Validation.metadb.Validation_h2
import molecule.db.compliance.setup.{DbProviders, Platform}
import molecule.db.h2.setup.DbConnection_h2

trait DbProviders_h2 extends DbProviders with DbConnection_h2 with Platform {
  override val database: String = "h2"

  override def types(test: Conn ?=> Any): Any = run(test, Types_h2())
  override def refs(test: Conn ?=> Any): Any = run(test, Refs_h2())
  override def joinTable(test: Conn ?=> Any): Any = run(test, JoinTable_h2())
  override def unique(test: Conn ?=> Any): Any = run(test, Uniques_h2())
  override def validation(test: Conn ?=> Any): Any = run(test, Validation_h2())
  override def social0(test: Conn ?=> Any): Any = run(test, SocialApp_overview_h2())
  override def social1(test: Conn ?=> Any): Any = run(test, SocialApp1_roles_h2())
  override def social2(test: Conn ?=> Any): Any = run(test, SocialApp2_role_actions_h2())
  override def social3(test: Conn ?=> Any): Any = run(test, SocialApp3_attr_roles_h2())
  override def social4(test: Conn ?=> Any): Any = run(test, SocialApp4_attr_update_h2())
  override def rawAccess(test: Conn ?=> Any): Any = run(test, SocialApp_raw_access_h2())
  override def segments(test: Conn ?=> Any): Any = run(test, Segments_h2())
}