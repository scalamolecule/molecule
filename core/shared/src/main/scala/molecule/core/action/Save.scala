package molecule.core.action

import molecule.boilerplate.ast.Model.Element

case class Save(
  elements: List[Element],
  private[molecule] val doInspect: Boolean = false
) extends Action(elements) {

  // Inspect also
  def i: Save = copy(doInspect = true)
}
