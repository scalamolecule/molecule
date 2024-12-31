package molecule.sql.postgres.compliance.relation

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.relation.flat._
import molecule.sql.postgres.setup.Api_postgres_async

class FlatRef extends MUnitSuite {
  FlatRef(this, Api_postgres_async)
}
class FlatRefOpt extends MUnitSuite {
  FlatRefOpt(this, Api_postgres_async)
}
class FlatRefOptNested extends MUnitSuite {
  FlatRefOptNested(this, Api_postgres_async)
}
class FlatRefOptAdjacent extends MUnitSuite {
  FlatRefOptAdjacent(this, Api_postgres_async)
}
