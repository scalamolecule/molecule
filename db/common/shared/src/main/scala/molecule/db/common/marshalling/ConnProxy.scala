package molecule.db.common.marshalling

import java.util.UUID
import molecule.core.util.BaseHelpers
import molecule.db.common.api.MetaDb

enum Mode:
  case Dev    // Permissive auth, verbose errors (development)
  case Test   // Strict auth, verbose errors (testing auth rules)
  case Stage  // Strict auth, verbose errors (pre-production)
  case Prod   // Strict auth, sanitized errors (production)

sealed trait ConnProxy {

  /** Unique internal identifier for cached proxy connection on server side */
  val uuid: UUID

  /** Various meta-information about the database */
  val metaDb: MetaDb

  /** Initial SQL commands before schema transaction */
  val initSql: String

  /** Environment mode (Dev allows more permissive behavior, Prod is strict) */
  val mode: Mode
}


case class JdbcProxy(
  url: String,
  override val uuid: UUID,
  override val metaDb: MetaDb,
  override val initSql: String = "",
  override val mode: Mode = Mode.Prod,  // Secure by default
) extends ConnProxy


object JdbcProxy extends BaseHelpers {
  def apply(url: String, metaDb: MetaDb): JdbcProxy = JdbcProxy(
    url,
    UUID.randomUUID(),
    metaDb,
    "",
    Mode.Prod
  )

  def apply(url: String, metaDb: MetaDb, initSql: String): JdbcProxy = JdbcProxy(
    url,
    UUID.randomUUID(),
    metaDb,
    initSql,
    Mode.Prod
  )

  def apply(url: String, metaDb: MetaDb, mode: Mode): JdbcProxy = JdbcProxy(
    url,
    UUID.randomUUID(),
    metaDb,
    "",
    mode
  )

  def apply(url: String, metaDb: MetaDb, initSql: String, mode: Mode): JdbcProxy = JdbcProxy(
    url,
    UUID.randomUUID(),
    metaDb,
    initSql,
    mode
  )
}