package molecule.core.action

import molecule.boilerplate.ast.Model.Element

case class Insert(
  elements: List[Element],
  tpls: Seq[Product],
  private[molecule] val doInspect: Boolean = false
) extends Action(elements) {

  // Inspect also
  def i: Insert = copy(doInspect = true)
}
