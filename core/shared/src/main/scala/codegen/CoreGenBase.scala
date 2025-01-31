package codegen

import molecule.base.util.CodeGenTemplate
import molecule.core.util.MoleculeLogging

abstract class CoreGenBase(fileName: String, dir: String)
  extends CodeGenTemplate(
    fileName,
    dir,
    "core/shared/src/main/scala/molecule/core"
  ) with MoleculeLogging
