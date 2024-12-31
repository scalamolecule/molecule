package molecule.sql.h2.compliance.relation

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.relation.flat._
import molecule.sql.h2.setup.Api_h2_async

class FlatRef extends MUnitSuite {
  FlatRef(this, Api_h2_async)
}
class FlatRefOpt extends MUnitSuite {
  FlatRefOpt(this, Api_h2_async)
}
class FlatRefOptNested extends MUnitSuite {
  FlatRefOptNested(this, Api_h2_async)
}
class FlatRefOptAdjacent extends MUnitSuite {
  FlatRefOptAdjacent(this, Api_h2_async)
}
