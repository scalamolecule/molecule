package molecule.datalog.datomic.compliance.filterAttr.seq

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.filterAttr.seq._
import molecule.datalog.datomic.setup.Api_datomic_async

class Adjacent extends Test {
  Adjacent(this, Api_datomic_async)
}
class CrossEntity extends Test {
  CrossEntity(this, Api_datomic_async)
}
class Types extends Test {
  Types(this, Api_datomic_async)
}
