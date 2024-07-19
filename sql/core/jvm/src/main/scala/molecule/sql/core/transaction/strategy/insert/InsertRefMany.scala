package molecule.sql.core.transaction.strategy.insert

import java.sql.Connection
import molecule.sql.core.transaction.strategy.SqlOps

case class InsertRefMany(
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
    val refIds = insert

    // Add joins once we have parent ids
    addPostSetter(
      (parentIds: List[Long]) => {
        val (l1, l2) = (parentIds.length, refIds.length)
        if (l1 != l2) {
          throw new Exception(
            s"Unexpected different number of left/right ids for " +
              s"joinTable ${ns}_${refAttr}_$refNs: $l1/$l2"
          )
        }
        val ps       = prepare(sqlOps.getJoinStmt(ns, refAttr, refNs))
        val leftIds  = parentIds.iterator
        val rightIds = refIds.iterator
        while (rightIds.hasNext) {
          ps.setLong(1, leftIds.next())
          ps.setLong(2, rightIds.next())
          ps.addBatch()
        }
        ps.executeBatch()
        ps.close()
      }
    )

    refIds
  }

  override def render(indent: Int): String = {
    // show join table after parent insert
    parent.postStmts += sqlOps.getJoinStmt(ns, refAttr, refNs)
    recurseRender(indent, "InsertRefMany")
  }
}
