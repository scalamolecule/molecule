package molecule.db.sql.sqlite.compliance.subscription

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.subscription.*
import molecule.db.sql
import molecule.db.sql.sqlite.setup.Api_sqlite_async

class SubscriptionTest extends Test {
  Subscription(this, Api_sqlite_async)
}
