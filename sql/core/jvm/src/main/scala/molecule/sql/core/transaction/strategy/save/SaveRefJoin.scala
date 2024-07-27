package molecule.sql.core.transaction.strategy.save

import molecule.sql.core.transaction.strategy.SqlOps

case class SaveRefJoin(
  parent: SaveAction,
  ref: SaveAction,
  sqlOps: SqlOps,
  ns: String,
  refAttr: String,
  refNs: String,
) extends SaveAction(parent, sqlOps, refNs) {

  override def process(): Unit = {
    val ps = prepare(curStmt)
    ps.setLong(1, parent.ids.last)
    ps.setLong(2, ref.ids.last)
    ps.addBatch()
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
