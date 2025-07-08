package molecule.db.sql.sqlite.compliance.subscription

import molecule.core.setup.MUnit
import molecule.db.compliance.test.subscription.Subscription
import molecule.db.sql.sqlite.setup.Api_sqlite_async

class SubscriptionTest extends MUnit {
  Subscription(this, Api_sqlite_async)
}
