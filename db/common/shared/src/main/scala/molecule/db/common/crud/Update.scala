package molecule.db.common.crud

import molecule.core.dataModel.DataModel

case class Update(
  dataModel: DataModel,
  private[molecule] val isUpsert: Boolean = false,
  private[molecule] val printInspect: Boolean = false,
  private[molecule] val doValidate: Boolean = true
) extends Mutation {

  // Inspect also
  def i: Update = copy(printInspect = true)
}
