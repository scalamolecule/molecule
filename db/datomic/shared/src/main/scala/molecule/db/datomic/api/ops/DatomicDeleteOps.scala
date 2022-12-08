package molecule.db.datomic.api.ops

import molecule.boilerplate.ast.MoleculeModel._
import molecule.core.api.ops._

class DatomicDeleteOps(elements: Seq[Element])
  extends DatomicDeleteOpsImpl(elements) with DeleteOps


