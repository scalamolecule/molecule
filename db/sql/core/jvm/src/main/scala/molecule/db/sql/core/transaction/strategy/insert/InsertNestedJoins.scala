package molecule.db.sql.core.transaction.strategy.insert

import molecule.db.sql.core.transaction.strategy.SqlOps

case class InsertNestedJoins(
  parent: InsertAction,
  nested: InsertEntity,
  sqlOps: SqlOps,
  ent: String,
  refAttr: String,
  ref: String,
  rowCount: Int
) extends InsertAction(parent, sqlOps, ref, rowCount) {

  // Keep track of nested count of joins for each row
  private var rowCounts = List.empty[Int]

  def addNestedCount(n: Int): Unit = {
    rowCounts = rowCounts :+ n
  }

  override def process(): Unit = {
    sameLength(parent.ids.length, rowCounts.length, refAttr, ref)
    val ps        = prepare(curStmt)
    val parentIds = parent.ids.iterator
    val counts    = rowCounts.iterator
    val nestedIds = nested.ids.iterator
    var i         = 0
    while (parentIds.hasNext) {
      val parentId = parentIds.next()
      val rowCount = counts.next()
      i = 0
      while (i < rowCount) {
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
    sqlOps.insertJoinStmt(ent, refAttr, ref)
  }

  override def render(indent: Int): String = {
    recurseRender(indent, "NestedJoins")
  }
}
