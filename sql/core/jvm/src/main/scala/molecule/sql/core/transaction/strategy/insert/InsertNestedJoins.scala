package molecule.sql.core.transaction.strategy.insert

import java.sql.Connection
import molecule.sql.core.transaction.strategy.SqlOps

case class InsertNestedJoins(
  curNs: InsertAction,
  nested: InsertNs,
  sqlConn: Connection,
  sqlOps: SqlOps,
  ns: String,
  refAttr: String,
  refNs: String,
) extends InsertAction(curNs, sqlConn, sqlOps, refNs) {

  // Keep track of nested counts for joins
  private var nestedCounts = List.empty[Int]

  def addNestedCount(n: Int): Unit = {
    nestedCounts = nestedCounts :+ n
  }

  override def execute(): Unit = {
    sameLength(curNs.ids.length, nestedCounts.length, refAttr, refNs)
    val ps        = prepare(curStmt)
    val parentIds = curNs.ids.iterator
    val counts    = nestedCounts.iterator
    val nestedIds = nested.ids.iterator
    var i         = 0
    while (parentIds.hasNext) {
      val parentId    = parentIds.next()
      val nestedCount = counts.next()
      i = 0
      while (i < nestedCount) {
        ps.setLong(1, parentId)
        ps.setLong(2, nestedIds.next())
        ps.addBatch()
        i += 1
      }
    }
    ps.executeBatch()
    ps.close()
  }

  override def curStmt: String = {
    sqlOps.insertJoinStmt(ns, refAttr, refNs)
  }

  override def render(indent: Int): String = {
    recurseRender(indent, "NestedJoins")
  }
}
