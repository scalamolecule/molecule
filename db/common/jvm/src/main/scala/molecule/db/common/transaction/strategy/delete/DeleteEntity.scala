package molecule.db.common.transaction.strategy.delete

import java.sql.Statement
import molecule.db.common.api.MetaDb
import molecule.db.common.transaction.strategy.SqlOps

case class DeleteEntity(
  parent: DeleteAction,
  sqlStmt: Statement,
  sqlOps: SqlOps,
  entity: String,
  refAttr: String,
  refEnt: String,
  metaDb:MetaDb,
) extends DeleteAction(parent, sqlStmt, sqlOps, refEnt, metaDb) {

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
