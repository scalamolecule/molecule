package molecule.sql.core.transaction.strategy.save

import molecule.sql.core.transaction.strategy.SqlOps

case class SaveRoot(
  sqlOps: SqlOps,
  ns: String
) extends SaveAction(null, sqlOps, ns) {

  val saveNs: SaveNs = SaveNs(this, sqlOps, ns, "Ns")
  children += saveNs

  override def rootAction: SaveAction = this

  override def execute: List[Long] = {
    children.foreach(_.process())
    children.head.ids
  }

  override def toString: String = recurseRender(-1, "Save")
}
