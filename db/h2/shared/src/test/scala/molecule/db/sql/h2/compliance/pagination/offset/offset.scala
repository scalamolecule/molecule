package molecule.db.sql.h2.compliance.pagination.offset

import molecule.core.setup.MUnit
import molecule.db.compliance.test.pagination.offset.{OffsetBackwards, OffsetForward, OffsetSemantics}
import molecule.db.sql.h2.setup.Api_h2_async

class OffsetBackwardsTest extends MUnit {
  OffsetBackwards(this, Api_h2_async)
}
class OffsetForwardTest extends MUnit {
  OffsetForward(this, Api_h2_async)
}
class OffsetSemanticsTest extends MUnit {
  OffsetSemantics(this, Api_h2_async)
}
