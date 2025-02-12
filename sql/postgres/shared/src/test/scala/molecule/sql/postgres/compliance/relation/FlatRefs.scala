package molecule.sql.postgres.compliance.relation

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.relation.flat._
import molecule.sql.postgres.setup.Api_postgres_async

class FlatRef extends Test {
  FlatRef(this, Api_postgres_async)
}
class FlatOptEntity extends Test {
  FlatOptEntity(this, Api_postgres_async)
}
class FlatOptRef extends Test {
  FlatOptRef(this, Api_postgres_async)
}
class FlatOptRefNested extends Test {
  FlatOptRefNested(this, Api_postgres_async)
}
class FlatOptRefAdjacent extends Test {
  FlatOptRefAdjacent(this, Api_postgres_async)
}
