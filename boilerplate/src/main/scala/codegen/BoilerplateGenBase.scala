package codegen

import molecule.base.util.CodeGenTemplate
import scribe.Logging

abstract class BoilerplateGenBase(fileName: String, dir: String)
  extends CodeGenTemplate(
    fileName,
    dir,
    "boilerplate/src/main/scala/molecule/boilerplate"
  ) with Logging