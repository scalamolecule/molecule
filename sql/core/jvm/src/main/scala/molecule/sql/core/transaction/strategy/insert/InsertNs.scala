package molecule.sql.core.transaction.strategy.insert

import java.sql.Connection
import molecule.sql.core.transaction.strategy.SqlOps

case class InsertNs(
  parent: InsertAction,
  sqlConn: Connection,
  sqlOps: SqlOps,
  ns: String,
  action: String,
) extends InsertAction(parent, sqlConn, sqlOps, ns) {

  override def rootAction: InsertAction = parent.rootAction

  override def execute(): Unit = {
    children.foreach(_.execute())
    insert()
  }

  override def curStmt: String = {
    sqlOps.insertStmt(ns, cols, placeHolders)
  }

  override def render(indent: Int): String = {
    recurseRender(indent, action)
  }
}
