package molecule.db.sql.postgres.compliance.action.update.ops

import molecule.db.compliance.setup.{MUnitSuiteWithArrays, Test}
import molecule.db.compliance.test.action.update.ops.{OpsMap, OpsOne, OpsSeq, OpsSet}
import molecule.db.sql
import molecule.db.sql.postgres.setup.Api_postgres_async

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
