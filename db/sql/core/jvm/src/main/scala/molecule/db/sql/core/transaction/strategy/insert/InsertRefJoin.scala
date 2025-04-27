package molecule.db.sql.core.transaction.strategy.insert

import molecule.db.sql.core.transaction.strategy.SqlOps

case class InsertRefJoin(
  parentInsert: InsertAction,
  refInsert: InsertAction,
  sqlOps: SqlOps,
  ent: String,
  refAttr: String,
  ref: String,
  rowCount: Int
) extends InsertAction(parentInsert, sqlOps, ref, rowCount) {

  override def process(): Unit = {
    val ps     = prepare(curStmt)
    val entIds = parentInsert.ids.iterator
    val refIds = refInsert.ids.iterator
    while (entIds.hasNext) {
      ps.setLong(1, entIds.next())
      ps.setLong(2, refIds.next())
      ps.addBatch()
    }
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
