package molecule.db.datomic.action

import molecule.boilerplate.ast.Model._
import molecule.core.action.Save

case class DatomicSaveImpl(elements: List[Element]) extends Save