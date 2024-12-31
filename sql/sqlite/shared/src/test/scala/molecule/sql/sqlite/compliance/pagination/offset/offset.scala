package molecule.sql.sqlite.compliance.pagination.offset

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.pagination.offset._
import molecule.sql.sqlite.setup.Api_sqlite_async

class OffsetBackwards extends Test {
  OffsetBackwards(this, Api_sqlite_async)
}
class OffsetForward extends Test {
  OffsetForward(this, Api_sqlite_async)
}
class OffsetSemantics extends Test {
  OffsetSemantics(this, Api_sqlite_async)
}
