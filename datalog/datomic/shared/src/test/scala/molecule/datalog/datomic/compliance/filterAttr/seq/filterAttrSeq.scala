package molecule.datalog.datomic.compliance.filterAttr.seq

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.filterAttr.seq._
import molecule.datalog.datomic.setup.Api_datomic_async

class AdjacentTest extends Test {
  Adjacent(this, Api_datomic_async)
}
class CrossEntityTest extends Test {
  CrossEntity(this, Api_datomic_async)
}
class TypesTest extends Test {
  Types(this, Api_datomic_async)
}
