package molecule.db.datomic.action

import molecule.boilerplate.ast.Model._
import molecule.core.action.Delete

case class DatomicDeleteImpl(elements: List[Element]) extends Delete
