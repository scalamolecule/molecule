package molecule.db.mariadb.compliance.subscription

import molecule.core.setup.MUnit
import molecule.db
import molecule.db.compliance.test.subscription.Subscription
import molecule.db.mariadb.setup.Api_mariadb_async

class SubscriptionTest extends MUnit {
  Subscription(this, Api_mariadb_async)
}
