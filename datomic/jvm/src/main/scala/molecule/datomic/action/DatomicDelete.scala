package molecule.datomic.action

import molecule.boilerplate.ast.Model._
import molecule.core.action.Delete

case class DatomicDelete(elements: List[Element]) extends Delete
