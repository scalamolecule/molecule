package molecule.db.datalog.datomic.compliance.action.update.ops

import molecule.db.compliance.setup.{MUnitSuiteWithArrays, Test}
import molecule.db.compliance.test.action.update.ops.{OpsMap, OpsOne, OpsSeq, OpsSet}
import molecule.db.datalog
import molecule.db.datalog.datomic.setup.Api_datomic_async

class OpsOneTest extends Test {
  OpsOne(this, Api_datomic_async)
}
class OpsSetTest extends Test {
  OpsSet(this, Api_datomic_async)
}
class OpsSeqTest extends MUnitSuiteWithArrays {
  OpsSeq(this, Api_datomic_async)
}
class OpsMapTest extends Test {
  OpsMap(this, Api_datomic_async)
}
