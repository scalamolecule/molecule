package molecule.db.h2.compliance.subscription

import molecule.core.setup.MUnit
import molecule.db
import molecule.db.compliance.test.subscription.Subscription
import molecule.db.h2.setup.Api_h2_async

class SubscriptionTest extends MUnit {
  Subscription(this, Api_h2_async)
}
