package molecule.core.transaction

import molecule.base.error.ExecutionError
import molecule.boilerplate.ast.Model._

trait Action2Data {

  protected def unexpected(element: Element) =
    throw ExecutionError("Unexpected element: " + element)
}