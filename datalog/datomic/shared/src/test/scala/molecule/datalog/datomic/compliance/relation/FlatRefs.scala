package molecule.datalog.datomic.compliance.relation

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.relation.flat._
import molecule.datalog.datomic.setup.Api_datomic_async

class FlatRef extends Test {
  FlatRef(this, Api_datomic_async)
}
class FlatRefOpt extends Test {
  FlatOptRef(this, Api_datomic_async)
}
class FlatRefOptNested extends Test {
  FlatRefOptNested(this, Api_datomic_async)
}
class FlatRefOptAdjacent extends Test {
  FlatRefOptAdjacent(this, Api_datomic_async)
}
