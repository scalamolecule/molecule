package molecule.sql.core.transaction.strategy.update

import java.sql.Connection
import molecule.boilerplate.ast.Model.Element
import molecule.sql.core.query.Model2SqlQuery
import molecule.sql.core.transaction.strategy.SqlOps
import scala.collection.mutable.ListBuffer

case class UpdateRefMany(
  parent: UpdateAction,
  sqlConn: Connection,
  sqlOps: SqlOps,
  isUpsert: Boolean,
  ns: String,
  refAttr: String,
  refNs: String,
) extends UpdateAction(parent, sqlConn, sqlOps, isUpsert, refNs) {

  rowSetters += ListBuffer.empty[PS => Unit]

  override def execute(): Unit = {
    children.foreach(_.execute())
    update()
  }

  override def curStmt: String = {
    if (cols.isEmpty) {
      s"no update columns in $refNs ..."
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

    // Add joins from parent to new refs
    val joinUpdates = sqlOps.insertJoinStmt(ns, refAttr, refNs)
    val addJoins     = prepare(joinUpdates)
    parent.ids.zip(knownIds).foreach {
      case (nsId, 0)  => // missing ref
        addJoins.setLong(1, nsId) // left id of join
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
