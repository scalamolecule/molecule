package molecule.core.transaction

import molecule.base.util.exceptions.ExecutionError
import molecule.boilerplate.ast.Model._

trait Action2Data {

  protected def unexpected(element: Element) =
    throw ExecutionError("Unexpected element: " + element)
}