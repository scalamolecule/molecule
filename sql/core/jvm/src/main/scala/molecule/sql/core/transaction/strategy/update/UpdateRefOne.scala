package molecule.sql.core.transaction.strategy.update

import java.sql.{PreparedStatement => PS}
import molecule.sql.core.transaction.strategy.SqlOps
import scala.collection.mutable.ListBuffer

case class UpdateRefOne(
  parent: UpdateAction,
  sqlOps: SqlOps,
  ns: String,
  refAttr: String,
  refNs: String,
) extends UpdateAction(parent, sqlOps, refNs) {

  rowSetters += ListBuffer.empty[PS => Unit]

  override def process(): Unit = {
    children.foreach(_.process())
    updateThisAction()
  }

  override def curStmt: String = {
    if (cols.isEmpty) {
      s"no update columns in $refNs ..."
    } else if (ids.isEmpty) {
      s"no ids found to be updated in $ns ..."
    } else {
      val idClause = s"$refNs.id IN(" + ids.mkString(", ") + ")"
      sqlOps.updateStmt(refNs, cols, List(idClause))
    }
  }

  override def completeIds(refIds: Array[List[Long]]): Unit = {
    ids = getCompleteRefIds(refNs, refIds.head)
    children.foreach(_.completeIds(refIds.tail))
  }

  override def addRefs(knownIds: List[Long], newRefIds: List[Long]): Unit = {
    val newRefIds1 = newRefIds.iterator

    // Add ref ids from parent to new refs
    val parentUpdates = sqlOps.updateStmt(ns,
      List(s"$refAttr = ?"),
      List(s"id = ?")
    )
    val addRefIds     = prepare(parentUpdates)
    parent.ids.zip(knownIds).foreach {
      case (nsId, 0)  => // missing ref
        addRefIds.setLong(1, newRefIds1.next())
        addRefIds.setLong(2, nsId)
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
