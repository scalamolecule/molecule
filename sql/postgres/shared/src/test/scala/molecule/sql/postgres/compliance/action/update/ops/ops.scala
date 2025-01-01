package molecule.sql.postgres.compliance.action.update.ops

import molecule.coreTests.setup.{Test, MUnitSuiteWithArrays}
import molecule.coreTests.spi.action.update.ops._
import molecule.sql.postgres.setup.Api_postgres_async

class OpsOne extends Test {
  OpsOne(this, Api_postgres_async)
}
class OpsSet extends Test {
  OpsSet(this, Api_postgres_async)
}
class OpsSeq extends MUnitSuiteWithArrays {
  OpsSeq(this, Api_postgres_async)
}
class OpsMap extends Test {
  OpsMap(this, Api_postgres_async)
}
