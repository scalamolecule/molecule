package molecule.db.datalog.datomic.compliance.subscription

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.subscription.*
import molecule.db.datalog
import molecule.db.datalog.datomic.setup.Api_datomic_async

class SubscriptionTest extends Test {
  Subscription(this, Api_datomic_async)
}
