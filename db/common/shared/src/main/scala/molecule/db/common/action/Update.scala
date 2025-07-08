package molecule.db.common.action

import molecule.core.dataModel.DataModel

case class Update(
  dataModel: DataModel,
  private[molecule] val isUpsert: Boolean = false,
  private[molecule] val printInspect: Boolean = false,
  private[molecule] val doValidate: Boolean = true
) extends Action {

  // Inspect also
  def i: Update = copy(printInspect = true)
}
