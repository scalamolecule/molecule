package molecule.sql.core.transaction.strategy.save

import java.sql.Connection
import molecule.sql.core.transaction.strategy.SqlOps
import scala.collection.mutable.ListBuffer

case class SaveNs(
  parent: SaveAction,
  sqlConn: Connection,
  sqlOps: SqlOps,
  ns: String,
  action: String,
) extends SaveAction(parent, sqlConn, sqlOps, ns) {

  rowSetters += ListBuffer.empty[PS => Unit]

  override def rootAction: SaveAction = parent.rootAction

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
