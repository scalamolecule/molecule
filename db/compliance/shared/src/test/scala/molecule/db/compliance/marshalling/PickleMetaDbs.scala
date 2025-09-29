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

object PickleMetaDbs {

  def apply(pickleMetaDb: CompositePickler[MetaDb]): Unit = {
    pickleMetaDb.addConcreteType[JoinTable_h2]
    pickleMetaDb.addConcreteType[Types_h2]
    pickleMetaDb.addConcreteType[Refs_h2]
    pickleMetaDb.addConcreteType[Uniques_h2]
    pickleMetaDb.addConcreteType[Validation_h2]
    pickleMetaDb.addConcreteType[Segments_h2]

    pickleMetaDb.addConcreteType[JoinTable_mariadb]
    pickleMetaDb.addConcreteType[Types_mariadb]
    pickleMetaDb.addConcreteType[Refs_mariadb]
    pickleMetaDb.addConcreteType[Uniques_mariadb]
    pickleMetaDb.addConcreteType[Validation_mariadb]
    pickleMetaDb.addConcreteType[Segments_mariadb]

    pickleMetaDb.addConcreteType[JoinTable_mysql]
    pickleMetaDb.addConcreteType[Types_mysql]
    pickleMetaDb.addConcreteType[Refs_mysql]
    pickleMetaDb.addConcreteType[Uniques_mysql]
    pickleMetaDb.addConcreteType[Validation_mysql]
    pickleMetaDb.addConcreteType[Segments_mysql]

    pickleMetaDb.addConcreteType[JoinTable_postgresql]
    pickleMetaDb.addConcreteType[Types_postgresql]
    pickleMetaDb.addConcreteType[Refs_postgresql]
    pickleMetaDb.addConcreteType[Uniques_postgresql]
    pickleMetaDb.addConcreteType[Validation_postgresql]
    pickleMetaDb.addConcreteType[Segments_postgresql]

    pickleMetaDb.addConcreteType[JoinTable_sqlite]
    pickleMetaDb.addConcreteType[Types_sqlite]
    pickleMetaDb.addConcreteType[Refs_sqlite]
    pickleMetaDb.addConcreteType[Uniques_sqlite]
    pickleMetaDb.addConcreteType[Validation_sqlite]
    pickleMetaDb.addConcreteType[Segments_sqlite]
  }
}
