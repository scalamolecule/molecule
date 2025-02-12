package molecule.sql.h2.compliance.relation

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.relation.flat._
import molecule.sql.h2.setup.Api_h2_async

class FlatRef extends Test {
  FlatRef(this, Api_h2_async)
}
class FlatOptEntity extends Test {
  FlatOptEntity(this, Api_h2_async)
}
class FlatOptRef extends Test {
  FlatOptRef(this, Api_h2_async)
}
class FlatOptRefNested extends Test {
  FlatOptRefNested(this, Api_h2_async)
}
class FlatOptRefAdjacent extends Test {
  FlatOptRefAdjacent(this, Api_h2_async)
}
