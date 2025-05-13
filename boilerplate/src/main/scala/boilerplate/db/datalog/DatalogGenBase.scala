package boilerplate.db.datalog

import boilerplate.CodeGenTemplate

abstract class DatalogGenBase(fileName: String, dir: String)
  extends CodeGenTemplate(
    fileName,
    dir,
    "db/datalog/core/shared/src/main/scala/molecule/db/datalog/core"
  )
