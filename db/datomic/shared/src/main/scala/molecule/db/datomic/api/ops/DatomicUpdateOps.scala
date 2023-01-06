package molecule.db.datomic.api.ops

import molecule.boilerplate.ast.Model._
import molecule.core.api.Connection
import molecule.core.api.ops._

class DatomicUpdateOps(
  elements: Seq[Element],
  isUpsert: Boolean = false,
  isMultiple: Boolean = false,
) extends DatomicUpdateOpsImpl(elements, isUpsert, isMultiple) with UpdateOps


