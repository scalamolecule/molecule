package molecule.db.postgresql.compliance.subscription

import molecule.core.setup.MUnit
import molecule.db
import molecule.db.compliance.test.subscription.Subscription
import molecule.db.postgresql.setup.Api_postgresql_async

class SubscriptionTest extends MUnit {
  Subscription(this, Api_postgresql_async)
}
