package molecule.db.sql.mariadb.compliance.filterAttr.set

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.filterAttr.set.{Adjacent, CrossEntity, Types}
import molecule.db.sql
import molecule.db.sql.mariadb.setup.Api_mariadb_async

class AdjacentTest extends Test {
  Adjacent(this, Api_mariadb_async)
}
class CrossEntityTest extends Test {
  CrossEntity(this, Api_mariadb_async)
}
class TypesTest extends Test {
  Types(this, Api_mariadb_async)
}
