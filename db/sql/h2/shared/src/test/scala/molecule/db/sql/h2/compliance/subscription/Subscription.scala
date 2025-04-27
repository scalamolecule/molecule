package molecule.db.sql.h2.compliance.subscription

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.subscription.*
import molecule.db.sql
import molecule.db.sql.h2.setup.Api_h2_async

class SubscriptionTest extends Test {
  Subscription(this, Api_h2_async)
}
