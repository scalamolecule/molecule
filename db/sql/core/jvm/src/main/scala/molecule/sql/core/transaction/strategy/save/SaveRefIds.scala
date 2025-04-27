package molecule.sql.core.transaction.strategy.save

import molecule.sql.core.transaction.strategy.SqlOps

case class SaveRefIds(
  parent: SaveAction,
  sqlOps: SqlOps,
  ent: String,
  refAttr: String,
  ref: String,
  refIds0: Set[Long]
) extends SaveAction(parent, sqlOps, ref) {

  override def process(): Unit = {
    val ps     = prepare(curStmt)
    val curEnt = parent.children.head
    val entId  = curEnt.ids.head // Single card-one id
    val refIds = refIds0.iterator
    while (refIds.hasNext) {
      ps.setLong(1, entId)
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
    recurseRender(indent, "RefIds")
  }
}
