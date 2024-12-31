package molecule.datalog.datomic.compliance.subscription

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.subscription.Subscription
import molecule.datalog.datomic.setup.Api_datomic_async

class Subscription extends MUnitSuite {
  Subscription(this, Api_datomic_async)
}
