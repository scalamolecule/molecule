package molecule.sql.h2.compliance.pagination.offset

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.pagination.offset._
import molecule.sql.h2.setup.Api_h2_async

class OffsetBackwards extends Test {
  OffsetBackwards(this, Api_h2_async)
}
class OffsetForward extends Test {
  OffsetForward(this, Api_h2_async)
}
class OffsetSemantics extends Test {
  OffsetSemantics(this, Api_h2_async)
}
