package molecule.sql.core.transaction.strategy.delete

import java.sql.{Connection, Statement}
import molecule.base.ast._
import molecule.sql.core.transaction.strategy.SqlOps

case class DeleteRoot(
  nsMap: Map[String, MetaNs],
  sqlOps: SqlOps,
  sqlStmt: Statement,
  ns: String
) extends DeleteAction(nsMap, null, sqlStmt, sqlOps, ns) {

  val sqlConn: Connection = sqlOps.sqlConn

  val firstNs = DeleteNs(nsMap, this, sqlStmt, sqlOps, "", "", ns)
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
