package molecule.db.postgresql.compliance.pagination.offset

import molecule.core.setup.MUnit
import molecule.db
import molecule.db.compliance.test.pagination.offset.{OffsetBackwards, OffsetForward, OffsetSemantics}
import molecule.db.postgresql.setup.Api_postgresql_async

class OffsetBackwardsTest extends MUnit {
  OffsetBackwards(this, Api_postgresql_async)
}
class OffsetForwardTest extends MUnit {
  OffsetForward(this, Api_postgresql_async)
}
class OffsetSemanticsTest extends MUnit {
  OffsetSemantics(this, Api_postgresql_async)
}
