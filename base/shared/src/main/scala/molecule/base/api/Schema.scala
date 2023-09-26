package molecule.base.api

import molecule.base.ast._


/** Schema transaction data interface with data sources for various databases.
 * */
trait Schema {

  val metaSchema: MetaSchema

  /** Namespace name -> MetaNs */
  val nsMap: Map[String, MetaNs]

  /** Attribute name -> (Cardinality, Scala type, Required attributes) */
  val attrMap: Map[String, (Card, String, Seq[String])]

  val uniqueAttrs: List[String]

  /** Edn data strings to transact Datomic Peer schema. */
  val datomicPartitions: String
  val datomicSchema    : String
  val datomicAliases   : String

  /** sql schemas */
  val sqlSchema_h2      : String
  val sqlSchema_mysql   : String
  val sqlSchema_postgres: String

  /** sql reserved keyword detection */
  val sqlReserved_h2      : Option[Reserved]
  val sqlReserved_mysql   : Option[Reserved]
  val sqlReserved_postgres: Option[Reserved]
}
