package molecule.db.sql.sqlite.compliance.filterAttr.seq

import molecule.core.setup.MUnit
import molecule.db.compliance.test.filterAttr.seq.{Adjacent, CrossEntity, Types}
import molecule.db.sql.sqlite.setup.Api_sqlite_async

class AdjacentTest extends MUnit {
  Adjacent(this, Api_sqlite_async)
}
class CrossEntityTest extends MUnit {
  CrossEntity(this, Api_sqlite_async)
}
class TypesTest extends MUnit {
  Types(this, Api_sqlite_async)
}
