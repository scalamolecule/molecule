package molecule.sql.core.transaction.strategy.insert

import java.sql.Connection
import molecule.sql.core.transaction.strategy.SqlOps

case class InsertRefOne(
  parent: InsertAction,
  sqlConn: Connection,
  sqlOps: SqlOps,
  ns: String,
  refAttr: String,
  refNs: String,
) extends InsertAction(sqlConn, sqlOps, refNs) {

  override def initialAction: InsertAction = parent.initialAction
  override def backRef: InsertAction = parent

  override def execute: List[Long] = {
    val refIds = insert

    // Add ref id from parent to ref for all inserted rows
    val refAttrIndex = parent.paramIndex(refAttr)
    val refSetters   = parent.rowSetters.iterator
    refIds.foreach { refId =>
      val refSettersForRow = refSetters.next()
      refSettersForRow += (
        (ps: PS) => {
//          println(s"3--  $ns.$refAttr($refAttrIndex) = $refId")
          ps.setLong(refAttrIndex, refId)
        })
    }
    refIds
  }

  override def render(indent: Int): String = {
    // Add refAttr to parent insert
    parent.paramIndex(refAttr)
    recurseRender(indent, "InsertRefOne")
  }
}
