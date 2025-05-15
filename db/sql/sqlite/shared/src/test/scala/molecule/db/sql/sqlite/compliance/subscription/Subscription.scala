package molecule.db.sql.sqlite.compliance.subscription

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.subscription.Subscription
import molecule.db.sql.sqlite.setup.Api_sqlite_async

class SubscriptionTest extends Test {
  Subscription(this, Api_sqlite_async)
}
