package molecule.db.sql.h2.compliance.filterAttr.set

import molecule.core.setup.MUnit
import molecule.db.compliance.test.filterAttr.set.{Adjacent, CrossEntity, Types}
import molecule.db.sql.h2.setup.Api_h2_async

class AdjacentTest extends MUnit {
  Adjacent(this, Api_h2_async)
}
class CrossEntityTest extends MUnit {
  CrossEntity(this, Api_h2_async)
}
class TypesTest extends MUnit {
  Types(this, Api_h2_async)
}
