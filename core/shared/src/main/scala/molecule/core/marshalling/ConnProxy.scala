package molecule.core.marshalling

import java.util.UUID
import molecule.base.ast._
import molecule.core.marshalling.dbView.DbView

sealed trait ConnProxy {

  val metaSchema : MetaSchema
  val nsMap      : Map[String, MetaNs]
  val attrMap    : Map[String, (Card, String, Seq[String])]
  val uniqueAttrs: List[String]

  /** Internal holder of optional alternative Db view (asOf, since, widh). Used by Datomic */
  val dbView: Option[DbView]

  /** Unique internal identifier for cached proxy connection on server side */
  val uuid: UUID

  val reserved: Option[Reserved]
}


case class DatomicProxy(
  protocol: String,
  dbIdentifier: String,

  datomicPartitions: String,
  datomicSchema: String,
  datomicAliases: String,

  override val metaSchema: MetaSchema,
  override val nsMap: Map[String, MetaNs],
  override val attrMap: Map[String, (Card, String, Seq[String])],
  override val uniqueAttrs: List[String],
  override val dbView: Option[DbView] = None,
  override val uuid: UUID = UUID.randomUUID(),
  override val reserved: Option[Reserved] = None,
) extends ConnProxy


case class JdbcProxy(
  url: String,
  createSchema: String,

  override val metaSchema: MetaSchema,
  override val nsMap: Map[String, MetaNs],
  override val attrMap: Map[String, (Card, String, Seq[String])],
  override val uniqueAttrs: List[String],
  override val dbView: Option[DbView] = None,
  override val uuid: UUID = UUID.randomUUID(),
  override val reserved: Option[Reserved] = None,
  useTestContainer: Boolean = false
) extends ConnProxy
