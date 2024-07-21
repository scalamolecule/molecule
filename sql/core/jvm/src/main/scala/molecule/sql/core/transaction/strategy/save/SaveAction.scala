package molecule.sql.core.transaction.strategy.save

import java.sql.Connection
import molecule.sql.core.transaction.strategy.{SqlAction, SqlOps}


abstract class SaveAction(
  parent: SaveAction,
  sqlConn: Connection,
  sqlOps: SqlOps,
  ns: String
) extends SqlAction(parent, sqlConn, sqlOps, ns) {

  // Build execution graph ----------------------------------------

  def refIds(refAttr: String, refNs: String, refIds: Set[Long]): Unit = {
    addSibling(SaveRefIds(
      parent, sqlConn, sqlOps, ns, refAttr, refNs, refIds
    ))
  }

  def refOne(ns: String, refAttr: String, refNs: String): SaveAction = {
    addChild(SaveRefOne(
      this, sqlConn, sqlOps, ns, refAttr, refNs, paramIndex(refAttr)
    ))
  }

  def refMany(ns: String, refAttr: String, refNs: String): SaveAction = {
    val ref = addChild(SaveNs(this, sqlConn, sqlOps, refNs, "RefMany"))

    // Make join after current ns is inserted
    addSibling(SaveRefJoin(this, ref, sqlConn, sqlOps, ns, refAttr, refNs))

    // Continue in ref namespace
    ref
  }

  def backRef: SaveAction = parent

  def optRef: SaveAction = ???
  def optRefNest: SaveAction = ???


  // Traverse back and up to initial SaveAction
  def rootAction: SaveAction = ???
}