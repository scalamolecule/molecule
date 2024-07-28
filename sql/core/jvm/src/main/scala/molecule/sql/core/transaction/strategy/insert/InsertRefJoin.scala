package molecule.sql.core.transaction.strategy.insert

import molecule.sql.core.transaction.strategy.SqlOps

case class InsertRefJoin(
  parent: InsertAction,
  ref: InsertAction,
  sqlOps: SqlOps,
  ns: String,
  refAttr: String,
  refNs: String,
  rowCount: Int
) extends InsertAction(parent, sqlOps, refNs, rowCount) {

  override def process(): Unit = {
    val ps     = prepare(curStmt)
    val nsIds  = parent.ids.iterator
    val refIds = ref.ids.iterator
    while (nsIds.hasNext) {
      ps.setLong(1, nsIds.next())
      ps.setLong(2, refIds.next())
      ps.addBatch()
    }
    ps.executeBatch()
    ps.close()
  }

  override def curStmt: String = {
    sqlOps.insertJoinStmt(ns, refAttr, refNs)
  }

  override def render(indent: Int): String = {
    recurseRender(indent, "RefJoin")
  }
}
