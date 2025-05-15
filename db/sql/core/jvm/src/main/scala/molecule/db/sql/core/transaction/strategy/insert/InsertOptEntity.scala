package molecule.db.sql.core.transaction.strategy.insert

import molecule.db.sql.core.transaction.strategy.SqlOps

case class InsertOptEntity(
  parent: InsertAction,
  sqlOps: SqlOps,
  ent: String,
  rowCount: Int
) extends InsertAction(parent, sqlOps, ent, rowCount) {

  override def rootAction: InsertAction = parent.rootAction

  override def process(): Unit = {
    // Process children of optional entity
    children.foreach(_.process())

    insert()
  }
  override def curStmt: String = {
    sqlOps.insertStmt(ent, cols, placeHolders)
  }

  override def render(indent: Int): String = {
    recurseRender(indent, "OptEntity")
  }
}
