package molecule.db.datomic.api.ops

import molecule.boilerplate.ast.Model._
import molecule.core.api.ops._

class DatomicUpdateOps(elements: Seq[Element], isUpsert: Boolean = false)
  extends DatomicUpdateOpsImpl(elements, isUpsert) with UpdateOps


