package molecule.core.action

import molecule.boilerplate.ast.Model.Element

class Insert(elements0: List[Element])
  extends Action(elements0)

case class InsertTpls(elements0: List[Element], tpls: Seq[Product])
  extends Insert(elements0)