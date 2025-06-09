package molecule.db.sql.mysql.compliance.subscription

import molecule.core.setup.MUnit
import molecule.db.compliance.test.subscription.Subscription
import molecule.db.sql.mysql.setup.Api_mysql_async

class SubscriptionTest extends MUnit {
  Subscription(this, Api_mysql_async)
}
