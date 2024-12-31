package molecule.sql.core.transaction.strategy.update

import molecule.base.util.BaseHelpers
import molecule.sql.core.transaction.strategy.SqlOps

case class UpdateRefIdsDelete(
  parent: UpdateAction,
  sqlOps: SqlOps,
  ent: String,
  refAttr: String,
  refEnt: String,
  nsId: Long,
  refIds: Set[Long]
) extends UpdateAction(parent, sqlOps, ent) with BaseHelpers {

  override def process(): Unit = {
    val ps = prepare(curStmt)
    ps.addBatch()
    ps.executeBatch()
    ps.close()
  }

  override def curStmt: String = {
    val joinTable    = ss(ent, refAttr, refEnt)
    val eid          = ss(ent, "id")
    val refEnt_id    = ss(refEnt, "id")
    val nsIdClause   = s"$eid = $nsId"
    val refIdsClause = refIds.size match {
      case 0 => Nil
      case 1 => List(s"$refEnt_id = " + refIds.head)
      case _ => List(s"$refEnt_id IN (${refIds.mkString(", ")})")
    }
    sqlOps.deleteStmt(joinTable, nsIdClause +: refIdsClause)
  }

  override def render(indent: Int): String = {
    recurseRender(indent, "DeleteRefIds")
  }
}
