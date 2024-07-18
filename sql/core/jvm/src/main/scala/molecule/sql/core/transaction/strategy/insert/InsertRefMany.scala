package molecule.sql.core.transaction.strategy.insert

import java.sql.Connection
import molecule.sql.core.transaction.strategy.SqlOps

case class InsertRefMany(
  parent: InsertAction,
  sqlConn: Connection,
  sqlOps: SqlOps,
  ns: String,
  refAttr: String,
  refNs: String,
) extends InsertAction(sqlConn, sqlOps, refNs) {

  override def initialAction: InsertAction = parent.initialAction
  override def backRef: InsertAction = parent

  override def execute: List[Long] = {
    val refIds = insert
    // Add joins once we have parent ids
    addCardManyJoins(ns, refAttr, refNs, refIds)
    refIds
  }

  override def render(indent: Int): String = {
    // show join table after parent insert
    parent.postStmts += sqlOps.getJoinStmt(ns, refAttr, refNs)
    recurseRender(indent, "InsertRefMany")
  }
}
