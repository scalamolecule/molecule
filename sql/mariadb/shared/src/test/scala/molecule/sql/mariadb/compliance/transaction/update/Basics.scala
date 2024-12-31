package molecule.sql.mariadb.compliance.transaction.update

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.action.update._
import molecule.sql.mariadb.setup.Api_mariadb_async

class Basics extends MUnitSuite {
  Basics(this, Api_mariadb_async)
}
