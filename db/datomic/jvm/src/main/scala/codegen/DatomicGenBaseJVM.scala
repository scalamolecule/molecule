package codegen

import molecule.base.util.CodeGenBase

abstract class DatomicGenBaseJVM(fileName: String, dir: String)
  extends CodeGenBase(
    fileName,
    dir,
    "db/datomic/jvm/src/main/scala/molecule/db/datomic"
  )
