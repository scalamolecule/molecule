package molecule.sql.sqlite.compliance.pagination.cursor.primaryUnique

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.pagination.cursor.primaryUnique._
import molecule.sql.sqlite.setup.Api_sqlite_async

class Directions extends MUnitSuite {
  Directions(this, Api_sqlite_async)
}
class MutationAdd extends MUnitSuite {
  MutationAdd(this, Api_sqlite_async)
}
class MutationDelete extends MUnitSuite {
  MutationDelete(this, Api_sqlite_async)
}
class Nested extends MUnitSuite {
  Nested(this, Api_sqlite_async)
}
class TypesFilterAttr extends MUnitSuite {
  TypesFilterAttr(this, Api_sqlite_async)
}
