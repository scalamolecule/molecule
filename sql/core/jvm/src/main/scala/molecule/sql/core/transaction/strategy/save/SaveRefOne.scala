package molecule.sql.core.transaction.strategy.save

import java.sql.Connection
import molecule.sql.core.transaction.strategy.SqlOps

case class SaveRefOne(
  parent: SaveAction,
  sqlConn: Connection,
  sqlOps: SqlOps,
  ns: String,
  refAttr: String,
  refNs: String,
) extends SaveAction(sqlConn, sqlOps, refNs) {

  override def initialAction: SaveAction = parent.initialAction
  override def backRef: SaveAction = parent

  override def execute: List[Long] = {
    val List(refId) = insert

    // Add ref id from parent to ref
    val refAttrIndex = parent.paramIndex(refAttr)
    parent.rowSetters.last += ((ps: PS) => ps.setLong(refAttrIndex, refId))

    List(refId)
  }

  override def render(indent: Int): String = {
    // Add refAttr to parent insert
    parent.paramIndex(refAttr)
    recurseRender(indent, "SaveRefOne")
  }
}
