package molecule.sql.core.transaction.strategy.delete

import java.sql.{Connection, Statement}
import molecule.base.ast._
import molecule.sql.core.transaction.strategy.SqlOps

case class DeleteRoot(
  nsMap: Map[String, MetaNs],
  sqlOps: SqlOps,
  sqlStmt: Statement,
  ns: String,
  fkConstraintParam: String,
  fkConstraintOff: String,
  fkConstraintOn: String
) extends DeleteAction(nsMap, null, sqlStmt, sqlOps, ns) {

  val sqlConn: Connection = sqlOps.sqlConn

  val firstNs = DeleteNs(nsMap, this, sqlStmt, sqlOps, "", "", ns)
  children += firstNs

  override def executeRoot: List[Long] = {
    if (firstNs.ids.isEmpty) Nil else {
      fkConstraintParam match {
        case "SQlite" => executeRoot_sqlite
        case _        => executeRoot_other
      }
    }
  }

  private def executeRoot_sqlite: List[Long] = {
    // Turn autoCommit back one to save pragma key before deletions
    sqlConn.setAutoCommit(true)
    val off = sqlConn.prepareStatement("PRAGMA foreign_keys = 0")
    off.executeUpdate()
    off.close()

    // Execute all batches in one transaction here to be committed
    sqlConn.setAutoCommit(false)

    // Add batches of all delete statements in graph
    children.foreach(_.execute())

    sqlStmt.executeBatch
    sqlStmt.close()

    // Commit all or fail
    sqlConn.commit()

    val on = sqlConn.prepareStatement("PRAGMA foreign_keys = 1")
    on.executeUpdate()
    on.close()

    firstNs.ids
  }

  private def executeRoot_other: List[Long] = {
    val disableFkConstraints = fkConstraintParam.nonEmpty

    if (disableFkConstraints) {
      // Disarm foreign key constraints while deleting relationships
      // to avoid violation warnings
      sqlStmt.addBatch(s"$fkConstraintParam = $fkConstraintOff")
    }

    // Add batches of all delete statements in graph
    children.foreach(_.execute())

    if (disableFkConstraints) {
      sqlStmt.addBatch(s"$fkConstraintParam = $fkConstraintOn")
    }
    sqlStmt.executeBatch
    sqlStmt.close()

    firstNs.ids
  }


  override def toString: String = {
    recurseRender(-1, "Delete")
  }
}
