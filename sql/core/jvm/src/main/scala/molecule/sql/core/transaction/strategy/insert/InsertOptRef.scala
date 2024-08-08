package molecule.sql.core.transaction.strategy.insert

import java.sql.{PreparedStatement => PS}
import molecule.sql.core.transaction.strategy.SqlOps

case class InsertOptRef(
  parent: InsertAction,
  sqlOps: SqlOps,
  ns: String,
  refAttr: String,
  refNs: String,
  refAttrIndex: Int,
  rowCount: Int
) extends InsertAction(parent, sqlOps, refNs, rowCount) {

  override def rootAction: InsertAction = parent.rootAction


  override def process(): Unit = {
    // Process children of ref ns
    children.foreach(_.process())

    //    println(s"++++++++++++++++++++++++++++++++++++++++++++++++++++++++" +
    //      s"  $refNs  " + optionalDefineds.mkString("Array(", ", ", ")"))
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

    //    println("")
    //    println("parent.rowSetters")
    //    println(parent.rowSetters.map(rs => rs.toList.mkString("\n   ")).mkString("   ", "\n   -----\n   ", ""))

    // Add ref rows for this namespace/table (don't enforce empty row)
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
    sqlOps.insertStmt(refNs, cols, placeHolders)
  }

  override def render(indent: Int): String = {
    // Add refAttr to parent insert
    parent.setCol(refAttr)
    recurseRender(indent, "RefOpt")
  }
}
