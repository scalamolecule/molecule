package molecule.db.compliance.marshalling

import boopickle.CompositePickler
import boopickle.Default.*
import molecule.db.common.api.MetaDb
import molecule.db.compliance.domains.dsl.JoinTable.metadb.*
import molecule.db.compliance.domains.dsl.Refs.metadb.*
import molecule.db.compliance.domains.dsl.Segments.metadb.*
import molecule.db.compliance.domains.dsl.Types.metadb.*
import molecule.db.compliance.domains.dsl.Uniques.metadb.*
import molecule.db.compliance.domains.dsl.Validation.metadb.*
import molecule.db.compliance.domains.dsl.SocialApp1_roles.metadb.*
import molecule.db.compliance.domains.dsl.SocialApp2_role_actions.metadb.*
import molecule.db.compliance.domains.dsl.SocialApp3_attr_roles.metadb.*
import molecule.db.compliance.domains.dsl.SocialApp4_attr_update.metadb.*
import molecule.db.compliance.domains.dsl.SocialApp_overview.metadb.*
import molecule.db.compliance.domains.dsl.SocialApp_raw_access.metadb.*

object PickleMetaDbs {

  def apply(pickleMetaDb: CompositePickler[MetaDb]): Unit = {
    pickleMetaDb.addConcreteType[JoinTable_h2]
    pickleMetaDb.addConcreteType[Types_h2]
    pickleMetaDb.addConcreteType[Refs_h2]
    pickleMetaDb.addConcreteType[Uniques_h2]
    pickleMetaDb.addConcreteType[Validation_h2]
    pickleMetaDb.addConcreteType[Segments_h2]
    pickleMetaDb.addConcreteType[SocialApp1_roles_h2]
    pickleMetaDb.addConcreteType[SocialApp2_role_actions_h2]
    pickleMetaDb.addConcreteType[SocialApp3_attr_roles_h2]
    pickleMetaDb.addConcreteType[SocialApp4_attr_update_h2]
    pickleMetaDb.addConcreteType[SocialApp_overview_h2]
    pickleMetaDb.addConcreteType[SocialApp_raw_access_h2]

    pickleMetaDb.addConcreteType[JoinTable_mariadb]
    pickleMetaDb.addConcreteType[Types_mariadb]
    pickleMetaDb.addConcreteType[Refs_mariadb]
    pickleMetaDb.addConcreteType[Uniques_mariadb]
    pickleMetaDb.addConcreteType[Validation_mariadb]
    pickleMetaDb.addConcreteType[Segments_mariadb]
    pickleMetaDb.addConcreteType[SocialApp1_roles_mariadb]
    pickleMetaDb.addConcreteType[SocialApp2_role_actions_mariadb]
    pickleMetaDb.addConcreteType[SocialApp3_attr_roles_mariadb]
    pickleMetaDb.addConcreteType[SocialApp4_attr_update_mariadb]
    pickleMetaDb.addConcreteType[SocialApp_overview_mariadb]
    pickleMetaDb.addConcreteType[SocialApp_raw_access_mariadb]

    pickleMetaDb.addConcreteType[JoinTable_mysql]
    pickleMetaDb.addConcreteType[Types_mysql]
    pickleMetaDb.addConcreteType[Refs_mysql]
    pickleMetaDb.addConcreteType[Uniques_mysql]
    pickleMetaDb.addConcreteType[Validation_mysql]
    pickleMetaDb.addConcreteType[Segments_mysql]
    pickleMetaDb.addConcreteType[SocialApp1_roles_mysql]
    pickleMetaDb.addConcreteType[SocialApp2_role_actions_mysql]
    pickleMetaDb.addConcreteType[SocialApp3_attr_roles_mysql]
    pickleMetaDb.addConcreteType[SocialApp4_attr_update_mysql]
    pickleMetaDb.addConcreteType[SocialApp_overview_mysql]
    pickleMetaDb.addConcreteType[SocialApp_raw_access_mysql]

    pickleMetaDb.addConcreteType[JoinTable_postgresql]
    pickleMetaDb.addConcreteType[Types_postgresql]
    pickleMetaDb.addConcreteType[Refs_postgresql]
    pickleMetaDb.addConcreteType[Uniques_postgresql]
    pickleMetaDb.addConcreteType[Validation_postgresql]
    pickleMetaDb.addConcreteType[Segments_postgresql]
    pickleMetaDb.addConcreteType[SocialApp1_roles_postgresql]
    pickleMetaDb.addConcreteType[SocialApp2_role_actions_postgresql]
    pickleMetaDb.addConcreteType[SocialApp3_attr_roles_postgresql]
    pickleMetaDb.addConcreteType[SocialApp4_attr_update_postgresql]
    pickleMetaDb.addConcreteType[SocialApp_overview_postgresql]
    pickleMetaDb.addConcreteType[SocialApp_raw_access_postgresql]

    pickleMetaDb.addConcreteType[JoinTable_sqlite]
    pickleMetaDb.addConcreteType[Types_sqlite]
    pickleMetaDb.addConcreteType[Refs_sqlite]
    pickleMetaDb.addConcreteType[Uniques_sqlite]
    pickleMetaDb.addConcreteType[Validation_sqlite]
    pickleMetaDb.addConcreteType[Segments_sqlite]
    pickleMetaDb.addConcreteType[SocialApp1_roles_sqlite]
    pickleMetaDb.addConcreteType[SocialApp2_role_actions_sqlite]
    pickleMetaDb.addConcreteType[SocialApp3_attr_roles_sqlite]
    pickleMetaDb.addConcreteType[SocialApp4_attr_update_sqlite]
    pickleMetaDb.addConcreteType[SocialApp_overview_sqlite]
    pickleMetaDb.addConcreteType[SocialApp_raw_access_sqlite]
  }
}
