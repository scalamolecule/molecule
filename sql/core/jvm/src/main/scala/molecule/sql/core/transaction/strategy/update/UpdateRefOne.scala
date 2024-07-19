package molecule.sql.core.transaction.strategy.update

import java.sql.Connection
import molecule.sql.core.transaction.strategy.SqlOps

case class UpdateRefOne(
  parent: UpdateAction,
  sqlConn: Connection,
  sqlOps: SqlOps,
  ns: String,
  refAttr: String,
  refNs: String,
) extends UpdateAction(sqlConn, sqlOps, refNs) {

  override def initialAction: UpdateAction = parent.initialAction
  override def backRef: UpdateAction = parent

  override def execute: List[Long] = {
//    val List(refId) = update
//
//    // Add ref id from parent to ref
//    val refAttrIndex = parent.paramIndex(refAttr)
//    parent.rowSetters.last += ((ps: PS) => ps.setLong(refAttrIndex, refId))
//
//    List(refId)

    ???
  }

  override def render(indent: Int): String = {
    // Add refAttr to parent insert
    parent.paramIndex(refAttr)
    recurseRender(indent, "UpdateRefOne")
  }
}
