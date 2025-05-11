package molecule.db.sql.mariadb.compliance.subscription

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.subscription.Subscription
import molecule.db.sql
import molecule.db.sql.mariadb.setup.Api_mariadb_async

class SubscriptionTest extends Test {
  Subscription(this, Api_mariadb_async)
}
