package molecule.db.sql.sqlite.compliance.relation

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.relation.flat.*
import molecule.db.sql.sqlite.setup.Api_sqlite_async

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