package molecule.sql.core.transaction.strategy.insert

import java.sql.{PreparedStatement => PS}
import molecule.sql.core.transaction.strategy.{SqlAction, SqlOps}
import scala.collection.mutable.ListBuffer

abstract class InsertAction(
  parent: InsertAction,
  sqlOps: SqlOps,
  ns: String,
  rowCount: Int
) extends SqlAction(parent, sqlOps, ns) {

  // Build execution graph ----------------------------------------

  def refIds(refAttr: String, refNs: String): InsertRefIds = {
    addSibling(InsertRefIds(
      parent, sqlOps, ns, refAttr, refNs, rowCount
    ))
  }

  def refOne(ns: String, refAttr: String, refNs: String): InsertAction = {
    // Add ref attr to current ns
    val refAttrIndex = setCol(refAttr)
    addChild(InsertRefOne(
      this, sqlOps, ns, refAttr, refNs, refAttrIndex, rowCount
    ))
  }

  def refMany(ns: String, refAttr: String, refNs: String): InsertAction = {
    val ref = addChild(InsertNs(this, sqlOps, refNs, "RefMany", rowCount))

    // Make joins to refs after current and refs have been inserted
    addSibling(InsertRefJoin(this, ref, sqlOps, ns, refAttr, refNs, rowCount))

    // Continue in ref namespace
    ref
  }

  def backRef: InsertAction = parent

  def optRef(
    ns: String, refAttr: String, refNs: String
  ): InsertOptRef = {
    // Add ref attr to current ns
    val refAttrIndex = setCol(refAttr)
    addChild(InsertOptRef(
      this, sqlOps, ns, refAttr, refNs, refAttrIndex, rowCount
    ))
  }

  def nest(ns: String, refAttr: String, refNs: String): InsertNestedJoins = {
    // Nested namespace
    val nested = addChild(InsertNs(this, sqlOps, refNs, "Nested", rowCount))

    // Make joins to nested after current and nested have been inserted
    addSibling(InsertNestedJoins(
      this, nested, sqlOps, ns, refAttr, refNs, rowCount
    ))
  }

  // Traverse back and up to initial InsertAction
  def rootAction: InsertAction = ???


  // Helpers ------------------------------------------------

  // Options empty by default on this level for all insert rows
  var optionalDefineds = Array.fill(rowCount)(false)

  def setOptionalDefined(defined: Boolean): Unit = {
    optionalDefineds.update(rowIndex, defined)
  }

  var rowIndex = -1

  def nextRow(): Unit = {
    rowIndex += 1
    rowSetters += ListBuffer.empty[PS => Unit]
    children.foreach {
      case InsertNs(_, _, _, "Nested", _) => ()
      case child: InsertAction            => child.nextRow()
      case _                              => ()
    }
  }

  def sameLength(l1: Int, l2: Int, refAttr: String, refNs: String): Unit = {
    // Make sure arities match (not needed once implementation is stabilized)
    if (l1 != l2) {
      throw new Exception(
        s"Unexpected different number of left/right ids for " +
          s"joinTable ${ns}_${refAttr}_$refNs: $l1/$l2"
      )
    }
  }
}