package molecule.db.sqlite.compliance.action.update.ops

import molecule.core.setup.{MUnit, MUnit_arrays}
import molecule.db
import molecule.db.compliance.test.action.update.ops.{OpsMap, OpsOne, OpsSeq, OpsSet}
import molecule.db.sqlite.setup.Api_sqlite_async

class OpsOneTest extends MUnit {
  OpsOne(this, Api_sqlite_async)
}
class OpsSetTest extends MUnit {
  OpsSet(this, Api_sqlite_async)
}
class OpsSeqTest extends MUnit_arrays {
  OpsSeq(this, Api_sqlite_async)
}
class OpsMapTest extends MUnit {
  OpsMap(this, Api_sqlite_async)
}
