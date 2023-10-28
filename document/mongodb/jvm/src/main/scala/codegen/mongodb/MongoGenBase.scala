package codegen.mongodb

import molecule.base.util.CodeGenTemplate

abstract class MongoGenBase(fileName: String, dir: String)
  extends CodeGenTemplate(
    fileName,
    dir,
    "document/mongodb/jvm/src/main/scala/molecule/document/mongodb"
  )
