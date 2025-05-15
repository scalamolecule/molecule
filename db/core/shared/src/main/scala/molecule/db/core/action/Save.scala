package molecule.db.core.action

import molecule.db.core.ast.DataModel

case class Save(
  dataModel: DataModel,
  private[molecule] val doInspect: Boolean = false,
  private[molecule] val doValidate: Boolean = true
) extends Action {

  // Inspect also
  def i: Save = copy(doInspect = true)
}
