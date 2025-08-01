package molecule.db.common.transaction.strategy.insert

import molecule.db.common.transaction.strategy.SqlOps

case class InsertRoot(
  sqlOps: SqlOps,
  ent: String,
  rowCount: Int
) extends InsertAction(null, sqlOps, ent, rowCount) {

  lazy val insertEnt: InsertEntity = {
    val first = InsertEntity(this, sqlOps, ent, "Entity", rowCount)
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
