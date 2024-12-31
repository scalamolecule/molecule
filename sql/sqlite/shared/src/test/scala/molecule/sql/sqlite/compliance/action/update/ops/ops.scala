package molecule.sql.sqlite.compliance.action.update.ops

import molecule.coreTests.setup.{MUnitSuite, MUnitSuiteWithArrays}
import molecule.coreTests.spi.action.update.ops._
import molecule.sql.sqlite.setup.Api_sqlite_async

class OpsOne extends MUnitSuite {
  OpsOne(this, Api_sqlite_async)
}
class OpsSet extends MUnitSuite {
  OpsSet(this, Api_sqlite_async)
}
class OpsSeq extends MUnitSuiteWithArrays {
  OpsSeq(this, Api_sqlite_async)
}
class OpsMap extends MUnitSuite {
  OpsMap(this, Api_sqlite_async)
}
