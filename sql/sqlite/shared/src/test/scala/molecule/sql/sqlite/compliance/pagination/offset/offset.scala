package molecule.sql.sqlite.compliance.pagination.offset

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.pagination.offset._
import molecule.sql.sqlite.setup.Api_sqlite_async

class OffsetBackwards extends MUnitSuite {
  OffsetBackwards(this, Api_sqlite_async)
}
class OffsetForward extends MUnitSuite {
  OffsetForward(this, Api_sqlite_async)
}
class OffsetSemantics extends MUnitSuite {
  OffsetSemantics(this, Api_sqlite_async)
}
