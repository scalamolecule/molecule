package molecule.datalog.datomic.compliance.filterAttr.seq

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.filterAttr.seq._
import molecule.datalog.datomic.setup.Api_datomic_async

class Adjacent extends MUnitSuite {
  Adjacent(this, Api_datomic_async)
}
class CrossNs extends MUnitSuite {
  CrossNs(this, Api_datomic_async)
}
class Types extends MUnitSuite {
  Types(this, Api_datomic_async)
}
