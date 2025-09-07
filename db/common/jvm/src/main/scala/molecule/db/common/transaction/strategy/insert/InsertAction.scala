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

  var reverse: Option[(InsertAction, Int)] = None

  def setReverseProcess(newParent: InsertAction, refAttrIndex: Int): Unit = {
    reverse = Some((newParent, refAttrIndex))
  }

  // Build execution graph ----------------------------------------

  def refIds(refAttr: String, ref: String): InsertRefIds = {
    addSibling(InsertRefIds(
      parent, sqlOps, ent, refAttr, ref, rowCount
    ))
  }

  def refManyToOne(ent: String, refAttr: String, ref: String): InsertAction = {
    val manySide = this

    // Add ref attr to many-side
    val refAttrIndex = manySide.setCol(refAttr)
    val oneSide      = InsertRefOne(manySide, sqlOps, ref, refAttrIndex, rowCount)
    manySide.addChild(oneSide)
  }


  def refOneToMany(ent: String, refAttr: String, ref: String, reverseRefAttr: String): InsertAction = {
    val oneSide = this

    // many-side where the foreign key (reverse ref attr) is to be set
    val manySide     = InsertEntity(parent, sqlOps, ref, "RefOne", rowCount)
    val refAttrIndex = manySide.setCol(reverseRefAttr)

    // Ensure manySide has a row to receive setters for the current row
    if (manySide.rowSetters.isEmpty)
      manySide.rowSetters += ListBuffer.empty[PS => Unit]

    // many-side has one-side as child action to be processed before many-side can make a ref to it
    manySide.children += oneSide

    // many-side is now one-sides parent
    oneSide.setReverseProcess(manySide, refAttrIndex)

    // many-side is the new parent child that we will continue from
    parent.children.clear()
    parent.children += manySide

    // Continue with many-side
    manySide
  }

  def insertAndPrepareManyToOneRef(manySide: InsertAction, refAttrIndex: Int): Unit = {
    // Process children of ref
    children.foreach(_.process())

    // Add ref rows (don't enforce empty row)
    insert(false)

    // Add ref ids from parent (previous entity) to ref
    val refIds = ids.iterator

    manySide match {
      case _: InsertOptRef =>
        // make ref only when parent/prev entity has value
        manySide.rowSetters.zip(manySide.optionalDefineds).foreach {
          case (setter, true) =>
            setter += ((ps: PS) => ps.setLong(refAttrIndex, refIds.next()))

          case (setter, _) =>
            setter += ((ps: PS) => ps.setNull(refAttrIndex, 0))
        }

      case _: InsertOptEntity =>
        val entityResolvers = manySide.rowSetters.flatMap {
          case rowSetter if rowSetter.isEmpty =>
            refIds.next() // skip unused ref id
            None // no optional entity created

          case rowSetter =>
            val refId = refIds.next()
            rowSetter += ((ps: PS) => ps.setLong(refAttrIndex, refId))
            Some(rowSetter)
        }
        manySide.rowSetters.clear()
        manySide.rowSetters ++= entityResolvers

      case _ =>
        val manySideRowSetters = manySide.rowSetters.iterator
        while (refIds.hasNext) {
          val parentRowSetter = manySideRowSetters.next()
          val refId           = refIds.next()
          val refIdSetter     = (ps: PS) => ps.setLong(refAttrIndex, refId)
          parentRowSetter += refIdSetter
        }
    }
  }


  def backRef: InsertAction = {
    // When refOneToMany has flipped topology (many-side containing one-side child),
    // prefer the logical one-side (child) when back-reffing.
    if (children.nonEmpty) children.head.asInstanceOf[InsertAction]
    else parent
  }

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

  def nestFk(ent: String, refAttr: String, ref: String, reverseRefAttr: String): InsertNestedFk = {
    // Create the nested Many-side entity (not inserted directly by its own process)
    val nested = InsertEntity(this, sqlOps, ref, "Nested", rowCount)
    // Reserve the FK column on the Many side
    val fkIdx  = nested.setCol(reverseRefAttr)
    // Build the FK action that will inject parent ids and then insert the nested entity
    val fkLink = InsertNestedFk(this, nested, sqlOps, ent, refAttr, ref, reverseRefAttr, fkIdx, rowCount)

    // Attach according to context:
    // - If we're inside a Nested entity (deeper level), attach as child so it runs after this level inserts.
    // - Otherwise (top-level entity), attach as sibling so it runs after the top-level insert.
    this match {
      case InsertEntity(_, _, _, "Nested", _) =>
        addChild(fkLink)
      case _                                  =>
        addSibling(fkLink)
    }

    fkLink
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
        s"Unexpected different number of parent/child ids: " +
          s"$ent / $refAttr ($ref): $l1 / $l2"
      )
    }
  }
}