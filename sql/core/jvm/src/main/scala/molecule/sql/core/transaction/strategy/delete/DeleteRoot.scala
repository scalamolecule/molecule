package molecule.sql.core.transaction.strategy.delete

import java.sql.{Connection, Statement}
import molecule.base.ast._
import molecule.sql.core.transaction.strategy.SqlOps

case class DeleteRoot(
  entityMap: Map[String, MetaEntity],
  sqlOps: SqlOps,
  sqlStmt: Statement,
  ns: String
) extends DeleteAction(entityMap, null, sqlStmt, sqlOps, ns) {

  val sqlConn: Connection = sqlOps.sqlConn

  val firstNs = DeleteEntity(entityMap, this, sqlStmt, sqlOps, "", "", ns)
  children += firstNs

  override def execute: List[Long] = {
    if (firstNs.ids.isEmpty) Nil else {
      // Add batches of all delete statements in graph
      children.foreach(_.process())
      sqlStmt.executeBatch
      sqlStmt.close()
      firstNs.ids
    }
  }

  override def toString: String = {
    recurseRender(-1, "Delete")
  }
}
