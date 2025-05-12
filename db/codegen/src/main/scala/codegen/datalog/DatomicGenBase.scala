package codegen.datalog

import codegen.base.CodeGenTemplate

abstract class DatomicGenBase(fileName: String, dir: String)
  extends CodeGenTemplate(
    fileName,
    dir,
    "db/datalog/core/shared/src/main/scala/molecule/db/datalog/core"
  )
