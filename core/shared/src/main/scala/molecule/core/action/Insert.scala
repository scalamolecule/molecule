package molecule.core.action

import molecule.boilerplate.ast.Model.Element

case class Insert(elements0: List[Element], tpls: Seq[Product])
  extends Action(elements0)
