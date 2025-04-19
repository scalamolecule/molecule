package molecule.sql.core.transaction.strategy.insert

import java.sql.PreparedStatement as PS
import molecule.sql.core.transaction.strategy.SqlOps

case class InsertOptRef(
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
    // Process children of ref entity
    children.foreach(_.process())

    //    println(s"+++ InsertOptRef ++++++++++++++++++++++++++++++++++++" +
    //      s"  $ref  " + optionalDefineds.mkString("Array(", ", ", ")"))
    //    println("rowSetters")
    //    println(rowSetters.map(rs => rs.toList.mkString("\n   ")).mkString("   ", "\n   -----\n   ", ""))

    // Don't insert rows for empty branches (leafs have no optional refs)
    val isBranch = children.nonEmpty
    if (isBranch) {
      optionalDefineds.zipWithIndex.collect {
        case (false, i) =>
          rowSetters.remove(i)
      }
    }

    //    println("\nparent.rowSetters")
    //    println(parent.rowSetters.map(rs => rs.toList.mkString("\n   ")).mkString("   ", "\n   -----\n   ", ""))
    //    println("######### " + parent.ids.length)
    //    println("========= " + ids.length)

    // Add ref rows for this entity/table (don't enforce empty row)
    insert(false)

    val refIds = ids.iterator
    parent.rowSetters.zip(optionalDefineds).foreach {
      case (setter, true) =>
        setter += ((ps: PS) => ps.setLong(refAttrIndex, refIds.next()))

      case (setter, _) =>
        setter += ((ps: PS) => ps.setNull(refAttrIndex, 0))
    }
  }
  override def curStmt: String = {
    sqlOps.insertStmt(ref, cols, placeHolders)
  }

  override def render(indent: Int): String = {
    recurseRender(indent, "OptRef")
  }
}
