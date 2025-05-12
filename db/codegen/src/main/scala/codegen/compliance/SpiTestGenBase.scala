package codegen.compliance

import codegen.base.CodeGenTemplate

abstract class SpiTestGenBase(fileName: String, dir: String)
  extends CodeGenTemplate(
    fileName,
    dir,
    "db/compliance/shared/src/test/scala/molecule/db/compliance/test"
  )

