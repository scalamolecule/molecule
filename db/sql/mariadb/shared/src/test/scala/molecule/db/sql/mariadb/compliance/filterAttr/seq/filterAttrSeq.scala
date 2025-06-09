package molecule.db.sql.mariadb.compliance.filterAttr.seq

import molecule.core.setup.MUnit
import molecule.db.compliance.test.filterAttr.seq.{Adjacent, CrossEntity, Types}
import molecule.db.sql.mariadb.setup.Api_mariadb_async

class AdjacentTest extends MUnit {
  Adjacent(this, Api_mariadb_async)
}
class CrossEntityTest extends MUnit {
  CrossEntity(this, Api_mariadb_async)
}
class TypesTest extends MUnit {
  Types(this, Api_mariadb_async)
}
