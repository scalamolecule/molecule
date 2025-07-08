package molecule.db.mariadb.compliance.filterAttr.set

import molecule.core.setup.MUnit
import molecule.db
import molecule.db.compliance.test.filterAttr.set.{Adjacent, CrossEntity, Types}
import molecule.db.mariadb.setup.Api_mariadb_async

class AdjacentTest extends MUnit {
  Adjacent(this, Api_mariadb_async)
}
class CrossEntityTest extends MUnit {
  CrossEntity(this, Api_mariadb_async)
}
class TypesTest extends MUnit {
  Types(this, Api_mariadb_async)
}
