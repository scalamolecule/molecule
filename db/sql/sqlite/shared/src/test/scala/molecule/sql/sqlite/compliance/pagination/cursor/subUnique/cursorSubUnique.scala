package molecule.sql.sqlite.compliance.pagination.cursor.subUnique

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.pagination.cursor.subUnique.*
import molecule.sql.sqlite.setup.Api_sqlite_async

class AttrOrderTest extends Test {
  AttrOrder(this, Api_sqlite_async)
}
class DirectionsStandardUniqueTest extends Test {
  DirectionsStandardUnique(this, Api_sqlite_async)
}
class DirectionsUniqueStandardTest extends Test {
  DirectionsUniqueStandard(this, Api_sqlite_async)
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
class OptNestedTest extends Test {
  OptNested(this, Api_sqlite_async)
}
class TypesUniqueValueTest extends Test {
  TypesUniqueValue(this, Api_sqlite_async)
}
