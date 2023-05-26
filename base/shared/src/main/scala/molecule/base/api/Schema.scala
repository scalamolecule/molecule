package molecule.base.api

import molecule.base.ast.SchemaAST._


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

  /** Database-dependent sql schema */
  def sqlSchema(db: String): String
}
