package molecule.sql.core.transaction.op

import java.sql.Connection
import molecule.sql.core.transaction.strategy.TxStrategy

case class SaveNs(
  sqlConn: Connection,
  ns: String,
)(implicit dbOps: DbOps) extends SaveBase(sqlConn, dbOps, ns) {

  // Initial namespace
  def fromTop: TxStrategy = this

  override def execute: List[Long] = {
    insert
  }

  override def toString: String = render(0)
  override def render(indent: Int): String = render(indent, "SaveNs")
}
