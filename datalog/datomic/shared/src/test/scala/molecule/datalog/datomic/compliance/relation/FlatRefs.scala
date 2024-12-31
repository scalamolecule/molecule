package molecule.datalog.datomic.compliance.relation

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.relation.flat._
import molecule.datalog.datomic.setup.Api_datomic_async

class FlatRef extends MUnitSuite {
  FlatRef(this, Api_datomic_async)
}
class FlatRefOpt extends MUnitSuite {
  FlatRefOpt(this, Api_datomic_async)
}
class FlatRefOptNested extends MUnitSuite {
  FlatRefOptNested(this, Api_datomic_async)
}
class FlatRefOptAdjacent extends MUnitSuite {
  FlatRefOptAdjacent(this, Api_datomic_async)
}
