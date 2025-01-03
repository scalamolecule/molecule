package molecule.sql.mariadb.compliance.segments

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.segments._
import molecule.sql.mariadb.setup.Api_mariadb_async

class Prefixed extends Test {
  Prefixed(this, Api_mariadb_async)
}
