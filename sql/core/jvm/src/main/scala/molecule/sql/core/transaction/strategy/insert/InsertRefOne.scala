package molecule.sql.core.transaction.strategy.insert

import java.sql.{PreparedStatement => PS}
import molecule.sql.core.transaction.strategy.SqlOps

case class InsertRefOne(
  parent: InsertAction,
  sqlOps: SqlOps,
  ns: String,
  refAttr: String,
  refNs: String,
  refAttrIndex: Int
) extends InsertAction(parent, sqlOps, refNs) {

  override def rootAction: InsertAction = parent.rootAction

  override def process(): Unit = {
    // Process children of ref ns
    children.foreach(_.process())

    // Add ref rows
    insertIntoTable()

    // Add ref ids from parent to ref
    val parentRowSetters = parent.rowSetters.iterator
    val refIds           = ids.iterator
    while (refIds.hasNext) {
      val parentRowSetter = parentRowSetters.next()
      val refId           = refIds.next()
      val refIdSetter     = (ps: PS) => ps.setLong(refAttrIndex, refId)
      parentRowSetter += refIdSetter
    }
  }

  override def curStmt: String = {
    sqlOps.insertStmt(refNs, cols, placeHolders)
  }

  override def render(indent: Int): String = {
    // Add refAttr to parent insert
    parent.setCol(refAttr)
    recurseRender(indent, "RefOne")
  }
}
