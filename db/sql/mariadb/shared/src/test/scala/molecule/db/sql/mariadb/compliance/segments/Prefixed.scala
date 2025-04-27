package molecule.db.sql.mariadb.compliance.segments

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.segments.*
import molecule.db.sql
import molecule.db.sql.mariadb.setup.Api_mariadb_async

class PrefixedTest extends Test {
  Prefixed(this, Api_mariadb_async)
}
