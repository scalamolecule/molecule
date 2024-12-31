package molecule.sql.h2.compliance.filterAttr.seq

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.filterAttr.seq._
import molecule.sql.h2.setup.Api_h2_async

class Adjacent extends MUnitSuite {
  Adjacent(this, Api_h2_async)
}
class CrossNs extends MUnitSuite {
  CrossNs(this, Api_h2_async)
}
class Types extends MUnitSuite {
  Types(this, Api_h2_async)
}
