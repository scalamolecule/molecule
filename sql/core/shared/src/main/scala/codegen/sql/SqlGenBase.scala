package codegen

import molecule.base.util.CodeGenTemplate

abstract class SqlGenBase(fileName: String, dir: String)
  extends CodeGenTemplate(
    fileName,
    dir,
    "datomic/shared/src/main/scala/molecule/datalog/datomic"
  )
