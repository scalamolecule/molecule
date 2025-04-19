package molecule.sql.h2.compliance.filterAttr.set

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.filterAttr.set.*
import molecule.sql.h2.setup.Api_h2_async

class AdjacentTest extends Test {
  Adjacent(this, Api_h2_async)
}
class CrossEntityTest extends Test {
  CrossEntity(this, Api_h2_async)
}
class TypesTest extends Test {
  Types(this, Api_h2_async)
}
