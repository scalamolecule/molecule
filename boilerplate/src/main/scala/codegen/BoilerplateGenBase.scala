package codegen

import molecule.base.util.CodeGenTemplate

abstract class BoilerplateGenBase(fileName: String, dir: String)
  extends CodeGenTemplate(
    fileName,
    dir,
    "boilerplate/src/main/scala/molecule/boilerplate"
  )