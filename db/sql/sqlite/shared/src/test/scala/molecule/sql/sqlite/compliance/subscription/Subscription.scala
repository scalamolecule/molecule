package molecule.sql.sqlite.compliance.subscription

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.subscription.*
import molecule.sql.sqlite.setup.Api_sqlite_async

class SubscriptionTest extends Test {
  Subscription(this, Api_sqlite_async)
}
