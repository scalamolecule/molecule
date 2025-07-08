package molecule.db.sql.sqlite.compliance.pagination.cursor.primaryUnique

import molecule.core.setup.MUnit
import molecule.db.compliance.test.pagination.cursor.primaryUnique.*
import molecule.db.sql.sqlite.setup.Api_sqlite_async

class DirectionsTest extends MUnit {
  Directions(this, Api_sqlite_async)
}
class MutationAddTest extends MUnit {
  MutationAdd(this, Api_sqlite_async)
}
class MutationDeleteTest extends MUnit {
  MutationDelete(this, Api_sqlite_async)
}
class NestedTest extends MUnit {
  Nested(this, Api_sqlite_async)
}
class TypesFilterAttrTest extends MUnit {
  TypesFilterAttr(this, Api_sqlite_async)
}
