package molecule.db.datomic.action

import molecule.boilerplate.ast.Model._
import molecule.core.action.Update

case class DatomicUpdateImpl(
  elements: List[Element],
  isUpsert: Boolean = false
) extends Update