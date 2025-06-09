package boilerplate.db.compliance

import boilerplate.Util

abstract class ComplianceGenBase(fileName: String, dir: String)
  extends Util(
    fileName,
    dir,
    "db/compliance/shared/src/test/scala/molecule/db/compliance/test"
  )

