package molecule.datomic.action

import molecule.boilerplate.ast.Model._
import molecule.core.action.Update

case class DatomicUpdate(private val elements0: List[Element], isUpsert: Boolean = false)
  extends Update(elements0, Nil)
