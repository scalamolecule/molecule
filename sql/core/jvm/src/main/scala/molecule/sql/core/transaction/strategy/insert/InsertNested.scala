package molecule.sql.core.transaction.strategy.insert

import java.sql.Connection
import molecule.sql.core.transaction.strategy.SqlOps

case class InsertNested(
  parent: InsertAction,
  sqlConn: Connection,
  sqlOps: SqlOps,
  ns: String,
  refAttr: String,
  refNs: String,
) extends InsertAction(sqlConn, sqlOps, refNs) {

  override def initialAction: InsertAction = parent.initialAction
  override def backRef: InsertAction = parent

  override def execute: List[Long] = {
    val nestedIds = insert

    // Join each parent row with its nested rows
    addPostSetter((parentIds: List[Long]) => {
      //      println("  parentIds   : " + parentIds)
      //      println("  nestedCounts: " + getNestedCounts)
      val joinStmt     = sqlOps.getJoinStmt(ns, refAttr, refNs)
      val ps           = sqlConn.prepareStatement(joinStmt)
      val nestedCounts = getNestedCounts //.iterator
      val nestedIdsIt  = nestedIds.iterator
      var i            = 0
      parentIds.zip(nestedCounts).foreach {
        case (parentId, nestedCount) =>
          i = 0
          while (i < nestedCount) {
            ps.setLong(1, parentId)
            ps.setLong(2, nestedIdsIt.next())
            ps.addBatch()
            i += 1
          }
      }
      ps.executeBatch()
      ps.close()
    })

    nestedIds
  }

  override def render(indent: Int): String = {
    // show join table after parent insert
    parent.postStmts += sqlOps.getJoinStmt(ns, refAttr, refNs)
    recurseRender(indent, "InsertNested")
  }
}
