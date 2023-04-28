package molecule.datomic.action

import molecule.boilerplate.ast.Model._
import molecule.core.action.Save

case class DatomicSave(private val elements0: List[Element])
  extends Save(elements0, Nil)
