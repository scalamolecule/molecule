package molecule.db.datalog.datomic.compliance.subscription

import molecule.core.setup.MUnit
import molecule.db.compliance.test.subscription.Subscription
import molecule.db.datalog.datomic.setup.Api_datomic_async

class SubscriptionTest extends MUnit {
  Subscription(this, Api_datomic_async)
}
