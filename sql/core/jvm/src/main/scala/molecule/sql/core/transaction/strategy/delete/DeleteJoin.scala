package molecule.sql.core.transaction.strategy.delete

import java.sql.Statement
import molecule.base.ast.MetaNs
import molecule.sql.core.transaction.strategy.SqlOps

case class DeleteJoin(
  nsMap: Map[String, MetaNs],
  parent: DeleteAction,
  sqlStmt: Statement,
  sqlOps: SqlOps,
  ns: String,
  refAttr: String,
  refNs: String,
) extends DeleteAction(nsMap, parent, sqlStmt, sqlOps, refNs) {

  override def process(): Unit = {
    sqlStmt.addBatch(curStmt)
  }

  override def curStmt: String = {
    val joinTable  = ss(ns, refAttr, refNs)
    val (ns_id, _) = sqlOps.joinIdNames(ns, refNs)
    s"DELETE FROM $joinTable WHERE $ns_id IN (" + ids.mkString(", ") + ")"
  }

  override def render(indent: Int): String = {
    recurseRender(indent, s"$ns.$refAttr.$refNs ")
  }
}
