package molecule.sql.mariadb.compliance.segments

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.segments.*
import molecule.sql.mariadb.setup.Api_mariadb_async

class PrefixedTest extends Test {
  Prefixed(this, Api_mariadb_async)
}
