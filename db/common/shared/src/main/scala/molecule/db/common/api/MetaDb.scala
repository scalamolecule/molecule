package molecule.db.common.api

import molecule.core.dataModel.Value

/** Metadata about the database */
trait MetaDb {

  /** Resource path to SQL schema file to create the database */
  val schemaResourcePath: String

  /** entity -> List[mandatory-attribute] */
  val mandatoryAttrs: Map[String, List[String]]

  /** entity -> List[(entity.attr, mandatory refEntity)] */
  val mandatoryRefs: Map[String, List[(String, String)]]

  /** attr -> (Value, Scala type, required attributes) */
  val attrData: Map[String, (Value, String, List[String])]

  /** Attributes requiring unique values */
  val uniqueAttrs: List[String]

  /** Indexed flags for reserved entity names */
  val reservedEntities: IArray[Byte] = IArray.empty[Byte]

  /** Indexed flags for reserved attribute names */
  val reservedAttributes: IArray[Byte] = IArray.empty[Byte]


  // Access control -------------------------------------------------------

  /** Role name to bit index (0-31) */
  val roleIndex: Map[String, Int] = Map.empty[String, Int]

  /** Bitwise role access for entities on query action
    * Each Int is a bitmask where bit N = role N can query
    * Supports up to 32 roles (bits 0-31)
    */
  val queryAccessEntities: IArray[Int] = IArray.empty[Int]

  /** Bitwise role access for attributes on query action
    * Each Int is a bitmask where bit N = role N can query
    */
  val queryAccessAttributes: IArray[Int] = IArray.empty[Int]
}

trait MetaDb_h2 extends MetaDb
trait MetaDb_mariadb extends MetaDb
trait MetaDb_mysql extends MetaDb
trait MetaDb_postgresql extends MetaDb
trait MetaDb_sqlite extends MetaDb