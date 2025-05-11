package molecule.db.sql.postgres.compliance.subscription

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.subscription.Subscription
import molecule.db.sql
import molecule.db.sql.postgres.setup.Api_postgres_async

class SubscriptionTest extends Test {
  Subscription(this, Api_postgres_async)
}
