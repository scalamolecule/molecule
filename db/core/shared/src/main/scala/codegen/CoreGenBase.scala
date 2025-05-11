package codegen

import molecule.db.core.util.MoleculeLogging
import molecule.db.base.util.CodeGenTemplate

abstract class CoreGenBase(fileName: String, dir: String)
  extends CodeGenTemplate(
    fileName,
    dir,
    "core/shared/src/main/scala/molecule/core"
  ) with MoleculeLogging
