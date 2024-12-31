package molecule.sql.h2.compliance.transaction.update.ops

import molecule.coreTests.setup.{MUnitSuite, MUnitSuiteWithArrays}
import molecule.coreTests.spi.action.update.ops._
import molecule.sql.h2.setup.Api_h2_async

class OpsOne extends MUnitSuite {
  OpsOne(this, Api_h2_async)
}
class OpsSet extends MUnitSuite {
  OpsSet(this, Api_h2_async)
}
class OpsSeq extends MUnitSuiteWithArrays {
  OpsSeq(this, Api_h2_async)
}
class OpsMap extends MUnitSuite {
  OpsMap(this, Api_h2_async)
}
