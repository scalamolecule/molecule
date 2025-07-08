package molecule.db.common.transaction.strategy.update

import java.sql.PreparedStatement as PS
import molecule.db.common.transaction.strategy.SqlOps
import scala.collection.mutable.ListBuffer

case class UpdateEntity(
  parent: UpdateAction,
  sqlOps: SqlOps,
  ent: String,
  action: String
) extends UpdateAction(parent, sqlOps, ent) {

  rowSetters += ListBuffer.empty[PS => Unit]

  override def rootAction: UpdateAction = parent.rootAction

  override def process(): Unit = {
    children.foreach(_.process())
    updateThisAction()
  }

  override def curStmt: String = {
    if (cols.isEmpty) {
      s"no update columns in $ent ..."
    } else if (ids.isEmpty) {
      s"no ids found to be updated in $ent ..."
    } else {
      val idClause = s"$ent.id IN(" + ids.mkString(", ") + ")"
      sqlOps.updateStmt(ent, cols, idClause +: mandatoryCols)
    }
  }

  override def completeIds(refIds: Array[List[Long]]): Unit = {
    ids = refIds.head
    children.foreach(_.completeIds(refIds.tail))
  }

  override def render(indent: Int): String = {
    recurseRender(indent, action)
  }
}
