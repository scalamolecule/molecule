package molecule.db.common.transaction.strategy.insert

import molecule.db.common.transaction.strategy.SqlOps

case class InsertEntity(
  parent: InsertAction,
  sqlOps: SqlOps,
  ent: String,
  action: String,
  rowCount: Int
) extends InsertAction(parent, sqlOps, ent, rowCount) {

  override def rootAction: InsertAction = parent.rootAction

  override def process(): Unit = {
    reverse.fold {
      // No reverse handoff: process children first, then insert this entity
      children.foreach(_.process())
      insert()
    } { case (manySide, refAttrIndex) =>
      // Reverse handoff: insert this (one-side) to get ids, inject them into many-side FK,
      // and prepare many-side to insert with FK values set.
      insertAndPrepareManyToOneRef(manySide, refAttrIndex)
    }
  }


  override def curStmt: String = {
    sqlOps.insertStmt(ent, cols, placeHolders)
  }

  override def render(indent: Int): String = {
    recurseRender(indent, action)
  }
}
