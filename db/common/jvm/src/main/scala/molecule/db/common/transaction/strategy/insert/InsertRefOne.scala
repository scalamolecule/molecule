package molecule.db.common.transaction.strategy.insert

import java.sql.PreparedStatement as PS
import molecule.db.common.transaction.strategy.SqlOps

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

    //    println(s"+++ InsertRefOne ++++++++++++++++++++++++++++++++++++" +
    //      s"  $ref  " + optionalDefineds.mkString("Array(", ", ", ")"))
    //    println("rowSetters")
    //    println(rowSetters.map(rs => rs.toList.mkString("\n   ")).mkString("   ", "\n   -----\n   ", ""))
    //
    //    println("\nparent.rowSetters  " + parent.rowSetters.length)
    //    println(parent.rowSetters.map(rs => rs.toList.mkString("\n   ")).mkString("   ", "\n   -----\n   ", ""))
    //    //
    //    println("\nparent.optionalDefineds  " + parent.optionalDefineds.length)
    //    println(parent.optionalDefineds.mkString("\n  ", ",\n  ", ""))
    //
    //    println("\nparent.rowSetters.zip(parent.optionalDefineds)")
    //    println(parent.rowSetters.zip(parent.optionalDefineds).mkString("   ", "\n   -----\n   ", ""))

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

      case _: InsertOptEntity =>
        val entityResolvers = parent.rowSetters.flatMap {
          case rowSetter if rowSetter.isEmpty =>
            refIds.next() // skip unused ref id
            None // no optional entity created

          case rowSetter =>
            val refId = refIds.next()
            rowSetter += ((ps: PS) => ps.setLong(refAttrIndex, refId))
            Some(rowSetter)
        }
        parent.rowSetters.clear()
        parent.rowSetters ++= entityResolvers

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
    recurseRender(indent, "RefOne")
  }
}
