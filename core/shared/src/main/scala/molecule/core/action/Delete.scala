package molecule.core.action

import molecule.core.ast.DataModel.Element

case class Delete(
  elements: List[Element],
  private[molecule] val doInspect: Boolean = false
) extends Action(elements) {

  // Inspect also
  def i: Delete = copy(doInspect = true)
}
