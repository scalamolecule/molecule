package codegen

import molecule.base.util.CodeGenBase

abstract class DatomicGenBase(fileName: String, dir: String)
  extends CodeGenBase(
    fileName,
    dir,
    "db/datomic/shared/src/main/scala/molecule/db/datomic"
  )
