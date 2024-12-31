package molecule.sql.sqlite.compliance.relation

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.relation.flat._
import molecule.sql.sqlite.setup.Api_sqlite_async

class FlatRef extends MUnitSuite {
  FlatRef(this, Api_sqlite_async)
}
class FlatRefOpt extends MUnitSuite {
  FlatRefOpt(this, Api_sqlite_async)
}
class FlatRefOptNested extends MUnitSuite {
  FlatRefOptNested(this, Api_sqlite_async)
}
class FlatRefOptAdjacent extends MUnitSuite {
  FlatRefOptAdjacent(this, Api_sqlite_async)
}