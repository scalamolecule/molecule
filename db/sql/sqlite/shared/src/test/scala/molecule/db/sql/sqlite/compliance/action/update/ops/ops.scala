package molecule.db.sql.sqlite.compliance.action.update.ops

import molecule.db.compliance.setup.{MUnitSuiteWithArrays, Test}
import molecule.db.compliance.test.action.update.ops.{OpsMap, OpsOne, OpsSeq, OpsSet}
import molecule.db.sql
import molecule.db.sql.sqlite.setup.Api_sqlite_async

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
