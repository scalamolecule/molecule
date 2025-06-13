package molecule.db.core.marshalling

import java.util.UUID
import molecule.base.metaModel.{Card, MetaEntity}
import molecule.core.dataModel.Schema
import molecule.db.core.marshalling.dbView.DbView

sealed trait ConnProxy {

  /** Unique internal identifier for cached proxy connection on server side */
  val uuid: UUID

  /** String for transacting the schema of the database */
  val schemaStr: String

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

  /** Internal holder of optional alternative Db view (asOf, since, widh). Used by Datomic only */
  val dbView: Option[DbView] = None
}


case class JdbcProxy(
  url: String,
  override val uuid: UUID,
  override val schemaStr: String,
  override val entityMap: Map[String, MetaEntity],
  override val attrMap: Map[String, (Card, String, Seq[String])],
  override val uniqueAttrs: List[String],
  override val schemaData: List[String],
  override val reservedEntities: IArray[Byte] = IArray.empty[Byte],
  override val reservedAttributes: IArray[Byte] = IArray.empty[Byte]
) extends ConnProxy

object JdbcProxy {
  def apply(url: String, schema: Schema): JdbcProxy = JdbcProxy(
    url,
    UUID.randomUUID(),
    schema.schemaData.head,
    schema.entityMap,
    schema.attrMap,
    schema.uniqueAttrs,
    schema.schemaData,
    schema.reservedEntities,
    schema.reservedAttributes
  )

  def apply(url: String, schema: Schema, initSql: String): JdbcProxy = JdbcProxy(
    url,
    UUID.randomUUID(),
    if (initSql.isEmpty) schema.schemaData.head else initSql,
    schema.entityMap,
    schema.attrMap,
    schema.uniqueAttrs,
    schema.schemaData,
    schema.reservedEntities,
    schema.reservedAttributes,
  )
}

case class DatomicProxy(
  protocol: String,
  dbIdentifier: String,
  override val uuid: UUID,
  override val dbView: Option[DbView] = None,
  override val schemaStr: String,
  override val entityMap: Map[String, MetaEntity],
  override val attrMap: Map[String, (Card, String, Seq[String])],
  override val uniqueAttrs: List[String],
  override val schemaData: List[String],
  override val reservedEntities: IArray[Byte] = IArray.empty[Byte],
  override val reservedAttributes: IArray[Byte] = IArray.empty[Byte],

) extends ConnProxy


object DatomicProxy {
  def apply(
    protocol: String,
    dbIdentifier: String,
    schema: Schema
  ): DatomicProxy = DatomicProxy(
    protocol,
    dbIdentifier,
    UUID.randomUUID(),
    None,
    schema.schemaData.head,
    schema.entityMap,
    schema.attrMap,
    schema.uniqueAttrs,
    schema.schemaData,
    schema.reservedEntities,
    schema.reservedAttributes
  )

  def apply(
    protocol: String,
    dbIdentifier: String,
    schema: Schema,
    dbView: Option[DbView]
  ): DatomicProxy = DatomicProxy(
    protocol,
    dbIdentifier,
    UUID.randomUUID(),
    dbView,
    schema.schemaData.head,
    schema.entityMap,
    schema.attrMap,
    schema.uniqueAttrs,
    schema.schemaData,
    schema.reservedEntities,
    schema.reservedAttributes
  )
}

