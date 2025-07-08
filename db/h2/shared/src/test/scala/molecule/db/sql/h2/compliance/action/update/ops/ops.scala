package molecule.db.sql.h2.compliance.action.update.ops

import molecule.core.setup.{MUnit, MUnit_arrays}
import molecule.db.compliance.test.action.update.ops.{OpsMap, OpsOne, OpsSeq, OpsSet}
import molecule.db.sql.h2.setup.Api_h2_async

class OpsOneTest extends MUnit {
  OpsOne(this, Api_h2_async)
}
class OpsSetTest extends MUnit {
  OpsSet(this, Api_h2_async)
}
class OpsSeqTest extends MUnit_arrays {
  OpsSeq(this, Api_h2_async)
}
class OpsMapTest extends MUnit {
  OpsMap(this, Api_h2_async)
}
