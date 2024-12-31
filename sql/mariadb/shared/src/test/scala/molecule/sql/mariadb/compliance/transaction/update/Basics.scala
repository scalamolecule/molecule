package molecule.sql.mariadb.compliance.transaction.update

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.transaction.update._
import molecule.sql.mariadb.setup.Api_mariadb_async

class Basics extends Test {
  Basics(this, Api_mariadb_async)
}
