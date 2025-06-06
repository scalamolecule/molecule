package molecule.db.sql.h2.compliance.action.update.ops

import molecule.db.compliance.setup.{MUnitSuiteWithArrays, Test}
import molecule.db.compliance.test.action.update.ops.{OpsMap, OpsOne, OpsSeq, OpsSet}
import molecule.db.sql.h2.setup.Api_h2_async

class OpsOneTest extends Test {
  OpsOne(this, Api_h2_async)
}
class OpsSetTest extends Test {
  OpsSet(this, Api_h2_async)
}
class OpsSeqTest extends MUnitSuiteWithArrays {
  OpsSeq(this, Api_h2_async)
}
class OpsMapTest extends Test {
  OpsMap(this, Api_h2_async)
}
