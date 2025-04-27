package molecule.db.sql.core.transaction.strategy.save

import molecule.db.sql.core.transaction.strategy.SqlOps

case class SaveRefJoin(
  parentSave: SaveAction,
  refSave: SaveAction,
  sqlOps: SqlOps,
  ent: String,
  refAttr: String,
  ref: String,
) extends SaveAction(parentSave, sqlOps, ref) {

  override def process(): Unit = {
    val ps = prepare(curStmt)
    ps.setLong(1, parentSave.ids.last)
    ps.setLong(2, refSave.ids.last)
    ps.addBatch()
    ps.executeBatch()
    ps.close()
  }

  override def curStmt: String = {
    sqlOps.insertJoinStmt(ent, refAttr, ref)
  }

  override def render(indent: Int): String = {
    recurseRender(indent, "RefJoin")
  }
}
