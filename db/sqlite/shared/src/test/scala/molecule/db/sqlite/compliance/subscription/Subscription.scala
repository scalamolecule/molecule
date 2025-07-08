package molecule.db.sqlite.compliance.subscription

import molecule.core.setup.MUnit
import molecule.db
import molecule.db.compliance.test.subscription.Subscription
import molecule.db.sqlite.setup.Api_sqlite_async

class SubscriptionTest extends MUnit {
  Subscription(this, Api_sqlite_async)
}
