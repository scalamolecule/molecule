package codegen

import molecule.base.util.CodeGenTemplate

abstract class DatomicTestGenBase(fileName: String, dir: String)
  extends CodeGenTemplate(
    fileName,
    dir,
    "db/datomic/jvm/src/test/scala/molecule/db/datomic"
//    "db/datomic/shared/src/test/scala/molecule/db/datomic"
  )
