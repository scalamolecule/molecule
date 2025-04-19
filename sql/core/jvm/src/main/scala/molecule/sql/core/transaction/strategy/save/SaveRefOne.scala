package molecule.sql.core.transaction.strategy.save

import java.sql.PreparedStatement as PS
import molecule.sql.core.transaction.strategy.SqlOps
import scala.collection.mutable.ListBuffer

case class SaveRefOne(
  parent: SaveAction,
  sqlOps: SqlOps,
  ent: String,
  refAttr: String,
  ref: String,
  refAttrIndex: Int
) extends SaveAction(parent, sqlOps, ref) {

  rowSetters += ListBuffer.empty[PS => Unit]

  override def rootAction: SaveAction = parent.rootAction

  override def process(): Unit = {
    children.foreach(_.process())
    insert()

    parent.rowSetters.last += {
      (ps: PS) => ps.setLong(refAttrIndex, ids.head)
    }
  }

  override def curStmt: String = {
    sqlOps.insertStmt(ref, cols, placeHolders)
  }

  override def render(indent: Int): String = {
    recurseRender(indent, "RefOne")
  }
}
