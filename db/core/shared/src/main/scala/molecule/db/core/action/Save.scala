package molecule.db.core.action

import molecule.db.core.ast.Element

case class Save(
  elements: List[Element],
  private[molecule] val doInspect: Boolean = false,
  private[molecule] val doValidate: Boolean = true
) extends Action(elements) {

  // Inspect also
  def i: Save = copy(doInspect = true)
}
