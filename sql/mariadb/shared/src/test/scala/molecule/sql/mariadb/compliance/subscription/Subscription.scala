package molecule.sql.mariadb.compliance.subscription

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.subscription.*
import molecule.sql.mariadb.setup.Api_mariadb_async

class SubscriptionTest extends Test {
  Subscription(this, Api_mariadb_async)
}
