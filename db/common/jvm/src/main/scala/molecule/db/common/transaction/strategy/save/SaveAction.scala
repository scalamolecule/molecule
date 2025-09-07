package molecule.db.common.transaction.strategy.save

import java.sql.PreparedStatement as PS
import molecule.db.common.transaction.strategy.save.*
import molecule.db.common.transaction.strategy.{SqlAction, SqlOps}

abstract class SaveAction(
  parent: SaveAction,
  sqlOps: SqlOps,
  ent: String
) extends SqlAction(parent, sqlOps, ent) {

  protected var reverse: Option[(SaveAction, Int)] = None

  def setReverseProcess(newParent: SaveAction, refAttrIndex: Int): Unit = {
    reverse = Some((newParent, refAttrIndex))
  }

  def getParent = parent


  // Build execution graph ----------------------------------------

  def refIds(refAttr: String, ref: String, refIds: Set[Long]): Unit = {
    addSibling(SaveRefIds(
      parent, sqlOps, ent, refAttr, ref, refIds
    ))
  }

  def refManyToOne(ent: String, refAttr: String, ref: String): SaveAction = {
    addChild(SaveRefOne(
      this, sqlOps, ent, refAttr, ref, setCol(refAttr)
    ))
  }

  def refOneToMany(ent: String, refAttr: String, ref: String, reverseRefAttr: String): SaveAction = {
    // Create many-side (B/C) with parent = this, so that backRef goes back to the logical one-side (A/B)
    val manySide     = SaveEntity(this, sqlOps, ref, "RefOne")
    val refAttrIndex = manySide.setCol(reverseRefAttr)

    // Let the actual one-side entity inject its id into manySide's FK when it inserts
    setReverseProcess(manySide, refAttrIndex)

    // Ensure manySide has a row to receive FK setter (single-row save)
    if (manySide.rowSetters.isEmpty)
      manySide.rowSetters += scala.collection.mutable.ListBuffer.empty[PS => Unit]

    // Schedule manySide to execute after the current entity's parent (e.g. after A)
    val gp = parent.getParent
    if (gp != null) {
      gp.children += manySide
    } else {
      // Top-level case (A -> B): append under parent so it runs after A
      parent.children += manySide
    }

    // Continue attribute collection on many-side
    manySide
  }

  def saveAndPrepareManyToOneRef(manySide: SaveAction, refAttrIndex: Int): Unit = {
    // Process any children first (consistent order)
    children.foreach(_.process())

    // Insert the one-side entity now to obtain its id
    insert(false)

    // Inject this entity's id into the many-side's FK column
    val id = ids.headOption.getOrElse(
      throw new IllegalStateException(s"Missing generated id for $ent during reverse handoff.")
    )

    // Ensure manySide has a row to receive setters
    if (manySide.rowSetters.isEmpty)
      manySide.rowSetters += scala.collection.mutable.ListBuffer.empty[PS => Unit]

    manySide.rowSetters.last += { (ps: PS) =>
      ps.setLong(refAttrIndex, id)
    }
  }

  def backRef: SaveAction = parent

  def optRef: SaveAction = ???

  // Traverse back and up to initial SaveAction
  def rootAction: SaveAction = ???
}