package molecule.db.core.transaction

import molecule.base.error.ModelError
import molecule.core.ast.Element

trait Action2Data {

  // Used in generated validation code. Don't delete
  protected def unexpected(element: Element) =
    throw ModelError("Unexpected element: " + element)
}