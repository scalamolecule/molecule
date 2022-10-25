package codegen

import molecule.base.util.CodeGenBase

abstract class CoreGenBase(fileName: String, dir: String)
  extends CodeGenBase(
    fileName,
    dir,
    "core/shared/src/main/scala/molecule/core"
  )
