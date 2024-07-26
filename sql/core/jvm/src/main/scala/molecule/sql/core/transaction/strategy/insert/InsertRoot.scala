package molecule.sql.core.transaction.strategy.insert

import molecule.sql.core.transaction.strategy.SqlOps

case class InsertRoot(
  sqlOps: SqlOps,
  ns: String,
) extends InsertAction(null, sqlOps, ns) {

  val insertNs: InsertNs = {
    val first = InsertNs(this, sqlOps, ns, "Ns")
    children += first
    first
  }

  override def rootAction: InsertAction = this

  override def executeRoot: List[Long] = {
    children.foreach(_.execute())
    children.head.ids
  }

  override def toString: String = recurseRender(-1, "Insert")
}
