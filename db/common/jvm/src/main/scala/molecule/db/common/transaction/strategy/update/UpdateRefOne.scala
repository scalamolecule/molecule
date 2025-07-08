package molecule.db.common.transaction.strategy.update

import java.sql.PreparedStatement as PS
import molecule.db.common.transaction.strategy.SqlOps
import scala.collection.mutable.ListBuffer

case class UpdateRefOne(
  parent: UpdateAction,
  sqlOps: SqlOps,
  ent: String,
  refAttr: String,
  ref: String,
) extends UpdateAction(parent, sqlOps, ref) {

  rowSetters += ListBuffer.empty[PS => Unit]

  override def process(): Unit = {
    children.foreach(_.process())
    updateThisAction()
  }

  override def curStmt: String = {
    if (cols.isEmpty) {
      s"no update columns in $ref ..."
    } else if (ids.isEmpty) {
      s"no ids found to be updated in $ent ..."
    } else {
      val idClause = s"$ref.id IN(" + ids.mkString(", ") + ")"
      sqlOps.updateStmt(ref, cols, List(idClause))
    }
  }

  override def completeIds(refIds: Array[List[Long]]): Unit = {
    ids = getCompleteRefIds(ref, refIds.head)
    children.foreach(_.completeIds(refIds.tail))
  }

  override def addRefs(knownIds: List[Long], newRefIds: List[Long]): Unit = {
    val newRefIds1 = newRefIds.iterator

    // Add ref ids from parent to new refs
    val parentUpdates = sqlOps.updateStmt(ent,
      List(s"$refAttr = ?"),
      List(s"id = ?")
    )
    val addRefIds     = prepare(parentUpdates)
    parent.ids.zip(knownIds).foreach {
      case (entId, 0) => // missing ref
        addRefIds.setLong(1, newRefIds1.next())
        addRefIds.setLong(2, entId)
        addRefIds.addBatch()
      case (_, refId) => () // existing ref id
    }
    addRefIds.executeBatch()
    addRefIds.close()
  }


  override def render(indent: Int): String = {
    recurseRender(indent, "UpdateRefOne")
  }
}
