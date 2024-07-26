package molecule.sql.core.transaction.strategy.update

import molecule.base.util.BaseHelpers
import molecule.sql.core.transaction.strategy.SqlOps

case class UpdateRefIdsDelete(
  parent: UpdateAction,
  sqlOps: SqlOps,
  ns: String,
  refAttr: String,
  refNs: String,
  nsId: Long,
  refIds: Set[Long]
) extends UpdateAction(parent, sqlOps, ns) with BaseHelpers {

  override def execute(): Unit = {
    val ps = prepare(curStmt)
    ps.addBatch()
    ps.executeBatch()
    ps.close()
  }

  override def curStmt: String = {
    val joinTable    = ss(ns, refAttr, refNs)
    val ns_id        = ss(ns, "id")
    val refNs_id     = ss(refNs, "id")
    val nsIdClause   = s"$ns_id = $nsId"
    val refIdsClause = refIds.size match {
      case 0 => Nil
      case 1 => List(s"$refNs_id = " + refIds.head)
      case _ => List(s"$refNs_id IN (${refIds.mkString(", ")})")
    }
    sqlOps.deleteStmt(joinTable, nsIdClause +: refIdsClause)
  }

  override def render(indent: Int): String = {
    recurseRender(indent, "DeleteRefIds")
  }
}
