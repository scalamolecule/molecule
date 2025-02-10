package molecule.sql.postgres.compliance.relation

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.relation.flat._
import molecule.sql.postgres.setup.Api_postgres_async

class FlatRef extends Test {
  FlatRef(this, Api_postgres_async)
}
class FlatRefOpt extends Test {
  FlatOptRef(this, Api_postgres_async)
}
class FlatRefOptNested extends Test {
  FlatRefOptNested(this, Api_postgres_async)
}
class FlatRefOptAdjacent extends Test {
  FlatRefOptAdjacent(this, Api_postgres_async)
}
