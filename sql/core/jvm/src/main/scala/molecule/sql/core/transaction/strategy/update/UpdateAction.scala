package molecule.sql.core.transaction.strategy.update

import java.sql.Connection
import molecule.sql.core.transaction.strategy.{SqlAction, SqlOps}

abstract class UpdateAction(
  sqlConn: Connection,
  sqlOps: SqlOps,
  ns: String
) extends SqlAction(sqlConn, sqlOps, ns) {

  def update(ids: List[Long]): List[Long] = {
    children.foreach(_.execute)

    // Execute this namespace update
    val ps = prepStmt(curStmt)
    rowSetters.head.foreach(_(ps)) // set values in prepared stmt
    ps.addBatch()
    ps.executeBatch()
    ps.close()

    // Return ids used to identify updated rows
    ids
  }


  // Change strategy ----------------------------------------

  // Traverse back to initial InsertAction
  def initialAction: UpdateAction

  def withIds(ns: String, ids: Seq[Long]): UpdateAction =
    addChild(UpdateWithIds(this, sqlConn, sqlOps, ns, ids.toList), true)

  def refOne(ns: String, refAttr: String, refNs: String): UpdateAction =
    addChild(UpdateRefOne(this, sqlConn, sqlOps, ns, refAttr, refNs))

  def refMany(ns: String, refAttr: String, refNs: String): UpdateAction =
    addChild(UpdateRefMany(this, sqlConn, sqlOps, ns, refAttr, refNs))

  def backRef: UpdateAction = ???

  def optRef: UpdateAction = ???
  def optRefNest: UpdateAction = ???


  // Render --------------------------------------

  override def curStmt: String = sqlOps.updateStmt(ns, cols, clauses)
}
