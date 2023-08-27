package molecule.core.action

import molecule.boilerplate.ast.Model.Element

case class Insert(elements: List[Element], tpls: Seq[Product])
  extends Action(elements)
