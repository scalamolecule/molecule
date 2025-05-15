package molecule.db.sql.mysql.compliance.subscription

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.subscription.Subscription
import molecule.db.sql.mysql.setup.Api_mysql_async

class SubscriptionTest extends Test {
  Subscription(this, Api_mysql_async)
}
