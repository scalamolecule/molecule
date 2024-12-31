package molecule.sql.mysql.compliance.transaction.update.ops

import molecule.coreTests.setup.{MUnitSuite, MUnitSuiteWithArrays}
import molecule.coreTests.spi.action.update.ops._
import molecule.sql.mysql.setup.Api_mysql_async

class OpsOne extends MUnitSuite {
  OpsOne(this, Api_mysql_async)
}
class OpsSet extends MUnitSuite {
  OpsSet(this, Api_mysql_async)
}
class OpsSeq extends MUnitSuiteWithArrays {
  OpsSeq(this, Api_mysql_async)
}
class OpsMap extends MUnitSuite {
  OpsMap(this, Api_mysql_async)
}
