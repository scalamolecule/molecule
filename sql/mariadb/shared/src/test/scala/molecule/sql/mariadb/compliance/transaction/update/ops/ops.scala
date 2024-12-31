package molecule.sql.mariadb.compliance.transaction.update.ops

import molecule.coreTests.setup.{Test, MUnitSuiteWithArrays}
import molecule.coreTests.spi.transaction.update.ops._
import molecule.sql.mariadb.setup.Api_mariadb_async

class OpsOne extends Test {
  OpsOne(this, Api_mariadb_async)
}
class OpsSet extends Test {
  OpsSet(this, Api_mariadb_async)
}
class OpsSeq extends MUnitSuiteWithArrays {
  OpsSeq(this, Api_mariadb_async)
}
class OpsMap extends Test {
  OpsMap(this, Api_mariadb_async)
}
