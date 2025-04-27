package codegen

import molecule.base.util.CodeGenTemplate

abstract class DatomicGenBase(fileName: String, dir: String)
  extends CodeGenTemplate(
    fileName,
    dir,
    "datalog/core/shared/src/main/scala/molecule/db/datalog/core"
  )
