package molecule.db.common.marshalling

import java.util.UUID
import molecule.base.util.BaseHelpers
import molecule.db.common.api.MetaDb
import molecule.db.common.marshalling.dbView.DbView

sealed trait ConnProxy {

  /** Unique internal identifier for cached proxy connection on server side */
  val uuid: UUID

  /** Various meta-information about the database */
  val metaDb: MetaDb

  /** Initial SQL commands before schema transaction */
  val initSql: String

  /** Internal holder of optional alternative Db view (asOf, since). Used by Datomic only */
  val dbView: Option[DbView] = None
}


case class JdbcProxy(
  url: String,
  override val uuid: UUID,
  override val metaDb: MetaDb,
  override val initSql: String = "",
) extends ConnProxy


object JdbcProxy extends BaseHelpers {
  def apply(url: String, metaDb: MetaDb): JdbcProxy = JdbcProxy(
    url,
    UUID.randomUUID(),
    metaDb,
  )

  def apply(url: String, metaDb: MetaDb, initSql: String): JdbcProxy = JdbcProxy(
    url,
    UUID.randomUUID(),
    metaDb,
    initSql
  )
}

case class DatomicProxy(
  protocol: String,
  dbIdentifier: String,
  override val uuid: UUID,
  override val metaDb: MetaDb,
  override val initSql: String = "",
  override val dbView: Option[DbView] = None,
) extends ConnProxy


object DatomicProxy {
  def apply(
    protocol: String,
    dbIdentifier: String,
    metaDb: MetaDb
  ): DatomicProxy = DatomicProxy(
    protocol,
    dbIdentifier,
    UUID.randomUUID(),
    metaDb,
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
    dbView = dbView,
  )
}

