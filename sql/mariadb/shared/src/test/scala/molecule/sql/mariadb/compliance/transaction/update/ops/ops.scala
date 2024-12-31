package molecule.sql.mariadb.compliance.transaction.update.ops

import molecule.coreTests.setup.{MUnitSuite, MUnitSuiteWithArrays}
import molecule.coreTests.spi.action.update.ops._
import molecule.sql.mariadb.setup.Api_mariadb_async

class OpsOne extends MUnitSuite {
  OpsOne(this, Api_mariadb_async)
}
class OpsSet extends MUnitSuite {
  OpsSet(this, Api_mariadb_async)
}
class OpsSeq extends MUnitSuiteWithArrays {
  OpsSeq(this, Api_mariadb_async)
}
class OpsMap extends MUnitSuite {
  OpsMap(this, Api_mariadb_async)
}
