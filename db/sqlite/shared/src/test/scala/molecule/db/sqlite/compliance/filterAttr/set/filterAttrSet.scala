package molecule.db.sqlite.compliance.filterAttr.set

import molecule.core.setup.MUnit
import molecule.db
import molecule.db.compliance.test.filterAttr.set.{Adjacent, CrossEntity, Types}
import molecule.db.sqlite.setup.Api_sqlite_async

class AdjacentTest extends MUnit {
  Adjacent(this, Api_sqlite_async)
}
class CrossEntityTest extends MUnit {
  CrossEntity(this, Api_sqlite_async)
}
class TypesTest extends MUnit {
  Types(this, Api_sqlite_async)
}
