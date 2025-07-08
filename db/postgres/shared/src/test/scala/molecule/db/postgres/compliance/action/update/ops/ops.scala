package molecule.db.postgres.compliance.action.update.ops

import molecule.core.setup.{MUnit, MUnit_arrays}
import molecule.db
import molecule.db.compliance.test.action.update.ops.{OpsMap, OpsOne, OpsSeq, OpsSet}
import molecule.db.postgres.setup.Api_postgres_async

class OpsOneTest extends MUnit {
  OpsOne(this, Api_postgres_async)
}
class OpsSetTest extends MUnit {
  OpsSet(this, Api_postgres_async)
}
class OpsSeqTest extends MUnit_arrays {
  OpsSeq(this, Api_postgres_async)
}
class OpsMapTest extends MUnit {
  OpsMap(this, Api_postgres_async)
}
