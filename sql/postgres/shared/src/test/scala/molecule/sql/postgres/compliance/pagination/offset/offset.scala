package molecule.sql.postgres.compliance.pagination.offset

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.pagination.offset._
import molecule.sql.postgres.setup.Api_postgres_async

class OffsetBackwardsTest extends Test {
  OffsetBackwards(this, Api_postgres_async)
}
class OffsetForwardTest extends Test {
  OffsetForward(this, Api_postgres_async)
}
class OffsetSemanticsTest extends Test {
  OffsetSemantics(this, Api_postgres_async)
}
