package molecule.db.h2.compliance.crud.update.ops

import molecule.core.setup.{MUnit, MUnit_arrays}
import molecule.db
import molecule.db.compliance.test.crud.update.ops.{OpsMap, OpsOne, OpsSeq, OpsSet}
import molecule.db.h2.setup.Api_h2_async

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
