package molecule.db.datomic.action

import molecule.boilerplate.ast.Model._
import molecule.core.action.Update

case class DatomicUpdate(
  elements: List[Element],
  isUpsert: Boolean = false
) extends Update