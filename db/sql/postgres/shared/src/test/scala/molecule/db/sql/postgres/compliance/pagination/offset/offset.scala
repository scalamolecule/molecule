package molecule.db.sql.postgres.compliance.pagination.offset

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.pagination.offset.{OffsetBackwards, OffsetForward, OffsetSemantics}
import molecule.db.sql.postgres.setup.Api_postgres_async

class OffsetBackwardsTest extends Test {
  OffsetBackwards(this, Api_postgres_async)
}
class OffsetForwardTest extends Test {
  OffsetForward(this, Api_postgres_async)
}
class OffsetSemanticsTest extends Test {
  OffsetSemantics(this, Api_postgres_async)
}
