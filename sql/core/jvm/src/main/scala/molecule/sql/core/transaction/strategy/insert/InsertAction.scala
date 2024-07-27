package molecule.sql.core.transaction.strategy.insert

import java.sql.{PreparedStatement => PS}
import molecule.sql.core.transaction.strategy.{SqlAction, SqlOps}
import scala.collection.mutable.ListBuffer

abstract class InsertAction(
  parent: InsertAction,
  sqlOps: SqlOps,
  ns: String
) extends SqlAction(parent, sqlOps, ns) {

  // Build execution graph ----------------------------------------

  def refIds(refAttr: String, refNs: String): InsertRefIds = {
    addSibling(InsertRefIds(
      parent, sqlOps, ns, refAttr, refNs
    ))
  }

  def refOne(ns: String, refAttr: String, refNs: String): InsertAction = {
    addChild(InsertRefOne(
      this, sqlOps, ns, refAttr, refNs, setCol(refAttr)
    ))
  }

  //  def optRef(ns: String, refAttr: String, refNs: String): InsertOptionalRefs = {
  def optRef(ns: String, refAttr: String, refNs: String): InsertOptionalRefs = {

    // Add ref attr to current ns
    val refAttrIndex = setCol(refAttr)
    addChild(InsertOptionalRefs(this, sqlOps, ns, refAttr, refNs, refAttrIndex))

    //    // Optional namespace
    //    val optional = addChild(InsertNs(this, sqlOps, refNs, "Nested"))
    //
    //    // Make joins to nested after current and nested have been inserted
    //    addSibling(InsertOptionalRefs(
    //      this, optional, sqlOps, ns, refAttr, refNs, setCol(refAttr)
    //    ))
  }
  def optRefNest: InsertAction = ???

  def refMany(ns: String, refAttr: String, refNs: String): InsertAction = {
    val ref = addChild(InsertNs(this, sqlOps, refNs, "RefMany"))

    // Make joins to refs after current and refs have been inserted
    addSibling(InsertRefJoin(this, ref, sqlOps, ns, refAttr, refNs))

    // Continue in ref namespace
    ref
  }


  def backRef: InsertAction = parent

  def nest(ns: String, refAttr: String, refNs: String): InsertNestedJoins = {
    // Nested namespace
    val nested = addChild(InsertNs(this, sqlOps, refNs, "Nested"))

    // Make joins to nested after current and nested have been inserted
    addSibling(InsertNestedJoins(this, nested, sqlOps, ns, refAttr, refNs))
  }

  // Traverse back and up to initial InsertAction
  def rootAction: InsertAction = ???


  def nextRow(): Unit = {
    rowSetters += ListBuffer.empty[PS => Unit]
    children.foreach {
      case InsertNs(_, _, _, "Nested") => ()
      case child: InsertAction         => child.nextRow()
      case _                           => ()
    }
  }

  def sameLength(l1: Int, l2: Int, refAttr: String, refNs: String) = {
    // Make sure arities match (not needed once implementation is stabilized)
    if (l1 != l2) {
      throw new Exception(
        s"Unexpected different number of left/right ids for " +
          s"joinTable ${ns}_${refAttr}_$refNs: $l1/$l2"
      )
    }
  }
}