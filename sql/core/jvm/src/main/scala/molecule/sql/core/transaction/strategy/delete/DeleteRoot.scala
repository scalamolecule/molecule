package molecule.sql.core.transaction.strategy.delete

import java.sql.{Connection, Statement}
import molecule.base.ast._
import molecule.sql.core.transaction.strategy.SqlOps

case class DeleteRoot(
  entityMap: Map[String, MetaEntity],
  sqlOps: SqlOps,
  sqlStmt: Statement,
  ent: String
) extends DeleteAction(entityMap, null, sqlStmt, sqlOps, ent) {

  val sqlConn: Connection = sqlOps.sqlConn

  val firstEnt = DeleteEntity(entityMap, this, sqlStmt, sqlOps, "", "", ent)
  children += firstEnt

  override def execute: List[Long] = {
    if (firstEnt.ids.isEmpty) Nil else {
      // Add batches of all delete statements in graph
      children.foreach(_.process())
      sqlStmt.executeBatch
      sqlStmt.close()
      firstEnt.ids
    }
  }

  override def toString: String = {
    recurseRender(-1, "Delete")
  }
}
