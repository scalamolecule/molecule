package molecule.db.mariadb.compliance.crud.update.ops

import molecule.core.setup.{MUnit, MUnit_arrays}
import molecule.db
import molecule.db.compliance.test.crud.update.ops.{OpsMap, OpsOne, OpsSeq, OpsSet}
import molecule.db.mariadb.setup.Api_mariadb_async

class OpsOneTest extends MUnit {
  OpsOne(this, Api_mariadb_async)
}
class OpsSetTest extends MUnit {
  OpsSet(this, Api_mariadb_async)
}
class OpsSeqTest extends MUnit_arrays {
  OpsSeq(this, Api_mariadb_async)
}
class OpsMapTest extends MUnit {
  OpsMap(this, Api_mariadb_async)
}
