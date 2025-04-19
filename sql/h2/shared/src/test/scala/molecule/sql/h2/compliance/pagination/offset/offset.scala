package molecule.sql.h2.compliance.pagination.offset

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.pagination.offset.*
import molecule.sql.h2.setup.Api_h2_async

class OffsetBackwardsTest extends Test {
  OffsetBackwards(this, Api_h2_async)
}
class OffsetForwardTest extends Test {
  OffsetForward(this, Api_h2_async)
}
class OffsetSemanticsTest extends Test {
  OffsetSemantics(this, Api_h2_async)
}
