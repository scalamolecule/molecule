package molecule.db.datomic.api.action

import molecule.boilerplate.ast.Model._
import molecule.core.api.action._

class DatomicDeleteApi(
  elements: List[Element],
  isMultiple: Boolean = false
) extends DatomicDeleteApiImpl(elements, isMultiple) with DeleteApi


