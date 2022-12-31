package molecule.core.transaction

import molecule.base.util.exceptions.MoleculeException
import molecule.boilerplate.ast.Model._

trait Action2Data {

  protected def unexpected(element: Element) =
    throw MoleculeException("Unexpected element: " + element)
}