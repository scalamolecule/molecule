package molecule.sql.sqlite.compliance.pagination.cursor.primaryUnique

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.pagination.cursor.primaryUnique._
import molecule.sql.sqlite.setup.Api_sqlite_async

class Directions extends Test {
  Directions(this, Api_sqlite_async)
}
class MutationAdd extends Test {
  MutationAdd(this, Api_sqlite_async)
}
class MutationDelete extends Test {
  MutationDelete(this, Api_sqlite_async)
}
class Nested extends Test {
  Nested(this, Api_sqlite_async)
}
class TypesFilterAttr extends Test {
  TypesFilterAttr(this, Api_sqlite_async)
}
