package molecule.db.core.marshalling

import java.util.UUID
import molecule.base.util.BaseHelpers
import molecule.db.core.api.MetaDb
import molecule.db.core.marshalling.dbView.DbView
import scala.io.Source
import scala.util.Using

sealed trait ConnProxy {

  /** Unique internal identifier for cached proxy connection on server side */
  val uuid: UUID

  /** Various meta-information about the database */
  val metaDb: MetaDb

  /** String for transacting the schema of the database */
  val schemaStr: String

  /** Internal holder of optional alternative Db view (asOf, since). Used by Datomic only */
  val dbView: Option[DbView] = None
}


case class JdbcProxy(
  url: String,
  override val uuid: UUID,
  override val metaDb: MetaDb,
  override val schemaStr: String,
) extends ConnProxy


trait SchemaLoader {
  def getSchema(schemaResourcePath: String): String = {
    Using(Source.fromResource(schemaResourcePath)) { source =>
      source.mkString
    }.getOrElse(throw new Exception(
      s"Couldn't find database schema file resources/$schemaResourcePath."
    ))
  }
}

object JdbcProxy extends SchemaLoader with BaseHelpers {
  def apply(url: String, metaDb: MetaDb): JdbcProxy = JdbcProxy(
    url,
    UUID.randomUUID(),
    metaDb,
    getSchema(metaDb.schemaResourcePath)
  )

  def apply(url: String, metaDb: MetaDb, initSql: String): JdbcProxy = JdbcProxy(
    url,
    UUID.randomUUID(),
    metaDb,
    if (initSql.isEmpty) getSchema(metaDb.schemaResourcePath) else initSql,
  )
}

case class DatomicProxy(
  protocol: String,
  dbIdentifier: String,
  override val uuid: UUID,
  override val metaDb: MetaDb,
  override val schemaStr: String,
  override val dbView: Option[DbView] = None,
) extends ConnProxy


object DatomicProxy extends SchemaLoader {
  def apply(
    protocol: String,
    dbIdentifier: String,
    metaDb: MetaDb
  ): DatomicProxy = DatomicProxy(
    protocol,
    dbIdentifier,
    UUID.randomUUID(),
    metaDb,
    getSchema(metaDb.schemaResourcePath),
    None,
  )

  def apply(
    protocol: String,
    dbIdentifier: String,
    metaDb: MetaDb,
    dbView: Option[DbView]
  ): DatomicProxy = DatomicProxy(
    protocol,
    dbIdentifier,
    UUID.randomUUID(),
    metaDb,
    getSchema(metaDb.schemaResourcePath),
    dbView,
  )
}

