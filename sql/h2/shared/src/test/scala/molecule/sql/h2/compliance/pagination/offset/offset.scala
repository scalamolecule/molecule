package molecule.sql.h2.compliance.pagination.offset

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.pagination.offset._
import molecule.sql.h2.setup.Api_h2_async

class OffsetBackwards extends MUnitSuite {
  OffsetBackwards(this, Api_h2_async)
}
class OffsetForward extends MUnitSuite {
  OffsetForward(this, Api_h2_async)
}
class OffsetSemantics extends MUnitSuite {
  OffsetSemantics(this, Api_h2_async)
}
