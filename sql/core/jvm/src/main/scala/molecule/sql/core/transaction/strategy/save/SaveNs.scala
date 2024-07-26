package molecule.sql.core.transaction.strategy.save

import java.sql.{PreparedStatement => PS}
import molecule.sql.core.transaction.strategy.SqlOps
import scala.collection.mutable.ListBuffer

case class SaveNs(
  parent: SaveAction,
  sqlOps: SqlOps,
  ns: String,
  action: String,
) extends SaveAction(parent, sqlOps, ns) {

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
