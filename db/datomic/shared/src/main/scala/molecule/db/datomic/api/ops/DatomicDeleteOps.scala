package molecule.db.datomic.api.ops

import molecule.boilerplate.ast.Model._
import molecule.core.api.ops._

class DatomicDeleteOps(
  elements: List[Element],
  isMultiple: Boolean = false
) extends DatomicDeleteOpsImpl(elements, isMultiple) with DeleteOps


