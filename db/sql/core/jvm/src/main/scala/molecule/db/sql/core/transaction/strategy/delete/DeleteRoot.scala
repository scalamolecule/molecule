package molecule.db.sql.core.transaction.strategy.delete

import java.sql.{Connection, Statement}
import molecule.db.core.api.MetaDb
import molecule.db.sql.core.transaction.strategy.SqlOps

case class DeleteRoot(
  sqlOps: SqlOps,
  sqlStmt: Statement,
  ent: String,
  metaDb: MetaDb,
) extends DeleteAction(null, sqlStmt, sqlOps, ent, metaDb) {

  val sqlConn: Connection = sqlOps.sqlConn

  val firstEnt = DeleteEntity(this, sqlStmt, sqlOps, "", "", ent, metaDb)
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
