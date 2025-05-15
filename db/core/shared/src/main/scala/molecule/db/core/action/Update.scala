package molecule.db.core.action

import molecule.db.core.ast.DataModel

case class Update(
  dataModel: DataModel,
  private[molecule] val isUpsert: Boolean = false,
  private[molecule] val doInspect: Boolean = false,
  private[molecule] val doValidate: Boolean = true
) extends Action {

  // Inspect also
  def i: Update = copy(doInspect = true)
}
