package codegen.core

import codegen.base.CodeGenTemplate

abstract class CoreGenBase(fileName: String, dir: String)
  extends CodeGenTemplate(
    fileName,
    dir,
    "db/core/shared/src/main/scala/molecule/db/core"
  )
