package molecule.db.sql.core.transaction.strategy.save

import molecule.db.sql.core.transaction.strategy.{SqlAction, SqlOps}

abstract class SaveAction(
  parent: SaveAction,
  sqlOps: SqlOps,
  ent: String
) extends SqlAction(parent, sqlOps, ent) {

  // Build execution graph ----------------------------------------

  def refIds(refAttr: String, ref: String, refIds: Set[Long]): Unit = {
    addSibling(SaveRefIds(
      parent, sqlOps, ent, refAttr, ref, refIds
    ))
  }

  def refOne(ent: String, refAttr: String, ref: String): SaveAction = {
    addChild(SaveRefOne(
      this, sqlOps, ent, refAttr, ref, setCol(refAttr)
    ))
  }

  def refMany(ent: String, refAttr: String, r: String): SaveAction = {
    val ref = addChild(SaveEntity(this, sqlOps, r, "RefMany"))

    // Make join after current entity is inserted
    addSibling(SaveRefJoin(this, ref, sqlOps, ent, refAttr, r))

    // Continue with ref entity
    ref
  }

  def backRef: SaveAction = parent

  def optRef: SaveAction = ???


  // Traverse back and up to initial SaveAction
  def rootAction: SaveAction = ???
}