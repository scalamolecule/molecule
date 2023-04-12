package molecule.datomic.action

import molecule.boilerplate.ast.Model._
import molecule.core.action.{Action, Update}

case class DatomicUpdate(
  private val elements0: List[Element],
  isUpsert: Boolean = false
) extends Action(elements0) with Update