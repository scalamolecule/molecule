package molecule.sql.core.transaction.strategy.delete

import java.sql.Statement
import molecule.base.ast.MetaNs
import molecule.sql.core.transaction.strategy.SqlOps

case class DeleteNs(
  nsMap: Map[String, MetaNs],
  parent: DeleteAction,
  sqlStmt: Statement,
  sqlOps: SqlOps,
  ns: String,
  refAttr: String,
  refNs: String,
) extends DeleteAction(nsMap, parent, sqlStmt, sqlOps, refNs) {

  override def process(): Unit = {
    buildExecutionGraph()
    children.foreach(_.process())
    sqlStmt.addBatch(curStmt)
  }

  override def curStmt: String = {
    s"DELETE FROM $refNs WHERE id IN (" + ids.mkString(", ") + ")"
  }

  override def render(indent: Int): String = {
    buildExecutionGraph()
    val path = if (refAttr.isEmpty) s"$refNs " else s"$ns.$refAttr.$refNs "
    recurseRender(indent, path)
  }
}
