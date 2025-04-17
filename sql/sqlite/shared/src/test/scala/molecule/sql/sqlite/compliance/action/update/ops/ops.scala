package molecule.sql.sqlite.compliance.action.update.ops

import molecule.coreTests.setup.{MUnitSuiteWithArrays, Test}
import molecule.coreTests.spi.action.update.ops._
import molecule.sql.sqlite.setup.Api_sqlite_async

class OpsOneTest extends Test {
  OpsOne(this, Api_sqlite_async)
}
class OpsSetTest extends Test {
  OpsSet(this, Api_sqlite_async)
}
class OpsSeqTest extends MUnitSuiteWithArrays {
  OpsSeq(this, Api_sqlite_async)
}
class OpsMapTest extends Test {
  OpsMap(this, Api_sqlite_async)
}
