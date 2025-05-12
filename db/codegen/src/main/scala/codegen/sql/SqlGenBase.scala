package codegen.sql

import codegen.base.CodeGenTemplate

abstract class SqlGenBase(fileName: String, dir: String)
  extends CodeGenTemplate(
    fileName,
    dir,
    "db/sql/core/shared/src/main/scala/molecule/db/sql/core"
  )
