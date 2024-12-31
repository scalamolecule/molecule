package molecule.sql.postgres.compliance.transaction.update.ops

import molecule.coreTests.setup.{MUnitSuite, MUnitSuiteWithArrays}
import molecule.coreTests.spi.action.update.ops._
import molecule.sql.postgres.setup.Api_postgres_async

class OpsOne extends MUnitSuite {
  OpsOne(this, Api_postgres_async)
}
class OpsSet extends MUnitSuite {
  OpsSet(this, Api_postgres_async)
}
class OpsSeq extends MUnitSuiteWithArrays {
  OpsSeq(this, Api_postgres_async)
}
class OpsMap extends MUnitSuite {
  OpsMap(this, Api_postgres_async)
}
