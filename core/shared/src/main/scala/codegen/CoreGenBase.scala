package codegen

import molecule.base.util.CodeGenTemplate

abstract class CoreGenBase(fileName: String, dir: String)
  extends CodeGenTemplate(
    fileName,
    dir,
    "core/shared/src/main/scala/molecule/core"
  )
