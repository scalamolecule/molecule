package molecule.db.core.action

import molecule.core.ast.DataModel

case class Save(
  dataModel: DataModel,
  private[molecule] val printInspect: Boolean = false,
  private[molecule] val doValidate: Boolean = true
) extends Action {


//  println("dataModel.binds  " + dataModel.binds)

//  if (dataModel.binds != 0)
//    throw ModelError("Save action does not support bind parameters.")

  // Inspect also
  def i: Save = copy(printInspect = true)
}
