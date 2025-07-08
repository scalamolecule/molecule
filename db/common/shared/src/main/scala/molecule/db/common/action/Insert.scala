package molecule.db.common.action

import molecule.core.dataModel.DataModel

case class Insert(
  dataModel: DataModel,
  tpls: Seq[Product],
  private[molecule] val printInspect: Boolean = false,
  private[molecule] val doValidate: Boolean = true
) extends Action {

  // Inspect also
  def i: Insert = copy(printInspect = true)
}
