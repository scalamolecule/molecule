package molecule.db.sql.h2.compliance.subscription

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.subscription.Subscription
import molecule.db.sql.h2.setup.Api_h2_async

class SubscriptionTest extends Test {
  Subscription(this, Api_h2_async)
}
