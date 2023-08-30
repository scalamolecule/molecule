package molecule.core.action

import molecule.boilerplate.ast.Model.Element

case class Update(
  elements: List[Element],
  private[molecule] val isUpsert: Boolean = false,
  private[molecule] val doInspect: Boolean = false
) extends Action(elements) {

  // Inspect also
  def i: Update = copy(doInspect = true)
}
