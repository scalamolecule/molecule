package molecule.db.core.action

import molecule.core.ast.DataModel

case class Delete(
  dataModel: DataModel,
  private[molecule] val printInspect: Boolean = false
) extends Action {

  // Inspect also
  def i: Delete = copy(printInspect = true)
}
