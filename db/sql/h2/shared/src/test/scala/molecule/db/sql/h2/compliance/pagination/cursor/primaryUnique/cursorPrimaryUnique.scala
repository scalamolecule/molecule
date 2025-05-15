package molecule.db.sql.h2.compliance.pagination.cursor.primaryUnique

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.pagination.cursor.primaryUnique.*
import molecule.db.sql.h2.setup.Api_h2_async

class DirectionsTest extends Test {
  Directions(this, Api_h2_async)
}
class MutationAddTest extends Test {
  MutationAdd(this, Api_h2_async)
}
class MutationDeleteTest extends Test {
  MutationDelete(this, Api_h2_async)
}
class NestedTest extends Test {
  Nested(this, Api_h2_async)
}
class TypesFilterAttrTest extends Test {
  TypesFilterAttr(this, Api_h2_async)
}
