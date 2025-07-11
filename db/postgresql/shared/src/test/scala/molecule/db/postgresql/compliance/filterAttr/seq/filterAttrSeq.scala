package molecule.db.postgresql.compliance.filterAttr.seq

import molecule.core.setup.MUnit
import molecule.db
import molecule.db.compliance.test.filterAttr.seq.{Adjacent, CrossEntity, Types}
import molecule.db.postgresql.setup.Api_postgresql_async

class AdjacentTest extends MUnit {
  Adjacent(this, Api_postgresql_async)
}
class CrossEntityTest extends MUnit {
  CrossEntity(this, Api_postgresql_async)
}
class TypesTest extends MUnit {
  Types(this, Api_postgresql_async)
}
