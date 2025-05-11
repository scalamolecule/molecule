package molecule.db.core.action

import molecule.db.core.ast.Element

case class Insert(
  elements: List[Element],
  tpls: Seq[Product],
  private[molecule] val doInspect: Boolean = false,
  private[molecule] val doValidate: Boolean = true
) extends Action(elements) {

  // Inspect also
  def i: Insert = copy(doInspect = true)
}
