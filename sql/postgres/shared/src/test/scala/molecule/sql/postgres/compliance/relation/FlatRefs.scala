package molecule.sql.postgres.compliance.relation

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.relation.flat._
import molecule.sql.postgres.setup.Api_postgres_async

class FlatRefTest extends Test {
  FlatRef(this, Api_postgres_async)
}
class FlatOptEntityTest extends Test {
  FlatOptEntity(this, Api_postgres_async)
}
class FlatOptRefTest extends Test {
  FlatOptRef(this, Api_postgres_async)
}
class FlatOptRefNestedTest extends Test {
  FlatOptRefNested(this, Api_postgres_async)
}
class FlatOptRefAdjacentTest extends Test {
  FlatOptRefAdjacent(this, Api_postgres_async)
}
