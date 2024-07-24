package molecule.sql.core.transaction.strategy.update

import java.sql.Connection
import molecule.sql.core.transaction.strategy.SqlOps
import scala.collection.mutable.ListBuffer

case class UpdateRefOne(
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
    ids = getCompleteRefIds(refIds.head)
    children.foreach(_.completeIds(refIds.tail))
  }

  private def getCompleteRefIds(knownIds: List[Long]): List[Long] = {
    // Insert empty ref rows for each missing id (0)
    val insertEmptyRefRows = prepare(sqlOps.insertStmt(refNs, Nil, Nil))
    (1 to knownIds.count(_ == 0L)).foreach(_ => insertEmptyRefRows.addBatch())
    val newRefIds  = sqlOps.getIds(sqlConn, refNs, insertEmptyRefRows)
    val newRefIds1 = newRefIds.iterator

    // Add ref id to parent ns where ref ids are missing
    val nsUpdate  = sqlOps.updateStmt(ns,
      List(s"$refAttr = ?"),
      List(s"id = ?")
    )
    val addRefIds = prepare(nsUpdate)
    parent.ids.zip(knownIds).map {
      case (nsId, 0)  => // no ref id
        addRefIds.setLong(1, newRefIds1.next())
        addRefIds.setLong(2, nsId)
        addRefIds.addBatch()
      case (_, refId) => refId // existing ref id
    }
    addRefIds.executeBatch()
    addRefIds.close()

    // Return completed referenced ref ids
    val newRefIds2 = newRefIds.iterator
    knownIds.map {
      case 0     => newRefIds2.next()
      case refId => refId
    }
  }

  override def render(indent: Int): String = {
    recurseRender(indent, "UpdateRef")
  }
}
