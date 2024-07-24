package molecule.sql.core.transaction.strategy.update

import java.sql.Connection
import molecule.sql.core.transaction.strategy.SqlOps
import scala.collection.mutable.ListBuffer

case class UpdateNs(
  parent: UpdateAction,
  sqlConn: Connection,
  sqlOps: SqlOps,
  isUpsert: Boolean,
  ns: String,
  action: String
) extends UpdateAction(parent, sqlConn, sqlOps, isUpsert, ns) {

  rowSetters += ListBuffer.empty[PS => Unit]

  override def rootAction: UpdateAction = parent.rootAction

  override def execute(): Unit = {
    children.foreach(_.execute())
    update()
  }

  override def curStmt: String = {
    if (cols.isEmpty) {
      s"no update columns in $ns ..."
    } else {
      val idClause = s"$ns.id IN(" + ids.mkString(", ") + ")"
      sqlOps.updateStmt(ns, cols, idClause +: mandatoryCols)
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
