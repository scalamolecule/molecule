package codegen

import molecule.base.util.CodeGenTemplate
import molecule.boilerplate.util.MoleculeLogging

abstract class BoilerplateGenBase(fileName: String, dir: String)
  extends CodeGenTemplate(
    fileName,
    dir,
    "boilerplate/src/main/scala/molecule/boilerplate"
  ) with MoleculeLogging