package molecule.sql.core.transaction.strategy.insert

import java.sql.Connection
import molecule.sql.core.transaction.strategy.{SqlOps, SqlAction}

case class InsertNs(
  sqlConn: Connection,
  ns: String,
)(implicit dbOps: SqlOps) extends InsertBase(sqlConn, dbOps, ns) {

  // Initial namespace
  def fromTop: SqlAction = this

  override def execute: List[Long] = {
    insert
  }

  override def toString: String = render(0)
  override def render(indent: Int): String = render(indent, "SaveNs")
}
