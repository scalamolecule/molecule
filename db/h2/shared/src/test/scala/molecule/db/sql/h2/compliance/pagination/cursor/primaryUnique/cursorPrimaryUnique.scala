package molecule.db.sql.h2.compliance.pagination.cursor.primaryUnique

import molecule.core.setup.MUnit
import molecule.db.compliance.test.pagination.cursor.primaryUnique.*
import molecule.db.sql.h2.setup.Api_h2_async

class DirectionsTest extends MUnit {
  Directions(this, Api_h2_async)
}
class MutationAddTest extends MUnit {
  MutationAdd(this, Api_h2_async)
}
class MutationDeleteTest extends MUnit {
  MutationDelete(this, Api_h2_async)
}
class NestedTest extends MUnit {
  Nested(this, Api_h2_async)
}
class TypesFilterAttrTest extends MUnit {
  TypesFilterAttr(this, Api_h2_async)
}
