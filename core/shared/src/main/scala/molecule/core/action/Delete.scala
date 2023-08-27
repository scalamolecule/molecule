package molecule.core.action

import molecule.boilerplate.ast.Model.Element

case class Delete(elements: List[Element])
  extends Action(elements)
