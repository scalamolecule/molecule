package molecule.sql.postgres.compliance.pagination.offset

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.pagination.offset._
import molecule.sql.postgres.setup.Api_postgres_async

class OffsetBackwards extends Test {
  OffsetBackwards(this, Api_postgres_async)
}
class OffsetForward extends Test {
  OffsetForward(this, Api_postgres_async)
}
class OffsetSemantics extends Test {
  OffsetSemantics(this, Api_postgres_async)
}
