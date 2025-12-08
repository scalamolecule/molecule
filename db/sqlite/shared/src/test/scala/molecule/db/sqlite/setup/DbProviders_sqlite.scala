package molecule.db.sqlite.setup

import molecule.db.common.spi.Conn
import molecule.db.compliance.domains.dsl.JoinTable.metadb.JoinTable_sqlite
import molecule.db.compliance.domains.dsl.Refs.metadb.Refs_sqlite
import molecule.db.compliance.domains.dsl.Segments.metadb.Segments_sqlite
import molecule.db.compliance.domains.dsl.SocialApp1_roles.metadb.SocialApp1_roles_sqlite
import molecule.db.compliance.domains.dsl.SocialApp2_role_actions.metadb.SocialApp2_role_actions_sqlite
import molecule.db.compliance.domains.dsl.SocialApp3_attr_roles.metadb.SocialApp3_attr_roles_sqlite
import molecule.db.compliance.domains.dsl.SocialApp4_attr_update.metadb.SocialApp4_attr_update_sqlite
import molecule.db.compliance.domains.dsl.SocialApp_overview.metadb.SocialApp_overview_sqlite
import molecule.db.compliance.domains.dsl.SocialApp_raw_access.metadb.SocialApp_raw_access_sqlite
import molecule.db.compliance.domains.dsl.Types.metadb.*
import molecule.db.compliance.domains.dsl.Uniques.metadb.Uniques_sqlite
import molecule.db.compliance.domains.dsl.Validation.metadb.Validation_sqlite
import molecule.db.compliance.setup.{DbProviders, Platform}
import molecule.db.sqlite.setup.DbConnection_sqlite

trait DbProviders_sqlite extends DbProviders with DbConnection_sqlite with Platform {
  override val database: String = "sqlite"

  override def types(test: Conn ?=> Any): Any = run(test, Types_sqlite())
  override def refs(test: Conn ?=> Any): Any = run(test, Refs_sqlite())
  override def joinTable(test: Conn ?=> Any): Any = run(test, JoinTable_sqlite())
  override def unique(test: Conn ?=> Any): Any = run(test, Uniques_sqlite())
  override def validation(test: Conn ?=> Any): Any = run(test, Validation_sqlite())
  override def social0(test: Conn ?=> Any): Any = run(test, SocialApp_overview_sqlite())
  override def social1(test: Conn ?=> Any): Any = run(test, SocialApp1_roles_sqlite())
  override def social2(test: Conn ?=> Any): Any = run(test, SocialApp2_role_actions_sqlite())
  override def social3(test: Conn ?=> Any): Any = run(test, SocialApp3_attr_roles_sqlite())
  override def social4(test: Conn ?=> Any): Any = run(test, SocialApp4_attr_update_sqlite())
  override def rawAccess(test: Conn ?=> Any): Any = run(test, SocialApp_raw_access_sqlite())
  override def segments(test: Conn ?=> Any): Any = run(test, Segments_sqlite())
}