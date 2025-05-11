package molecule.db.datalog.datomic.compliance.subscription

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.subscription.Subscription
import molecule.db.datalog
import molecule.db.datalog.datomic.setup.Api_datomic_async

class SubscriptionTest extends Test {
  Subscription(this, Api_datomic_async)
}
