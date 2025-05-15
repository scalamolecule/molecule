package molecule.db.sql.h2.compliance.relation

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.relation.flat.*
import molecule.db.sql.h2.setup.Api_h2_async

class FlatRefTest extends Test {
  FlatRef(this, Api_h2_async)
}
class FlatOptEntityTest extends Test {
  FlatOptEntity(this, Api_h2_async)
}
class FlatOptRefTest extends Test {
  FlatOptRef(this, Api_h2_async)
}
class FlatOptRefNestedTest extends Test {
  FlatOptRefNested(this, Api_h2_async)
}
class FlatOptRefAdjacentTest extends Test {
  FlatOptRefAdjacent(this, Api_h2_async)
}
