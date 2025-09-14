package molecule.db.common.crud

import molecule.core.dataModel.DataModel

case class Insert(
  dataModel: DataModel,
  tpls: Seq[Product],
  private[molecule] val printInspect: Boolean = false,
  private[molecule] val doValidate: Boolean = true
) extends Mutation {

  // Inspect also
  def i: Insert = copy(printInspect = true)
}
