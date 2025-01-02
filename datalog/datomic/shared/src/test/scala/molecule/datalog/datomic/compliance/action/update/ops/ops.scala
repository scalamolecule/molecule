package molecule.datalog.datomic.compliance.action.update.ops

import molecule.coreTests.setup.{MUnitSuiteWithArrays, Test}
import molecule.coreTests.spi.action.update.ops._
import molecule.datalog.datomic.setup.Api_datomic_async

class OpsOne extends Test {
  OpsOne(this, Api_datomic_async)
}
class OpsSet extends Test {
  OpsSet(this, Api_datomic_async)
}
class OpsSeq extends MUnitSuiteWithArrays {
  OpsSeq(this, Api_datomic_async)
}
class OpsMap extends Test {
  OpsMap(this, Api_datomic_async)
}
