package molecule.sql.mariadb.compliance.subscription

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.subscription._
import molecule.sql.mariadb.setup.Api_mariadb_async

class Subscription extends Test {
  Subscription(this, Api_mariadb_async)
}
