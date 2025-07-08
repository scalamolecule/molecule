package molecule.db.sql.mariadb.compliance.subscription

import molecule.core.setup.MUnit
import molecule.db.compliance.test.subscription.Subscription
import molecule.db.sql.mariadb.setup.Api_mariadb_async

class SubscriptionTest extends MUnit {
  Subscription(this, Api_mariadb_async)
}
