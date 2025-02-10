package molecule.sql.h2.compliance.relation

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.relation.flat._
import molecule.sql.h2.setup.Api_h2_async

class FlatRef extends Test {
  FlatRef(this, Api_h2_async)
}
class FlatRefOpt extends Test {
  FlatOptRef(this, Api_h2_async)
}
class FlatRefOptNested extends Test {
  FlatRefOptNested(this, Api_h2_async)
}
class FlatRefOptAdjacent extends Test {
  FlatRefOptAdjacent(this, Api_h2_async)
}
