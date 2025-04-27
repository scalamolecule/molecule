package molecule.sql.sqlite.compliance.pagination.cursor.primaryUnique

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.pagination.cursor.primaryUnique.*
import molecule.sql.sqlite.setup.Api_sqlite_async

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
