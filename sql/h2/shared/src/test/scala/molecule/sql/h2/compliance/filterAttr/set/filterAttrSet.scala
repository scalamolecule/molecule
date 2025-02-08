package molecule.sql.h2.compliance.filterAttr.set

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.filterAttr.set._
import molecule.sql.h2.setup.Api_h2_async

class Adjacent extends Test {
  Adjacent(this, Api_h2_async)
}
class CrossEntity extends Test {
  CrossEntity(this, Api_h2_async)
}
class Types extends Test {
  Types(this, Api_h2_async)
}
