package molecule.sql.core.transaction.strategy.insert

import molecule.sql.core.transaction.strategy.SqlOps
import scala.collection.mutable.ListBuffer

case class InsertRefIds(
  parent: InsertAction,
  sqlOps: SqlOps,
  ns: String,
  refAttr: String,
  refNs: String,
) extends InsertAction(parent, sqlOps, refNs) {

  // Cache card-many ref ids for each row
  val refIdss = ListBuffer.empty[Iterable[Long]]

  def addRefIds(refIds: Iterable[Long]): Unit = {
    refIdss += refIds
  }

  override def process(): Unit = {
    val curNs = parent.children.head
    sameLength(curNs.ids.length, refIdss.length, refAttr, refNs)

    // Insert all joins
    val ps        = prepare(curStmt)
    val nsIds     = curNs.ids.iterator
    val refIdssIt = refIdss.iterator
    while (nsIds.hasNext) {
      val nsId   = nsIds.next()
      val refIds = refIdssIt.next().iterator
      while (refIds.hasNext) {
        ps.setLong(1, nsId)
        ps.setLong(2, refIds.next())
        ps.addBatch()
      }
    }
    ps.executeBatch()
    ps.close()
  }

  override def curStmt: String = {
    sqlOps.insertJoinStmt(ns, refAttr, refNs)
  }

  override def render(indent: Int): String = {
    recurseRender(indent, "RefMany")
  }
}
