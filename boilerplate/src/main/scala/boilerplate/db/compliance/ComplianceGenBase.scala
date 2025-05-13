package boilerplate.db.compliance

import boilerplate.CodeGenTemplate

abstract class ComplianceGenBase(fileName: String, dir: String)
  extends CodeGenTemplate(
    fileName,
    dir,
    "db/compliance/shared/src/test/scala/molecule/db/compliance/test"
  )

