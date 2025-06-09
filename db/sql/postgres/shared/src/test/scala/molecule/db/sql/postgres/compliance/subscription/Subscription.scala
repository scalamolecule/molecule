package molecule.db.sql.postgres.compliance.subscription

import molecule.core.setup.MUnit
import molecule.db.compliance.test.subscription.Subscription
import molecule.db.sql.postgres.setup.Api_postgres_async

class SubscriptionTest extends MUnit {
  Subscription(this, Api_postgres_async)
}
