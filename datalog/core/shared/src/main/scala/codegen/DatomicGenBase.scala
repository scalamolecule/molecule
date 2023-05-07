package codegen

import molecule.base.util.CodeGenTemplate

abstract class DatomicGenBase(fileName: String, dir: String)
  extends CodeGenTemplate(
    fileName,
    dir,
    "datomic/shared/src/main/scala/molecule/datalog/datomic"
  )
