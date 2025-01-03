package molecule.sql.sqlite.compliance.action.update.ops

import molecule.coreTests.setup.{MUnitSuiteWithArrays, Test}
import molecule.coreTests.spi.action.update.ops._
import molecule.sql.sqlite.setup.Api_sqlite_async

class OpsOne extends Test {
  OpsOne(this, Api_sqlite_async)
}
class OpsSet extends Test {
  OpsSet(this, Api_sqlite_async)
}
class OpsSeq extends MUnitSuiteWithArrays {
  OpsSeq(this, Api_sqlite_async)
}
class OpsMap extends Test {
  OpsMap(this, Api_sqlite_async)
}
