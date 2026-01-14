package molecule.db.h2.compliance.pagination.offset

import molecule.core.setup.MUnit
import molecule.db
import molecule.db.compliance.test.pagination.offset.{Offset, OffsetSemantics}
import molecule.db.h2.setup.Api_h2_async

class OffsetTest extends MUnit {
  Offset(this, Api_h2_async)
}
class OffsetSemanticsTest extends MUnit {
  OffsetSemantics(this, Api_h2_async)
}
