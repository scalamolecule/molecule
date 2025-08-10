package molecule.db.common.transaction.strategy.insert

import java.sql.PreparedStatement as PS
import scala.collection.mutable.ListBuffer
import molecule.db.common.transaction.strategy.{SqlAction, SqlOps}

abstract class InsertAction(
  parent: InsertAction,
  sqlOps: SqlOps,
  ent: String,
  rowCount: Int
) extends SqlAction(parent, sqlOps, ent) {

  // Build execution graph ----------------------------------------

  def refIds(refAttr: String, ref: String): InsertRefIds = {
    addSibling(InsertRefIds(
      parent, sqlOps, ent, refAttr, ref, rowCount
    ))
  }

  def refOne(ent: String, refAttr: String, ref: String): InsertAction = {
    // Add ref attr to current entity
    val refAttrIndex = setCol(refAttr)
    addChild(InsertRefOne(
      this, sqlOps, ent, refAttr, ref, refAttrIndex, rowCount
    ))
  }

  def refOneReverse(ent: String, refAttr: String, ref: String): InsertAction = {
    //    val ref = addChild(InsertEntity(this, sqlOps, r, "RefMany", rowCount))

    // Make joins to refs after current and refs have been inserted
    //    addSibling(InsertRefJoin(this, ref, sqlOps, ent, refAttr, r, rowCount))
    //
    //    // Continue with ref entity
    //    ref


//    parent.setCol(refAttr)

    println("parent: " + parent)
    println("======================")

    //    replaceSibling(InsertEntity(this, sqlOps, ent, rowCount))


    val refAttrIndex = setCol(refAttr)

    val newParent = InsertRefOne(
      this, sqlOps, ent + "--------", refAttr + "xxxx", ref + "zzzzz", refAttrIndex, rowCount
    )


    println("newParent: " + newParent)
    println("======================")

//    newParent.addChild(parent)
//    addChild(parent)
//    val action = addChild(InsertRefOne(
//      this, sqlOps, ent, refAttr, ref, refAttrIndex, rowCount
//    ))
//    println("children:\n" + children.mkString("%%%%%%%%%%%%%%"))
//    println("======================")

    val root = parent.children.last
    parent.children.clear()

    newParent
//    addChild(newParent)
//    parent.parent
//    getGrandParent
  }

  def refMany(ent: String, refAttr: String, r: String): InsertAction = {
    val ref = addChild(InsertEntity(this, sqlOps, r, "RefMany", rowCount))

    // Make joins to refs after current and refs have been inserted
    addSibling(InsertRefJoin(this, ref, sqlOps, ent, refAttr, r, rowCount))

    // Continue with ref entity
    ref
  }

  def backRef: InsertAction = parent

  def optRef(ent: String, refAttr: String, ref: String): InsertOptRef = {
    // Add ref attr to current entity
    val refAttrIndex = setCol(refAttr)
    addChild(InsertOptRef(
      this, sqlOps, ent, refAttr, ref, refAttrIndex, rowCount
    ))
  }

  def optEntity(ent: String): InsertOptEntity = {
    replaceSibling(InsertOptEntity(this, sqlOps, ent, rowCount))
  }

  def nest(ent: String, refAttr: String, ref: String): InsertNestedJoins = {
    // Nested entity
    val nested = addChild(InsertEntity(this, sqlOps, ref, "Nested", rowCount))

    // Make joins to nested after current and nested have been inserted
    addSibling(InsertNestedJoins(
      this, nested, sqlOps, ent, refAttr, ref, rowCount
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
      case InsertEntity(_, _, _, "Nested", _) => ()
      case child: InsertAction                => child.nextRow()
      case _                                  => ()
    }
  }

  def sameLength(l1: Int, l2: Int, refAttr: String, ref: String): Unit = {
    // Make sure arities match (not needed once implementation is stabilized)
    if (l1 != l2) {
      throw new Exception(
        s"Unexpected different number of left/right ids for " +
          s"joinTable ${ent}_${refAttr}_$ref: $l1/$l2"
      )
    }
  }
}