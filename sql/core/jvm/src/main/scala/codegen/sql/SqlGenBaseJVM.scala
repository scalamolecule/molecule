package codegen

import molecule.base.util.CodeGenTemplate

abstract class SqlGenBaseJVM(fileName: String, dir: String)
  extends CodeGenTemplate(
    fileName,
    dir,
    "datomic/jvm/src/main/scala/molecule/datomic"
  )
