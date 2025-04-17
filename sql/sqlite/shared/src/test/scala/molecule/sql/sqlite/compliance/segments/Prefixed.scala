package molecule.sql.sqlite.compliance.segments

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.segments._
import molecule.sql.sqlite.setup.Api_sqlite_async

class PrefixedTest extends Test {
  Prefixed(this, Api_sqlite_async)
}
