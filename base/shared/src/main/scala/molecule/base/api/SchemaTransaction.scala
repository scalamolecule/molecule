package molecule.base.api

import molecule.base.ast.SchemaAST._


/** Schema transaction data interface with data sources for various databases.
  * */
trait SchemaTransaction {

  val metaSchema: MetaSchema

  /** Namespace name -> MetaNs */
  val nsMap: Map[String, MetaNs]

  /** Attribute name -> (Cardinality, Scala type, Required attributes) */
  val attrMap: Map[String, (Cardinality, String, Seq[String])]

  val uniqueAttrs: List[String]



  /** Edn data strings to transact Datomic Peer schema. */
  val datomicSchema    : String
  val datomicPartitions: String
  val datomicAliases   : String
}