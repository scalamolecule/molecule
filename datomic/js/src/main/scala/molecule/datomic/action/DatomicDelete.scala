package molecule.datomic.action

import molecule.boilerplate.ast.Model._
import molecule.core.action.{Action, Delete}

case class DatomicDelete(private val elements0: List[Element])
  extends Action(elements0) with Delete
