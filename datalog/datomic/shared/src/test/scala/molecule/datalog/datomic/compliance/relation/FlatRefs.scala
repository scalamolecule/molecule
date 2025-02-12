package molecule.datalog.datomic.compliance.relation

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.relation.flat._
import molecule.datalog.datomic.setup.Api_datomic_async

class FlatRef extends Test {
  FlatRef(this, Api_datomic_async)
}
class FlatOptEntity extends Test {
  FlatOptEntity(this, Api_datomic_async)
}
class FlatOptRef extends Test {
  FlatOptRef(this, Api_datomic_async)
}
class FlatOptRefNested extends Test {
  FlatOptRefNested(this, Api_datomic_async)
}
class FlatOptRefAdjacent extends Test {
  FlatOptRefAdjacent(this, Api_datomic_async)
}
