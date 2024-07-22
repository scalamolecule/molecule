package molecule.sql.core.transaction.strategy.save

import java.sql.Connection
import molecule.sql.core.transaction.strategy.SqlOps
import scala.collection.mutable.ListBuffer

case class SaveRefOne(
  parent: SaveAction,
  sqlConn: Connection,
  sqlOps: SqlOps,
  ns: String,
  refAttr: String,
  refNs: String,
  refAttrIndex: Int
) extends SaveAction(parent, sqlConn, sqlOps, refNs) {

  rowSetters += ListBuffer.empty[PS => Unit]

  override def rootAction: SaveAction = parent.rootAction

  override def execute(): Unit = {
    children.foreach(_.execute())
    insert()

    parent.rowSetters.last += {
      (ps: PS) => ps.setLong(refAttrIndex, ids.head)
    }
  }

  override def curStmt: String = {
    sqlOps.insertStmt(refNs, cols, placeHolders)
  }

  override def render(indent: Int): String = {
    recurseRender(indent, "RefOne")
  }
}
