package codegen.sql

import molecule.base.util.CodeGenTemplate

abstract class SqlGenBase(fileName: String, dir: String)
  extends CodeGenTemplate(
    fileName,
    dir,
    "sql/core/shared/src/main/scala/molecule/sql/core"
  )
