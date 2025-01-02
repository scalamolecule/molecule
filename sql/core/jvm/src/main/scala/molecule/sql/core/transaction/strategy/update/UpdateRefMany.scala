package molecule.sql.core.transaction.strategy.update

import java.sql.{PreparedStatement => PS}
import molecule.sql.core.transaction.strategy.SqlOps
import scala.collection.mutable.ListBuffer

case class UpdateRefMany(
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

    // Add joins from parent to new refs
    val joinUpdates = sqlOps.insertJoinStmt(ent, refAttr, ref)
    val addJoins    = prepare(joinUpdates)
    parent.ids.zip(knownIds).foreach {
      case (entId, 0) => // missing ref
        addJoins.setLong(1, entId) // left id of join
        addJoins.setLong(2, newRefIds1.next()) // right id of join
        addJoins.addBatch()
      case (_, refId) => () // existing ref id
    }
    addJoins.executeBatch()
    addJoins.close()
  }

  override def render(indent: Int): String = {
    recurseRender(indent, "UpdateRefMany")
  }
}
