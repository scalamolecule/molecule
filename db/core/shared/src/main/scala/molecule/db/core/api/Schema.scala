package molecule.db.core.api

import molecule.base.metaModel.{Card, MetaEntity}

/** Schema transaction data interface
 *
 * Also has metadata about the domain
 * */
trait Schema {

//  val metaDomain: MetaDomain

  /** Entity name -> MetaEntity */
  val entityMap: Map[String, MetaEntity]

  /** Attribute name -> (Cardinality, Scala type, Required attributes) */
  val attrMap: Map[String, (Card, String, Seq[String])]

  /** Attributes requiring unique values */
  val uniqueAttrs: List[String]

  /** Schema creation strings for databases */
  val schemaData: List[String]

  /** Indexed flags for reserved entity names */
  val reservedEntities: IArray[Byte] = IArray.empty[Byte]

  /** Indexed flags for reserved attribute names */
  val reservedAttributes: IArray[Byte] = IArray.empty[Byte]
}

trait Schema_datomic extends Schema
trait Schema_h2 extends Schema
trait Schema_mariadb extends Schema
trait Schema_mysql extends Schema
trait Schema_postgres extends Schema
trait Schema_sqlite extends Schema