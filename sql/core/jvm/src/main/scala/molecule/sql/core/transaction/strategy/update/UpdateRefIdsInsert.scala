package molecule.sql.core.transaction.strategy.update

import molecule.sql.core.transaction.strategy.SqlOps

case class UpdateRefIdsInsert(
  parent: UpdateAction,
  sqlOps: SqlOps,
  ns: String,
  refAttr: String,
  refNs: String,
  refIds0: Set[Long]
) extends UpdateAction(parent, sqlOps, ns) {

  override def execute(): Unit = {
    val ps     = prepare(curStmt)
    val curNs  = parent.children.head
    val nsId   = curNs.ids.head // Single card-one id
    val refIds = refIds0.iterator
    while (refIds.hasNext) {
      ps.setLong(1, nsId)
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
    recurseRender(indent, "InsertRefIds")
  }
}
