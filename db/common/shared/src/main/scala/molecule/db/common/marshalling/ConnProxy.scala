package molecule.db.common.marshalling

import java.util.UUID
import molecule.core.util.BaseHelpers
import molecule.db.common.api.MetaDb

sealed trait ConnProxy {

  /** Unique internal identifier for cached proxy connection on server side */
  val uuid: UUID

  /** Various meta-information about the database */
  val metaDb: MetaDb

  /** Initial SQL commands before schema transaction */
  val initSql: String
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