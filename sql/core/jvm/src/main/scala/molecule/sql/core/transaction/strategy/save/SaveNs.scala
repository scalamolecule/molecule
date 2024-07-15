package molecule.sql.core.transaction.strategy.save

import java.sql.Connection
import molecule.sql.core.transaction.strategy.{SqlOps, SqlAction}

case class SaveNs(
  sqlConn: Connection,
  ns: String,
)(implicit dbOps: SqlOps) extends SaveBase(sqlConn, dbOps, ns) {

  // Initial namespace
  def fromTop: SqlAction = this

  override def execute: List[Long] = {
    // Initial action of top node to save its graph underneath of relationships
    insert
  }

  override def toString: String = render(0)
  override def render(indent: Int): String = render(indent, "SaveNs")
}
