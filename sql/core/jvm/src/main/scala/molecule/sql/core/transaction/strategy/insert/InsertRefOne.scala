package molecule.sql.core.transaction.strategy.insert

import java.sql.Connection
import molecule.sql.core.transaction.strategy.SqlOps

case class InsertRefOne(
  parent: InsertAction,
  sqlConn: Connection,
  sqlOps: SqlOps,
  ns: String,
  refAttr: String,
  refNs: String,
  refAttrIndex: Int
) extends InsertAction(parent, sqlConn, sqlOps, refNs) {

  override def rootAction: InsertAction = parent.rootAction

  override def execute(): Unit = {
    children.foreach(_.execute())
    insert()
    val refSetters = parent.rowSetters.iterator
    val refIds     = ids.iterator
    while (refIds.hasNext) {
      val refSetter = refSetters.next()
      val refId     = refIds.next()
      refSetter += ((ps: PS) => ps.setLong(refAttrIndex, refId))
    }
  }

  override def curStmt: String = {
    sqlOps.insertStmt(refNs, cols, placeHolders)
  }

  override def render(indent: Int): String = {
    // Add refAttr to parent insert
    parent.paramIndex(refAttr)
    recurseRender(indent, "RefOne")
  }
}
