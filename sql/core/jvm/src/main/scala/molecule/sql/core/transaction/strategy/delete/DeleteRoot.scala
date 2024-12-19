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
  fkConstraintOn: String,
  disableFKs: Boolean = true,
) extends DeleteAction(nsMap, null, sqlStmt, sqlOps, ns) {

  val sqlConn: Connection = sqlOps.sqlConn

  val firstNs = DeleteNs(nsMap, this, sqlStmt, sqlOps, "", "", ns)
  children += firstNs

  private lazy val disableFkConstraints = disableFKs && fkConstraintParam.nonEmpty
  //  println("disableFkConstraints: " + disableFkConstraints)

  override def execute: List[Long] = {
    if (firstNs.ids.isEmpty) Nil else {
      fkConstraintParam match {
        case "SQlite" => executeRoot_sqlite
        case _        => executeRoot_other
      }
    }
  }


  private def executeRoot_other: List[Long] = {
    if (disableFkConstraints) {
      // Disarm foreign key constraints while deleting relationships
      // to avoid violation warnings
      sqlStmt.addBatch(s"$fkConstraintParam = $fkConstraintOff")
    }

    // Add batches of all delete statements in graph
    children.foreach(_.process())

    if (disableFkConstraints) {
      sqlStmt.addBatch(s"$fkConstraintParam = $fkConstraintOn")
    }
    sqlStmt.executeBatch
    sqlStmt.close()

    firstNs.ids
  }

  private def executeRoot_sqlite: List[Long] = {
    if (disableFkConstraints) {
      // Turn autoCommit back on to save pragma key before deletions
      sqlConn.setAutoCommit(true)
      val off = sqlConn.prepareStatement("PRAGMA foreign_keys = 0")
      off.executeUpdate()
      off.close()

      // Execute all batches in one transaction here to be committed
      sqlConn.setAutoCommit(false)
    }

    // Add batches of all delete statements in graph
    children.foreach(_.process())

    sqlStmt.executeBatch
    sqlStmt.close()

    if (disableFkConstraints) {
      // Commit all or fail
      sqlConn.commit()

      val on = sqlConn.prepareStatement("PRAGMA foreign_keys = 1")
      on.executeUpdate()
      on.close()
    }

    firstNs.ids
  }


  override def toString: String = {
    recurseRender(-1, "Delete")
  }
}
