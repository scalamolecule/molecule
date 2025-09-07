package molecule.db.common.transaction.strategy.save

import java.sql.PreparedStatement as PS
import scala.collection.mutable.ListBuffer
import molecule.db.common.transaction.strategy.SqlOps
import molecule.db.common.transaction.strategy.insert.InsertAction

case class SaveEntity(
  parent: SaveAction,
  sqlOps: SqlOps,
  ent: String,
  action: String,
) extends SaveAction(parent, sqlOps, ent) {

  rowSetters += ListBuffer.empty[PS => Unit]

  override def rootAction: SaveAction = parent.rootAction

  override def process(): Unit = {
    reverse.fold {
      children.foreach(_.process())
      insert()
    } { case (newParent, refAttrIndex) =>
      saveAndPrepareManyToOneRef(newParent, refAttrIndex)
    }
  }

  override def curStmt: String = {
    sqlOps.insertStmt(ent, cols, placeHolders)
  }

  override def render(indent: Int): String = {
    recurseRender(indent, action)
  }
}
