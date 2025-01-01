package molecule.sql.mysql.compliance.action.update.ops

import molecule.coreTests.setup.{Test, MUnitSuiteWithArrays}
import molecule.coreTests.spi.action.update.ops._
import molecule.sql.mysql.setup.Api_mysql_async

class OpsOne extends Test {
  OpsOne(this, Api_mysql_async)
}
class OpsSet extends Test {
  OpsSet(this, Api_mysql_async)
}
class OpsSeq extends MUnitSuiteWithArrays {
  OpsSeq(this, Api_mysql_async)
}
class OpsMap extends Test {
  OpsMap(this, Api_mysql_async)
}
