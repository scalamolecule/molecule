package molecule.db.sql.core.transaction.strategy.delete

import java.sql.Statement
import molecule.base.ast.MetaEntity
import molecule.db.sql.core.transaction.strategy.SqlOps

case class DeleteEntity(
  entityMap: Map[String, MetaEntity],
  parent: DeleteAction,
  sqlStmt: Statement,
  sqlOps: SqlOps,
  entity: String,
  refAttr: String,
  refEnt: String,
) extends DeleteAction(entityMap, parent, sqlStmt, sqlOps, refEnt) {

  override def process(): Unit = {
    buildExecutionGraph()
    children.foreach(_.process())
    sqlStmt.addBatch(curStmt)
  }

  override def curStmt: String = {
    s"DELETE FROM $refEnt WHERE id IN (" + ids.mkString(", ") + ")"
  }

  override def render(indent: Int): String = {
    buildExecutionGraph()
    val path = if (refAttr.isEmpty) s"$refEnt " else s"$entity.$refAttr.$refEnt "
    recurseRender(indent, path)
  }
}
