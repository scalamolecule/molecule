package molecule.core.transaction

import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._

trait Action2Data {

  protected def unexpected(element: Element) =
    throw ModelError("Unexpected element: " + element)
}