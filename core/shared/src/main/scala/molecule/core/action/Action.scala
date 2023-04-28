package molecule.core.action

import molecule.boilerplate.ast.Model.Element
import molecule.core.util.ModelUtils

abstract class Action(elements0: List[Element], tpls0: Seq[Product]) extends ModelUtils {
  val elements = liftTxMetaData(elements0)
  val tpls     = tpls0
}