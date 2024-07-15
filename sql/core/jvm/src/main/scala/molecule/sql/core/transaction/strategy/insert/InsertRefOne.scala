package molecule.sql.core.transaction.strategy.insert

import java.sql.Connection
import molecule.sql.core.transaction.strategy.{SqlOps, SqlAction}

case class InsertRefOne(
  parent: SqlAction,
  sqlConn: Connection,
  dbOps: SqlOps,
  ns: String,
  refAttr: String,
  refNs: String,
) extends InsertBase(sqlConn, dbOps, refNs) {

  def fromTop: SqlAction = parent.fromTop

  override def execute: List[Long] = {
    val List(refId) = insert

    // Add ref id from parent to ref
    val refAttrIndex = parent.paramIndex
    parent.add(refAttr, (ps: PS) => ps.setLong(refAttrIndex, refId))

    List(refId)
  }

  override def backRef: SqlAction = parent

  override def toString: String = render(0)
  override def render(indent: Int): String = render(indent, "SaveRefOne")
}
