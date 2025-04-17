package molecule.datalog.datomic.compliance.action.update.ops

import molecule.coreTests.setup.{MUnitSuiteWithArrays, Test}
import molecule.coreTests.spi.action.update.ops._
import molecule.datalog.datomic.setup.Api_datomic_async

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
