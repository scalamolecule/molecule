package molecule.db.datomic.api.ops

import molecule.boilerplate.ast.MoleculeModel._
import molecule.core.api.ops._

class DatomicUpdateOps(elements: Seq[Element])
  extends DatomicUpdateOpsImpl(elements) with UpdateOps


