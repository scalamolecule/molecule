package boilerplate.db.sql

import boilerplate.CodeGenTemplate

abstract class SqlGenBase(fileName: String, dir: String)
  extends CodeGenTemplate(
    fileName,
    dir,
    "db/sql/core/shared/src/main/scala/molecule/db/sql/core"
  )
