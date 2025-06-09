package boilerplate.db.datalog

import boilerplate.Util

abstract class DbDatalogBase(fileName: String, dir: String)
  extends Util(
    fileName,
    dir,
    "db/datalog/core/shared/src/main/scala/molecule/db/datalog/core"
  )
