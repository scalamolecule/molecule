package codegen

import molecule.base.util.CodeGenTemplate

abstract class DatomicGenBaseJVM(fileName: String, dir: String)
  extends CodeGenTemplate(
    fileName,
    dir,
    "datomic/jvm/src/main/scala/molecule/datomic"
  )
