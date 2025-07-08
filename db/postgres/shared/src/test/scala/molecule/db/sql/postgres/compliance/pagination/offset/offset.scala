package molecule.db.sql.postgres.compliance.pagination.offset

import molecule.core.setup.MUnit
import molecule.db.compliance.test.pagination.offset.{OffsetBackwards, OffsetForward, OffsetSemantics}
import molecule.db.sql.postgres.setup.Api_postgres_async

class OffsetBackwardsTest extends MUnit {
  OffsetBackwards(this, Api_postgres_async)
}
class OffsetForwardTest extends MUnit {
  OffsetForward(this, Api_postgres_async)
}
class OffsetSemanticsTest extends MUnit {
  OffsetSemantics(this, Api_postgres_async)
}
