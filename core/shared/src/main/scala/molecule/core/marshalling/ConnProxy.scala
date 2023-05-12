package molecule.core.marshalling

import java.util.UUID
import molecule.base.api.Schema
import molecule.base.ast.SchemaAST._
import molecule.core.marshalling.dbView.DbView
import molecule.core.util.MetaModelUtils

sealed trait ConnProxy {

  val schema: Schema

  /** Internal holder of optional alternative Db view (asOf, since, widh) */
  val dbView: Option[DbView]

  /** Unique internal identifier for cached proxy connection on server side */
  val uuid: UUID
}


case class DatomicPeerProxy(
  protocol: String,
  dbIdentifier: String,
  schema: Schema,
  dbView: Option[DbView] = None,
  uuid: UUID = UUID.randomUUID(),
  isFreeVersion: Boolean = true
) extends ConnProxy


case class SqlProxy(
  url: String,
  schema: Schema,
  dbView: Option[DbView] = None,
  uuid: UUID = UUID.randomUUID(),
  isFreeVersion: Boolean = true
) extends ConnProxy
