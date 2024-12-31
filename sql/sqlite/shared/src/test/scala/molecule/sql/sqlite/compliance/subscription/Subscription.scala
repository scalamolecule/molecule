package molecule.sql.sqlite.compliance.subscription

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.subscription.Subscription
import molecule.sql.sqlite.setup.Api_sqlite_async

class Subscription extends MUnitSuite {
  Subscription(this, Api_sqlite_async)
}
