package molecule.core.action

import molecule.boilerplate.ast.Model.Element

abstract class Update(elements0: List[Element], tpls0: Seq[Product])
  extends Action(elements0, tpls0)
