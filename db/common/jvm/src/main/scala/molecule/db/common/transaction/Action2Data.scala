package molecule.db.common.transaction

import molecule.core.dataModel.Element
import molecule.core.error.ModelError

trait Action2Data {

  // Used in generated validation code. Don't delete
  protected def unexpected(element: Element) =
    throw ModelError("Unexpected element: " + element)
}