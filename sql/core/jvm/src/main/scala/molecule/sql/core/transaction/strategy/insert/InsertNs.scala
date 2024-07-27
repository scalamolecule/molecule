package molecule.sql.core.transaction.strategy.insert

import molecule.sql.core.transaction.strategy.SqlOps

case class InsertNs(
  parent: InsertAction,
  sqlOps: SqlOps,
  ns: String,
  action: String,
) extends InsertAction(parent, sqlOps, ns) {

  override def rootAction: InsertAction = parent.rootAction

  override def process(): Unit = {
    children.foreach(_.process())
    insertIntoTable()
  }

  override def curStmt: String = {
    sqlOps.insertStmt(ns, cols, placeHolders)
  }

  override def render(indent: Int): String = {
    recurseRender(indent, action)
  }
}
