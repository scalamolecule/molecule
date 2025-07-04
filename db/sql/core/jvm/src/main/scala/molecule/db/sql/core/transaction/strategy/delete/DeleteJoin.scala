package molecule.db.sql.core.transaction.strategy.delete

import java.sql.Statement
import molecule.db.core.api.MetaDb
import molecule.db.sql.core.transaction.strategy.SqlOps

case class DeleteJoin(
  parent: DeleteAction,
  sqlStmt: Statement,
  sqlOps: SqlOps,
  entity: String,
  refAttr: String,
  refEnt: String,
  metaDb:MetaDb,
) extends DeleteAction(parent, sqlStmt, sqlOps, refEnt, metaDb) {

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
