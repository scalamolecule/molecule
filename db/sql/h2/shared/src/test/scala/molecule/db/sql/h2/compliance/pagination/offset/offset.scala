package molecule.db.sql.h2.compliance.pagination.offset

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.pagination.offset.{OffsetBackwards, OffsetForward, OffsetSemantics}
import molecule.db.sql.h2.setup.Api_h2_async

class OffsetBackwardsTest extends Test {
  OffsetBackwards(this, Api_h2_async)
}
class OffsetForwardTest extends Test {
  OffsetForward(this, Api_h2_async)
}
class OffsetSemanticsTest extends Test {
  OffsetSemantics(this, Api_h2_async)
}
