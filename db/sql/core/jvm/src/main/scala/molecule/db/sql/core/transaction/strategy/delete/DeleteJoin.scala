package molecule.db.sql.core.transaction.strategy.delete

import java.sql.Statement
import molecule.db.base.ast.MetaEntity
import molecule.db.sql.core.transaction.strategy.SqlOps

case class DeleteJoin(
  entityMap: Map[String, MetaEntity],
  parent: DeleteAction,
  sqlStmt: Statement,
  sqlOps: SqlOps,
  entity: String,
  refAttr: String,
  refEnt: String,
) extends DeleteAction(entityMap, parent, sqlStmt, sqlOps, refEnt) {

  override def process(): Unit = {
    sqlStmt.addBatch(curStmt)
  }

  override def curStmt: String = {
    val joinTable = ss(entity, refAttr, refEnt)
    val (eid, _)  = sqlOps.joinIdNames(entity, refEnt)
    s"DELETE FROM $joinTable WHERE $eid IN (" + ids.mkString(", ") + ")"
  }

  override def render(indent: Int): String = {
    recurseRender(indent, s"$entity.$refAttr.$refEnt ")
  }
}
