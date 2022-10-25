package codegen

import molecule.base.util.CodeGenBase

abstract class BoilerplateGenBase(fileName: String, dir: String)
  extends CodeGenBase(
    fileName,
    dir,
    "boilerplate/src/main/scala/molecule/boilerplate"
  )