package molecule.sql.h2.compliance.filterAttr.seq

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.filterAttr.seq._
import molecule.sql.h2.setup.Api_h2_async

class Adjacent extends Test {
  Adjacent(this, Api_h2_async)
}
class CrossNs extends Test {
  CrossNs(this, Api_h2_async)
}
class Types extends Test {
  Types(this, Api_h2_async)
}
