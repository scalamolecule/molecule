package molecule.core.action

import molecule.boilerplate.ast.Model.Element
import molecule.core.util.ModelUtils

abstract class Action(elements0: List[Element]) extends ModelUtils {
  val elements = liftTxMetaData(elements0)
}