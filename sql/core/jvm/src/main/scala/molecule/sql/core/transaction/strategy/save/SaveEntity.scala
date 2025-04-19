package molecule.sql.core.transaction.strategy.save

import java.sql.PreparedStatement as PS
import molecule.sql.core.transaction.strategy.SqlOps
import scala.collection.mutable.ListBuffer

case class SaveEntity(
  parent: SaveAction,
  sqlOps: SqlOps,
  ent: String,
  action: String,
) extends SaveAction(parent, sqlOps, ent) {

  rowSetters += ListBuffer.empty[PS => Unit]

  override def rootAction: SaveAction = parent.rootAction

  override def process(): Unit = {
    children.foreach(_.process())
    insert()
  }

  override def curStmt: String = {
    sqlOps.insertStmt(ent, cols, placeHolders)
  }

  override def render(indent: Int): String = {
    recurseRender(indent, action)
  }
}
