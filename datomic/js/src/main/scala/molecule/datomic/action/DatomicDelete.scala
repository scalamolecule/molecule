package molecule.datomic.action

import molecule.boilerplate.ast.Model._
import molecule.core.action.Delete

case class DatomicDelete(private val elements0: List[Element])
  extends Delete(elements0, Nil)
