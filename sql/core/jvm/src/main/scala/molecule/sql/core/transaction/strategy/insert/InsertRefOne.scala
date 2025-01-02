package molecule.sql.core.transaction.strategy.insert

import java.sql.{PreparedStatement => PS}
import molecule.sql.core.transaction.strategy.SqlOps

case class InsertRefOne(
  parent: InsertAction,
  sqlOps: SqlOps,
  ent: String,
  refAttr: String,
  ref: String,
  refAttrIndex: Int,
  rowCount: Int
) extends InsertAction(parent, sqlOps, ref, rowCount) {

  override def rootAction: InsertAction = parent.rootAction

  override def process(): Unit = {
    // Process children of ref
    children.foreach(_.process())

    // Add ref rows (don't enforce empty row)
    insert(false)

    // Add ref ids from parent (previous entity) to ref
    val refIds = ids.iterator
    parent match {
      case _: InsertOptRef =>
        // make ref only when parent/prev entity has value
        parent.rowSetters.zip(parent.optionalDefineds).foreach {
          case (setter, true) =>
            setter += ((ps: PS) => ps.setLong(refAttrIndex, refIds.next()))

          case (setter, _) =>
            setter += ((ps: PS) => ps.setNull(refAttrIndex, 0))
        }

      case _ =>
        val parentRowSetters = parent.rowSetters.iterator
        while (refIds.hasNext) {
          val parentRowSetter = parentRowSetters.next()
          val refId           = refIds.next()
          val refIdSetter     = (ps: PS) => ps.setLong(refAttrIndex, refId)
          parentRowSetter += refIdSetter
        }
    }
  }

  override def curStmt: String = {
    sqlOps.insertStmt(ref, cols, placeHolders)
  }

  override def render(indent: Int): String = {
    // Add refAttr to parent insert
    parent.setCol(refAttr)
    recurseRender(indent, "RefOne")
  }
}
