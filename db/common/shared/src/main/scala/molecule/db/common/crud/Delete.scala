package molecule.db.common.crud

import molecule.core.dataModel.DataModel

case class Delete(
  dataModel: DataModel,
  private[molecule] val printInspect: Boolean = false
) extends Mutation {

  // Inspect also
  def i: Delete = copy(printInspect = true)
}
