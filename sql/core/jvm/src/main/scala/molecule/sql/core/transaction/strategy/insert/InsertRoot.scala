package molecule.sql.core.transaction.strategy.insert

import molecule.sql.core.transaction.strategy.SqlOps

case class InsertRoot(
  sqlOps: SqlOps,
  ns: String,
  rowCount: Int
) extends InsertAction(null, sqlOps, ns, rowCount) {

  val insertNs: InsertNs = {
    val first = InsertNs(this, sqlOps, ns, "Ns", rowCount)
    children += first
    first
  }

  override def rootAction: InsertAction = this

  override def execute: List[Long] = {
    children.foreach(_.process())
    children.head.ids
  }

  override def toString: String = recurseRender(-1, "Insert")
}
