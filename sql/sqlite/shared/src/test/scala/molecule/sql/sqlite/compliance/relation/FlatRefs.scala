package molecule.sql.sqlite.compliance.relation

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.relation.flat._
import molecule.sql.sqlite.setup.Api_sqlite_async

class FlatRef extends Test {
  FlatRef(this, Api_sqlite_async)
}
class FlatRefOpt extends Test {
  FlatOptRef(this, Api_sqlite_async)
}
class FlatRefOptNested extends Test {
  FlatRefOptNested(this, Api_sqlite_async)
}
class FlatRefOptAdjacent extends Test {
  FlatRefOptAdjacent(this, Api_sqlite_async)
}