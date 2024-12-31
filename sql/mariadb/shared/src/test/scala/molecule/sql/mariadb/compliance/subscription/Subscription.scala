package molecule.sql.mariadb.compliance.subscription

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.subscription.Subscription
import molecule.sql.mariadb.setup.Api_mariadb_async

class Subscription extends MUnitSuite {
  Subscription(this, Api_mariadb_async)
}
