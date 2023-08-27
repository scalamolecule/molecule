package molecule.core.action

import molecule.boilerplate.ast.Model.Element

case class Update(elements: List[Element], isUpsert: Boolean = false)
  extends Action(elements)
