package molecule.sql.core.transaction.strategy.insert

import java.sql.Connection
import molecule.sql.core.transaction.strategy.SqlOps

/*
// Initial insert action

1. Recursively build graph of Product => PS (PreparedStatement) => Unit setters
2. Create PS for each table in the graph
3. Loop row tuples and recursively populate PS's for each tuple/sub-tuple in graph
4. Execute ps.executeBatch for each table in the graph
 */
case class InsertNs(
  sqlConn: Connection,
  ns: String,
)(implicit sqlOps: SqlOps) extends InsertAction(sqlConn, sqlOps, ns) {

  // Initial namespace
  override def initialAction: InsertAction = this

  override def execute: List[Long] = insert

  override def toString: String = recurseRender(0, "InsertNs")
}
