package molecule.db.sql.h2.compliance.segments

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.segments.*
import molecule.db.sql
import molecule.db.sql.h2.setup.Api_h2_async

class PrefixedTest extends Test {
  Prefixed(this, Api_h2_async)
}
