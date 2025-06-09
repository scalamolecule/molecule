package molecule.db.datalog.datomic.compliance.action.update.ops

import molecule.core.setup.{MUnit, MUnit_arrays}
import molecule.db.compliance.test.action.update.ops.{OpsMap, OpsOne, OpsSeq, OpsSet}
import molecule.db.datalog.datomic.setup.Api_datomic_async

class OpsOneTest extends MUnit {
  OpsOne(this, Api_datomic_async)
}
class OpsSetTest extends MUnit {
  OpsSet(this, Api_datomic_async)
}
class OpsSeqTest extends MUnit_arrays {
  OpsSeq(this, Api_datomic_async)
}
class OpsMapTest extends MUnit {
  OpsMap(this, Api_datomic_async)
}
