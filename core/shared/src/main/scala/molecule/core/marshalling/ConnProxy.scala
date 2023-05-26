package molecule.core.marshalling

import java.util.UUID
import molecule.base.ast.SchemaAST._
import molecule.core.marshalling.dbView.DbView

sealed trait ConnProxy {

  val metaSchema : MetaSchema
  val nsMap      : Map[String, MetaNs]
  val attrMap    : Map[String, (Card, String, Seq[String])]
  val uniqueAttrs: List[String]

  /** Internal holder of optional alternative Db view (asOf, since, widh) */
  val dbView: Option[DbView]

  /** Unique internal identifier for cached proxy connection on server side */
  val uuid: UUID
}


case class DatomicProxy(
  protocol: String,
  dbIdentifier: String,

  datomicPartitions: String,
  datomicSchema: String,
  datomicAliases: String,

  metaSchema: MetaSchema,
  nsMap: Map[String, MetaNs],
  attrMap: Map[String, (Card, String, Seq[String])],
  uniqueAttrs: List[String],
  dbView: Option[DbView] = None,
  uuid: UUID = UUID.randomUUID()
) extends ConnProxy


case class JdbcProxy(
  url: String,
  createSchema: String,

  metaSchema: MetaSchema,
  nsMap: Map[String, MetaNs],
  attrMap: Map[String, (Card, String, Seq[String])],
  uniqueAttrs: List[String],
  dbView: Option[DbView] = None,
  uuid: UUID = UUID.randomUUID()
) extends ConnProxy
