package molecule.sql.mysql.compliance.action.update.ops

import molecule.coreTests.setup.{MUnitSuiteWithArrays, Test}
import molecule.coreTests.spi.action.update.ops._
import molecule.sql.mysql.setup.Api_mysql_async

class OpsOneTest extends Test {
  OpsOne(this, Api_mysql_async)
}
class OpsSetTest extends Test {
  OpsSet(this, Api_mysql_async)
}
class OpsSeqTest extends MUnitSuiteWithArrays {
  OpsSeq(this, Api_mysql_async)
}
class OpsMapTest extends Test {
  OpsMap(this, Api_mysql_async)
}
