package molecule.datalog.datomic.compliance.subscription

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.subscription.*
import molecule.datalog.datomic.setup.Api_datomic_async

class SubscriptionTest extends Test {
  Subscription(this, Api_datomic_async)
}
