package molecule.db.datalog.datomic.compliance.filterAttr.set

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.filterAttr.set.*
import molecule.db.datalog
import molecule.db.datalog.datomic.setup.Api_datomic_async

class AdjacentTest extends Test {
  Adjacent(this, Api_datomic_async)
}
class CrossEntityTest extends Test {
  CrossEntity(this, Api_datomic_async)
}
class TypesTest extends Test {
  Types(this, Api_datomic_async)
}
