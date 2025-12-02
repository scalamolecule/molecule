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

  /** Access control
   *
   * Bitwise accessors to all actions
   * Each Int is a bitmask where bit N = role N can query
   * Supports up to 32 roles (bits 0-31)
   * -1 = public (all roles), 0 = no access, other = role bitmask
   */
  val roleIndex: Map[String, Int] = Map.empty[String, Int]

  /** Role action bitmasks - which roles have which actions
   * Bitmask where bit N = role N has this action
   * Used to check authenticated user's permissions on public entities
   */
  val roleQueryAction: Int = 0
  val roleSubscribeAction: Int = 0
  val roleSaveAction: Int = 0
  val roleInsertAction: Int = 0
  val roleUpdateAction: Int = 0
  val roleDeleteAction: Int = 0

  val queryAccessEntities  : IArray[Int] = IArray.empty[Int]
  val queryAccessAttributes: IArray[Int] = IArray.empty[Int]

  val subscribeAccessEntities  : IArray[Int] = IArray.empty[Int]
  val subscribeAccessAttributes: IArray[Int] = IArray.empty[Int]

  val saveAccessEntities  : IArray[Int] = IArray.empty[Int]
  val saveAccessAttributes: IArray[Int] = IArray.empty[Int]

  val insertAccessEntities  : IArray[Int] = IArray.empty[Int]
  val insertAccessAttributes: IArray[Int] = IArray.empty[Int]

  val updateAccessEntities  : IArray[Int] = IArray.empty[Int]
  val updateAccessAttributes: IArray[Int] = IArray.empty[Int]

  val deleteAccessEntities  : IArray[Int] = IArray.empty[Int]
  val deleteAccessAttributes: IArray[Int] = IArray.empty[Int]


  /**
   * Maps each entity index to the list of attribute indices belonging to that entity.
   * Excludes 'id' attributes since they are not checked during permission validation.
   *
   * Index: entity coordinate (coord(0))
   * Value: array of attribute coordinates (coord(1)) for that entity, excluding id
   */
  val entityAttributesNoId: IArray[IArray[Int]] = IArray.empty[IArray[Int]]

  /**
   * Maps attribute index to attribute name for error messages.
   * Index: attribute coordinate (coord(1))
   * Value: "EntityName.attributeName"
   *
   * This is only used for generating human-readable error messages.
   */
  val attrNames: IArray[String] = IArray.empty[String]
}

trait MetaDb_h2 extends MetaDb
trait MetaDb_mariadb extends MetaDb
trait MetaDb_mysql extends MetaDb
trait MetaDb_postgresql extends MetaDb
trait MetaDb_sqlite extends MetaDb