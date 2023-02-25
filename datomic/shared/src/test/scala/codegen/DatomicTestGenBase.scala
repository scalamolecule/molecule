package codegen

import molecule.base.util.CodeGenTemplate

abstract class DatomicTestGenBase(fileName: String, dir: String)
  extends CodeGenTemplate(
    fileName,
    dir,
//    "datomic/jvm/src/test/scala/molecule/datomic"
    "datomic/shared/src/test/scala/molecule/datomic"
  )
