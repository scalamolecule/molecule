package molecule.db.core.action

import molecule.db.core.ast.DataModel

case class Delete(
  dataModel: DataModel,
  private[molecule] val doInspect: Boolean = false
) extends Action {

  // Inspect also
  def i: Delete = copy(doInspect = true)
}
