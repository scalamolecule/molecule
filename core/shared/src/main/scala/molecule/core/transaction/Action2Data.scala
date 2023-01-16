package molecule.core.transaction

import molecule.base.util.exceptions.MoleculeError
import molecule.boilerplate.ast.Model._

trait Action2Data {

  protected def unexpected(element: Element) =
    throw MoleculeError("Unexpected element: " + element)
}