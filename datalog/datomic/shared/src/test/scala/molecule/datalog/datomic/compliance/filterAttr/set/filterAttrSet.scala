package molecule.datalog.datomic.compliance.filterAttr.set

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.filterAttr.set._
import molecule.datalog.datomic.setup.Api_datomic_async

class Adjacent extends Test {
  Adjacent(this, Api_datomic_async)
}
class CrossNs extends Test {
  CrossNs(this, Api_datomic_async)
}
class Types extends Test {
  Types(this, Api_datomic_async)
}
