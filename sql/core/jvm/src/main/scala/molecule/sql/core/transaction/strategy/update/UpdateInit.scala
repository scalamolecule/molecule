package molecule.sql.core.transaction.strategy.update

import java.sql.Connection
import molecule.sql.core.transaction.strategy.SqlOps

// Initial update action before we know if ids or filter attributes
// will identify the rows to be updated
case class UpdateInit(
  sqlConn: Connection,
  ns: String,
)(implicit sqlOps: SqlOps) extends UpdateAction(sqlConn, sqlOps, ns) {

  // Delegated to actual action
  override def initialAction: UpdateAction = ???
  override def execute: List[Long] = ???
  override def toString: String = ???
}
