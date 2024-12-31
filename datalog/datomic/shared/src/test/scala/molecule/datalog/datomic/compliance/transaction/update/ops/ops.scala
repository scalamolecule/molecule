package molecule.datalog.datomic.compliance.transaction.update.ops

import molecule.coreTests.setup.{MUnitSuite, MUnitSuiteWithArrays}
import molecule.coreTests.spi.action.update.ops._
import molecule.datalog.datomic.setup.Api_datomic_async

class OpsOne extends MUnitSuite {
  OpsOne(this, Api_datomic_async)
}
class OpsSet extends MUnitSuite {
  OpsSet(this, Api_datomic_async)
}
class OpsSeq extends MUnitSuiteWithArrays {
  OpsSeq(this, Api_datomic_async)
}
class OpsMap extends MUnitSuite {
  OpsMap(this, Api_datomic_async)
}
