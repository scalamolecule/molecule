package molecule.db.sql.sqlite.compliance.filterAttr.set

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.filterAttr.set.{Adjacent, CrossEntity, Types}
import molecule.db.sql
import molecule.db.sql.sqlite.setup.Api_sqlite_async

class AdjacentTest extends Test {
  Adjacent(this, Api_sqlite_async)
}
class CrossEntityTest extends Test {
  CrossEntity(this, Api_sqlite_async)
}
class TypesTest extends Test {
  Types(this, Api_sqlite_async)
}
