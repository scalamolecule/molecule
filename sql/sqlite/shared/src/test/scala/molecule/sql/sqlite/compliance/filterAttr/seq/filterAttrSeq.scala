package molecule.sql.sqlite.compliance.filterAttr.seq

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.filterAttr.seq.*
import molecule.sql.sqlite.setup.Api_sqlite_async

class AdjacentTest extends Test {
  Adjacent(this, Api_sqlite_async)
}
class CrossEntityTest extends Test {
  CrossEntity(this, Api_sqlite_async)
}
class TypesTest extends Test {
  Types(this, Api_sqlite_async)
}
