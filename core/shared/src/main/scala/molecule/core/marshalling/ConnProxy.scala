package molecule.core.marshalling

import java.util.UUID
import molecule.base.api._
import molecule.core.marshalling.dbView.DbView

sealed trait ConnProxy {

  val schema: Schema

//  val reserved: Option[Reserved] = None

//  val metaDomain : MetaDomain                               = schema.metaDomain
//  val entityMap  : Map[String, MetaEntity]                  = schema.entityMap
//  val attrMap    : Map[String, (Card, String, Seq[String])] = schema.attrMap
//  val uniqueAttrs: List[String]                             = schema.uniqueAttrs

//  /** Internal holder of optional alternative Db view (asOf, since, widh). Used by Datomic */
  val dbView: Option[DbView] = None

  /** Unique internal identifier for cached proxy connection on server side */
  val uuid: UUID = UUID.randomUUID()
}


case class DatomicProxy(
  protocol: String,
  dbIdentifier: String,
  override val schema: Schema,
//  override val reserved: Option[Reserved] = None,
  override val dbView: Option[DbView] = None,
//  override val uuid: UUID = UUID.randomUUID(),
) extends ConnProxy {
//  val datomicPartitions: String = schema.datomicPartitions
//  val datomicSchema    : String = schema.datomicSchema
//  val datomicAliases   : String = schema.datomicAliases
}


sealed abstract class JdbcProxy(
  val url: String,
  override val schema: Schema,
  val schemaStr: String,
) extends ConnProxy


case class JdbcProxy_h2(
  override val url: String,
  override val schema: Schema_h2,
  adhocSchemaStr: String = "",
) extends JdbcProxy(
  url, schema, if (adhocSchemaStr.nonEmpty) adhocSchemaStr else schema.schemaData.head
)

case class JdbcProxy_mariadb(
  override val url: String,
  override val schema: Schema_mariadb,
  adhocSchemaStr: String = "",
) extends JdbcProxy(
  url, schema, if (adhocSchemaStr.nonEmpty) adhocSchemaStr else schema.schemaData.head
)

case class JdbcProxy_mysql(
  override val url: String,
  override val schema: Schema_mysql,
  adhocSchemaStr: String = "",
) extends JdbcProxy(
  url, schema, if (adhocSchemaStr.nonEmpty) adhocSchemaStr else schema.schemaData.head
)

case class JdbcProxy_postgres(
  override val url: String,
  override val schema: Schema_postgres,
  adhocSchemaStr: String = "",
) extends JdbcProxy(
  url, schema, if (adhocSchemaStr.nonEmpty) adhocSchemaStr else schema.schemaData.head
)

case class JdbcProxy_sqlite(
  override val url: String,
  override val schema: Schema_sqlite,
  adhocSchemaStr: String = "",
) extends JdbcProxy(
  url, schema, if (adhocSchemaStr.nonEmpty) adhocSchemaStr else schema.schemaData.head
)


