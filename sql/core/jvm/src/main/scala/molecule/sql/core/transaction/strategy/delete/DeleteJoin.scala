package molecule.sql.core.transaction.strategy.delete

import java.sql.Statement
import molecule.base.ast.MetaEntity
import molecule.sql.core.transaction.strategy.SqlOps

case class DeleteJoin(
  entityMap: Map[String, MetaEntity],
  parent: DeleteAction,
  sqlStmt: Statement,
  sqlOps: SqlOps,
  entity: String,
  refAttr: String,
  refEntity: String,
) extends DeleteAction(entityMap, parent, sqlStmt, sqlOps, refEntity) {

  override def process(): Unit = {
    sqlStmt.addBatch(curStmt)
  }

  override def curStmt: String = {
    val joinTable = ss(entity, refAttr, refEntity)
    val (eid, _)  = sqlOps.joinIdNames(entity, refEntity)
    s"DELETE FROM $joinTable WHERE $eid IN (" + ids.mkString(", ") + ")"
  }

  override def render(indent: Int): String = {
    recurseRender(indent, s"$entity.$refAttr.$refEntity ")
  }
}
