package molecule.db.datomic.api

import molecule.core.api.SaveOps
import molecule.boilerplate.ast.MoleculeModel._

class DatomicSaveOps(elements: Seq[Element])
  extends DatomicSaveOpsImpl(elements) with SaveOps


