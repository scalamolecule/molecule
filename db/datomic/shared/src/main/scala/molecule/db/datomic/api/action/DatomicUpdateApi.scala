package molecule.db.datomic.api.action

import molecule.boilerplate.ast.Model._
import molecule.core.api.Connection
import molecule.core.api.action._

class DatomicUpdateApi(
  elements: List[Element],
  isUpsert: Boolean = false,
  isMultiple: Boolean = false,
) extends DatomicUpdateApiImpl(elements, isUpsert, isMultiple) with UpdateApi


