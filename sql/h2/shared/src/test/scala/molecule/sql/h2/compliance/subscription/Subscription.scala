package molecule.sql.h2.compliance.subscription

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.subscription.Subscription
import molecule.sql.h2.setup.Api_h2_async

class Subscription extends MUnitSuite {
  Subscription(this, Api_h2_async)
}
