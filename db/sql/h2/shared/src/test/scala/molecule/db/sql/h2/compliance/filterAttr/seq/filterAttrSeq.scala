package molecule.db.sql.h2.compliance.filterAttr.seq

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.filterAttr.seq.{Adjacent, CrossEntity, Types}
import molecule.db.sql
import molecule.db.sql.h2.setup.Api_h2_async

class AdjacentTest extends Test {
  Adjacent(this, Api_h2_async)
}
class CrossEntityTest extends Test {
  CrossEntity(this, Api_h2_async)
}
class TypesTest extends Test {
  Types(this, Api_h2_async)
}
