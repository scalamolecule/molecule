package molecule.db.common.api

import molecule.base.metaModel.Cardinality

/** Metadata about the database */
trait MetaDb {

  /** Resource path to SQL schema file to create the database */
  val schemaResourcePath: String

  /** entity -> List[mandatory-attribute] */
  val mandatoryAttrs: Map[String, List[String]]

  /** entity -> List[(entity.attr, mandatory refEntity)] */
  val mandatoryRefs: Map[String, List[(String, String)]]

  /** entity -> List[(refAttr, Cardinality, owned refEntity)] */
  val ownedRefs: Map[String, List[(String, Cardinality, String)]]

  /** attr -> (Cardinality, Scala type, required attributes) */
  val attrData: Map[String, (Cardinality, String, List[String])]

  /** Attributes requiring unique values */
  val uniqueAttrs: List[String]

  /** Indexed flags for reserved entity names */
  val reservedEntities: IArray[Byte] = IArray.empty[Byte]

  /** Indexed flags for reserved attribute names */
  val reservedAttributes: IArray[Byte] = IArray.empty[Byte]
}

trait MetaDb_h2 extends MetaDb
trait MetaDb_mariadb extends MetaDb
trait MetaDb_mysql extends MetaDb
trait MetaDb_postgres extends MetaDb
trait MetaDb_sqlite extends MetaDb