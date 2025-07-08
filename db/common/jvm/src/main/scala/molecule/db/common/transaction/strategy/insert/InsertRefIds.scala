package molecule.db.common.transaction.strategy.insert

import molecule.db.common.transaction.strategy.SqlOps
import scala.collection.mutable.ListBuffer

case class InsertRefIds(
  parent: InsertAction,
  sqlOps: SqlOps,
  ent: String,
  refAttr: String,
  ref: String,
  rowCount: Int
) extends InsertAction(parent, sqlOps, ref, rowCount) {

  // Cache card-many ref ids for each row
  val refIdss = ListBuffer.empty[Iterable[Long]]

  def addRefIds(refIds: Iterable[Long]): Unit = {
    refIdss += refIds
  }

  override def process(): Unit = {
    val curEnt = parent.children.head
    sameLength(curEnt.ids.length, refIdss.length, refAttr, ref)

    // Insert all joins
    val ps        = prepare(curStmt)
    val entIds    = curEnt.ids.iterator
    val refIdssIt = refIdss.iterator
    while (entIds.hasNext) {
      val entId  = entIds.next()
      val refIds = refIdssIt.next().iterator
      while (refIds.hasNext) {
        ps.setLong(1, entId)
        ps.setLong(2, refIds.next())
        ps.addBatch()
      }
    }
    ps.executeBatch()
    ps.close()
  }

  override def curStmt: String = {
    sqlOps.insertJoinStmt(ent, refAttr, ref)
  }

  override def render(indent: Int): String = {
    recurseRender(indent, "RefMany")
  }
}
