package molecule.sql.h2.compliance.action.update.ops

import molecule.coreTests.setup.{MUnitSuiteWithArrays, Test}
import molecule.coreTests.spi.action.update.ops._
import molecule.sql.h2.setup.Api_h2_async

class OpsOne extends Test {
  OpsOne(this, Api_h2_async)
}
class OpsSet extends Test {
  OpsSet(this, Api_h2_async)
}
class OpsSeq extends MUnitSuiteWithArrays {
  OpsSeq(this, Api_h2_async)
}
class OpsMap extends Test {
  OpsMap(this, Api_h2_async)
}
