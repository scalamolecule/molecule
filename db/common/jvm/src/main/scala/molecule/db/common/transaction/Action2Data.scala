package molecule.db.common.transaction

import molecule.base.error.ModelError
import molecule.core.dataModel.Element

trait Action2Data {

  // Used in generated validation code. Don't delete
  protected def unexpected(element: Element) =
    throw ModelError("Unexpected element: " + element)
}