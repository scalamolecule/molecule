package molecule.sql.postgres.compliance.pagination.offset

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.pagination.offset._
import molecule.sql.postgres.setup.Api_postgres_async

class OffsetBackwards extends MUnitSuite {
  OffsetBackwards(this, Api_postgres_async)
}
class OffsetForward extends MUnitSuite {
  OffsetForward(this, Api_postgres_async)
}
class OffsetSemantics extends MUnitSuite {
  OffsetSemantics(this, Api_postgres_async)
}
