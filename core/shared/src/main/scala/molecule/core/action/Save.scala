package molecule.core.action

import molecule.boilerplate.ast.Model.Element

abstract class Save(elements0: List[Element], tpls0: Seq[Product])
  extends Action(elements0, tpls0)

case class Save1(elements0: List[Element], tpls0: Seq[Product])
  extends Save(elements0, tpls0)
