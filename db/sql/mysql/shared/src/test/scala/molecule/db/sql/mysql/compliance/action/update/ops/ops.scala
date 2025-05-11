package molecule.db.sql.mysql.compliance.action.update.ops

import molecule.db.compliance.setup.{MUnitSuiteWithArrays, Test}
import molecule.db.compliance.test.action.update.ops.{OpsMap, OpsOne, OpsSeq, OpsSet}
import molecule.db.sql
import molecule.db.sql.mysql.setup.Api_mysql_async

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
