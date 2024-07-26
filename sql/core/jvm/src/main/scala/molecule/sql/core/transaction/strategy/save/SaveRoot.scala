package molecule.sql.core.transaction.strategy.save

import molecule.sql.core.transaction.strategy.SqlOps

case class SaveRoot(
  sqlOps: SqlOps,
  ns: String
) extends SaveAction(null, sqlOps, ns) {

  val saveNs = SaveNs(this, sqlOps, ns, "Ns")
  children += saveNs

  override def rootAction: SaveAction = this

  override def executeRoot: List[Long] = {
    children.foreach(_.execute())
    children.head.ids
  }

  override def toString: String = recurseRender(-1, "Save")
}
