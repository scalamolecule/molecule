package molecule.sql.sqlite.compliance.relation

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.relation.flat.*
import molecule.sql.sqlite.setup.Api_sqlite_async

class FlatRefTest extends Test {
  FlatRef(this, Api_sqlite_async)
}
class FlatOptEntityTest extends Test {
  FlatOptEntity(this, Api_sqlite_async)
}
class FlatOptRefTest extends Test {
  FlatOptRef(this, Api_sqlite_async)
}
class FlatOptRefNestedTest extends Test {
  FlatOptRefNested(this, Api_sqlite_async)
}
class FlatOptRefAdjacentTest extends Test {
  FlatOptRefAdjacent(this, Api_sqlite_async)
}