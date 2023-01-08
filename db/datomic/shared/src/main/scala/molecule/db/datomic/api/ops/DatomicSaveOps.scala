package molecule.db.datomic.api.ops

import molecule.boilerplate.ast.Model._
import molecule.core.api.ops._

class DatomicSaveOps(elements: List[Element])
  extends DatomicSaveOpsImpl(elements) with SaveOps


