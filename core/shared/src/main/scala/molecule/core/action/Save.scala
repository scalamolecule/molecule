package molecule.core.action

import molecule.boilerplate.ast.Model.Element

case class Save(elements: List[Element])
  extends Action(elements)
