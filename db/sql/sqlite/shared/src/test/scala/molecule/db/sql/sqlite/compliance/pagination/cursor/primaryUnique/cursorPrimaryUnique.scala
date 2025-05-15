package molecule.db.sql.sqlite.compliance.pagination.cursor.primaryUnique

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.pagination.cursor.primaryUnique.*
import molecule.db.sql.sqlite.setup.Api_sqlite_async

class DirectionsTest extends Test {
  Directions(this, Api_sqlite_async)
}
class MutationAddTest extends Test {
  MutationAdd(this, Api_sqlite_async)
}
class MutationDeleteTest extends Test {
  MutationDelete(this, Api_sqlite_async)
}
class NestedTest extends Test {
  Nested(this, Api_sqlite_async)
}
class TypesFilterAttrTest extends Test {
  TypesFilterAttr(this, Api_sqlite_async)
}
