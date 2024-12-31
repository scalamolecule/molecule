package molecule.base.api

import molecule.base.ast._


/** Schema transaction data interface
 *
 * Also has metadata about the domain
 * */
trait Schema {

  val metaDomain: MetaDomain

  /** Entity name -> MetaEntity */
  val entityMap: Map[String, MetaEntity]

  /** Attribute name -> (Cardinality, Scala type, Required attributes) */
  val attrMap: Map[String, (Card, String, Seq[String])]

  /** Attributes requiring unique values */
  val uniqueAttrs: List[String]

  //  /** Edn data stringSet to transact Datomic Peer schema. */
  //  val datomicPartitions: String
  //  val datomicSchema    : String
  //  val datomicAliases   : String
  //
  //  /** sql schemas */
  //  val sqlSchema_h2      : String
  //  val sqlSchema_mariadb : String
  //  val sqlSchema_mysql   : String
  //  val sqlSchema_postgres: String
  //  val sqlSchema_sqlite  : String
  //
  //  /** sql reserved keyword detection */
  //  val sqlReserved_h2      : Option[Reserved]
  //  val sqlReserved_mariadb : Option[Reserved]
  //  val sqlReserved_mysql   : Option[Reserved]
  //  val sqlReserved_postgres: Option[Reserved]
  //  val sqlReserved_sqlite  : Option[Reserved]


  /** Schema creation strings for databases */
  val schemaData: List[String]

  /** Indexed flags for reserved entity names */
  val reservedEntities: Array[Boolean] = new Array[Boolean](0)

  /** Indexed flags for reserved attribute names */
  val reservedAttributes: Array[Boolean] = new Array[Boolean](0)
}

trait Schema_datomic extends Schema
trait Schema_h2 extends Schema
trait Schema_mariadb extends Schema
trait Schema_mysql extends Schema
trait Schema_postgres extends Schema
trait Schema_sqlite extends Schema