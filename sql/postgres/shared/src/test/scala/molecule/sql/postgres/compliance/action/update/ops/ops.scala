package molecule.sql.postgres.compliance.action.update.ops

import molecule.coreTests.setup.{MUnitSuiteWithArrays, Test}
import molecule.coreTests.spi.action.update.ops.*
import molecule.sql.postgres.setup.Api_postgres_async

class OpsOneTest extends Test {
  OpsOne(this, Api_postgres_async)
}
class OpsSetTest extends Test {
  OpsSet(this, Api_postgres_async)
}
class OpsSeqTest extends MUnitSuiteWithArrays {
  OpsSeq(this, Api_postgres_async)
}
class OpsMapTest extends Test {
  OpsMap(this, Api_postgres_async)
}
