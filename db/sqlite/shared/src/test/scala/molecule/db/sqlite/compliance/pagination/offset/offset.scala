package molecule.db.sqlite.compliance.pagination.offset

import molecule.core.setup.MUnit
import molecule.db
import molecule.db.compliance.test.pagination.offset.{OffsetBackwards, OffsetForward, OffsetSemantics}
import molecule.db.sqlite.setup.Api_sqlite_async

class OffsetBackwardsTest extends MUnit {
  OffsetBackwards(this, Api_sqlite_async)
}
class OffsetForwardTest extends MUnit {
  OffsetForward(this, Api_sqlite_async)
}
class OffsetSemanticsTest extends MUnit {
  OffsetSemantics(this, Api_sqlite_async)
}
