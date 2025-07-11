package molecule.db.postgresql.compliance.pagination.cursor.primaryUnique

import molecule.core.setup.MUnit
import molecule.db
import molecule.db.compliance.test.pagination.cursor.primaryUnique.*
import molecule.db.postgresql.setup.Api_postgresql_async

class DirectionsTest extends MUnit {
  Directions(this, Api_postgresql_async)
}
class MutationAddTest extends MUnit {
  MutationAdd(this, Api_postgresql_async)
}
class MutationDeleteTest extends MUnit {
  MutationDelete(this, Api_postgresql_async)
}
class NestedTest extends MUnit {
  Nested(this, Api_postgresql_async)
}
class TypesFilterAttrTest extends MUnit {
  TypesFilterAttr(this, Api_postgresql_async)
}
