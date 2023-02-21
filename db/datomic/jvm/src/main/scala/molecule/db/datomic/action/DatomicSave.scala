package molecule.db.datomic.action

import molecule.boilerplate.ast.Model._
import molecule.core.action.Save

case class DatomicSave(elements: List[Element]) extends Save