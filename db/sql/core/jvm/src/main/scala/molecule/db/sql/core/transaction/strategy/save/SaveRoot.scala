package molecule.db.sql.core.transaction.strategy.save

import molecule.db.sql.core.transaction.strategy.SqlOps

case class SaveRoot(
  sqlOps: SqlOps,
  ent: String
) extends SaveAction(null, sqlOps, ent) {

  val saveEnt: SaveEntity = SaveEntity(this, sqlOps, ent, "Entity")
  children += saveEnt

  override def rootAction: SaveAction = this

  override def execute: List[Long] = {
    children.foreach(_.process())
    children.head.ids
  }

  override def toString: String = recurseRender(-1, "Save")
}
