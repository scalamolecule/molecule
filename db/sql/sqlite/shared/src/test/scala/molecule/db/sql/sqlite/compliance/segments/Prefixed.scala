package molecule.db.sql.sqlite.compliance.segments

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.segments.*
import molecule.db.sql
import molecule.db.sql.sqlite.setup.Api_sqlite_async

class PrefixedTest extends Test {
  Prefixed(this, Api_sqlite_async)
}
