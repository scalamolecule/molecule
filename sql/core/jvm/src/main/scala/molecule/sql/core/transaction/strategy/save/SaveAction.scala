package molecule.sql.core.transaction.strategy.save

import molecule.sql.core.transaction.strategy.{SqlAction, SqlOps}

abstract class SaveAction(
  parent: SaveAction,
  sqlOps: SqlOps,
  ns: String
) extends SqlAction(parent, sqlOps, ns) {

  // Build execution graph ----------------------------------------

  def refIds(refAttr: String, refNs: String, refIds: Set[Long]): Unit = {
    addSibling(SaveRefIds(
      parent, sqlOps, ns, refAttr, refNs, refIds
    ))
  }

  def refOne(ns: String, refAttr: String, refNs: String): SaveAction = {
    addChild(SaveRefOne(
      this, sqlOps, ns, refAttr, refNs, setCol(refAttr)
    ))
  }

  def refMany(ns: String, refAttr: String, refNs: String): SaveAction = {
    val ref = addChild(SaveNs(this, sqlOps, refNs, "RefMany"))

    // Make join after current ns is inserted
    addSibling(SaveRefJoin(this, ref, sqlOps, ns, refAttr, refNs))

    // Continue in ref namespace
    ref
  }

  def backRef: SaveAction = parent

  def optRef: SaveAction = ???
  def optRefNest: SaveAction = ???


  // Traverse back and up to initial SaveAction
  def rootAction: SaveAction = ???
}